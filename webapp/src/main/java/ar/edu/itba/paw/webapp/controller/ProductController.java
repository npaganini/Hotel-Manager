package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.interfaces.services.StorageService;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.webapp.form.ProductForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final StorageService storageService;
    private final ProductService productService;

    @Autowired
    public ProductController(StorageService storageService, ProductService productService) {
        this.storageService = storageService;
        this.productService = productService;
    }

    @GetMapping("/products")
    public ModelAndView products() {
        final ModelAndView mav = new ModelAndView("products");
        mav.addObject("product", productService.getAll());
        return mav;
    }

    @GetMapping("/products/disable")
    public ModelAndView hideProduct(@RequestParam(value = "productId", required = false) long productId) {
        final ModelAndView mav = new ModelAndView("productDisable");
        productService.unableProduct(productId);
        return mav;
    }


    @GetMapping("/products/available")
    public ModelAndView showProduct(@RequestParam(value = "productId", required = false) long productId) {
        final ModelAndView mav = new ModelAndView("productAvailable");
        productService.enableProduct(productId);
        return mav;
    }

    @PostMapping("/products/addProduct")
    public String addProduct(@ModelAttribute ProductForm productForm,
                             RedirectAttributes redirectAttributes) {
        LOGGER.debug("Request to add product to DB received");
        String filePath = storageService.saveProductImg(productForm.getImg());
        productService.saveProduct(new Product(productForm.getDescription(),
                productForm.getPrice(), filePath));
        LOGGER.debug("Product was saved succesfully");
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + filePath + "!");
        return "redirect:/products";
    }

    @GetMapping(value = "/products/addProduct")
    public String inputProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "addProduct";
    }
}
