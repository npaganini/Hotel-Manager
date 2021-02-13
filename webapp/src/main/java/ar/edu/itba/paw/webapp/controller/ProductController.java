package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.webapp.dtos.FileUploadResponse;
import ar.edu.itba.paw.webapp.dtos.ProductRequest;
import ar.edu.itba.paw.webapp.utils.FilesUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.InputStream;

@Controller
@Path("products")
public class ProductController extends SimpleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    public static final String DEFAULT_FIRST_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "20";

    private final ProductService productService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response products(@QueryParam("page") @DefaultValue(DEFAULT_FIRST_PAGE) int page,
                             @QueryParam("limit") @DefaultValue(DEFAULT_PAGE_SIZE) int limit) {
        // todo: mav was "products.jsp"
        PaginatedDTO<Product> products;
        try {
            products = productService.getAll(page, limit);
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return sendPaginatedResponse(page, limit, products, uriInfo);
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
    public Response addProduct(@RequestBody ProductRequest productRequest) throws IOException {
        if (productRequest.getPrice() < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Price must be valid.").build();
        }
        LOGGER.debug("Request to add product to DB received");
        Product newProduct = new Product(productRequest.getDescription(), productRequest.getPrice(),
                FilesUtils.loadImg(productRequest.getImgPath()));
        newProduct = productService.saveProduct(newProduct);
        LOGGER.debug("Product was saved successfully");
        // TODO is this ok?
        return Response.ok(new GenericEntity<Product>(newProduct){}).build();
    }

    @POST
    @Path("/upload-file")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response loadProductFile(@FormDataParam("file") InputStream file,
                                    @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        String fileName = fileDetail.getFileName();

        String pathToFile = FilesUtils.saveFile(file, fileName);

        return Response.ok(new FileUploadResponse(pathToFile)).build();
    }

    @GET
    @Path(value = "/{productId}/img")
    @Produces("image/png")
    public @ResponseBody
    byte[] getImgForProduct(@PathParam("productId") long productId) throws EntityNotFoundException {
        return productService.findProductById(productId).getFile();
    }
}
