package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.services.FileService;
import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.models.product.Product;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.InputStream;
import java.net.URI;

@Controller
@Path("products")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final FileService fileService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public ProductController(final ProductService productService, final FileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response products() {
        // todo: mav was "products.jsp"
        return Response.ok(productService.getAll()).build();
    }

    @POST
    @Path("/{id}/disable")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response hideProduct(@PathParam(value = "id") long productId) throws Exception {
        // todo: mav was "productDisable.jsp"
        return Response.ok(productService.disableProduct(productId)).build();
    }

    @POST
    @Path("/{id}/enable")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response showProduct(@PathParam(value = "id") long productId) throws Exception {
        // todo: mav was "productAvailable.jsp"
        return Response.ok(productService.enableProduct(productId)).build();
    }

    @POST
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response addProduct(
            @FormParam("productImg") String imgPath,
            @FormParam("productDescription") String description,
            @FormParam("productPrice") Double price) {
        if (price < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Price must be valid.").build();
        }
        LOGGER.debug("Request to add product to DB received");
        Product newProduct = new Product(description, price, fileService.loadImg(imgPath));
        productService.saveProduct(newProduct);
        LOGGER.debug("Product was saved successfully");
        // TODO is this ok?
        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newProduct.getId())).build();
        return Response.created(uri).build();
    }

    @POST
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response loadProductFile(@FormDataParam("file") InputStream file,
                                    @FormDataParam("file") FormDataContentDisposition fileDetail) {
        String fileName = fileDetail.getFileName();

        fileService.saveFile(file, fileName);

        return Response.ok(fileName).build();
    }

    @GET
    @Path(value = "/{productId}/img")
    @Produces("image/png")
    public @ResponseBody byte[] getImgForProduct(@PathParam("productId") long productId) throws EntityNotFoundException {
        return productService.findProductById(productId).getFile();
    }
}
