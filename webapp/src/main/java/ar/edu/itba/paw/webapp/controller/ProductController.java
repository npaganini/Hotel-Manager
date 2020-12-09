package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.webapp.form.AddProductForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.net.URI;

@Controller
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
    @Path("/products")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response products() {
        // todo: mav was "products.jsp"
        return Response.ok(productService.getAll()).build();
    }

    @PUT
    @Path("/products/{id}/disable")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response hideProduct(@RequestParam(value = "id", required = false) long productId) throws Exception {
        // todo: mav was "productDisable.jsp"
        return Response.ok(productService.unableProduct(productId)).build();
    }

    @PUT
    @Path("/products/{id}/enable")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response showProduct(@RequestParam(value = "id", required = false) long productId) throws Exception {
        // todo: mav was "productAvailable.jsp"
        return Response.ok(productService.enableProduct(productId)).build();
    }

    @POST
    @Path("/products")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response addProduct(AddProductForm addProductForm) throws IOException {
        if (addProductForm.getPrice() < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        LOGGER.debug("Request to add product to DB received");
        Product newProduct = new Product(addProductForm.getDescription(), addProductForm.getPrice(), addProductForm.getImg().getBytes());
        productService.saveProduct(newProduct);
        LOGGER.debug("Product was saved successfully");
        addProductForm.getImg().getOriginalFilename();
//        return "redirect:/products";
        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newProduct.getId())).build();
        return Response.created(uri).build();
    }

    // todo: re-add this
//    @GET
//    @Path(value = "/products/addProduct")
//    public String inputProduct(Model model) {
//        model.addAttribute("productForm", new AddProductForm());
//        return "addProduct";
//    }

    // todo: re-add this
//    @GET
//    @Path(value = "/product/img", produces = MediaType.IMAGE_PNG_VALUE)
//    public @ResponseBody byte[] getImg(@RequestParam("productId") long productId) throws EntityNotFoundException {
//        return productService.findProductById(productId).getFile();
//    }
}
