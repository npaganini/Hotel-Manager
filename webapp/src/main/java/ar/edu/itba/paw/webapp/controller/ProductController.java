package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.models.product.Product;
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
import java.io.IOException;
import java.net.URI;

@Controller
@Path("products")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response products() {
        // todo: mav was "products.jsp"
        return Response.ok(productService.getAll()).build();
    }

    @PUT
    @Path("/{id}/disable")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response hideProduct(@PathParam(value = "id") long productId) throws Exception {
        // todo: mav was "productDisable.jsp"
        return Response.ok(productService.disableProduct(productId)).build();
    }

    @PUT
    @Path("/{id}/enable")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response showProduct(@PathParam(value = "id") long productId) throws Exception {
        // todo: mav was "productAvailable.jsp"
        return Response.ok(productService.enableProduct(productId)).build();
    }

    @POST
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addProduct(
            // todo: FIXME multipart file
//            @FormParam("productImg") MultipartFile img,
            @FormParam("productImg") String img,
            @FormParam("productDescription") String description,
            @FormParam("productPrice") Double price) throws IOException {
        if (price < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Price must be valid.").build();
        }
        LOGGER.debug("Request to add product to DB received");
        Product newProduct = new Product(description, price, img.getBytes());
        productService.saveProduct(newProduct);
        LOGGER.debug("Product was saved successfully");
//        ((MultipartFile) img).getOriginalFilename();
//        return "redirect:/products";
        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newProduct.getId())).build();
        return Response.created(uri).build();
    }

    // todo: what was this being used by?
//    @GET
//    @Path(value = "/addProduct")
//    public String inputProduct(Model model) {
//        model.addAttribute("productForm", new AddProductForm());
//        return "addProduct";
//    }

    @GET
    @Path(value = "/{productId}/img")
    @Produces("image/png")
    public @ResponseBody byte[] getImg(@PathParam("productId") long productId) throws EntityNotFoundException {
        return productService.findProductById(productId).getFile();
    }
}
