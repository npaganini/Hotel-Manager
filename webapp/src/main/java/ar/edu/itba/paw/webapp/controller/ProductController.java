package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.ProductService;
import ar.edu.itba.paw.interfaces.services.StorageService;
import ar.edu.itba.paw.models.product.Product;
import ar.edu.itba.paw.webapp.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final StorageService storageService;
    private final ProductService productService;

    @Autowired
    public ProductController(StorageService storageService, ProductService productService) {
        this.storageService = storageService;
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductForm productForm,
                             RedirectAttributes redirectAttributes) {
        String filePath = storageService.store(productForm.getImg());
        productService.saveProduct(new Product(productForm.getDescription(),
                productForm.getPrice(), filePath));
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + filePath + "!");
        return "redirect:/";
    }

    @RequestMapping(value = "/product-input-form")
    public String inputProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "upload";
    }
}
