package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/{reservationID}")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ModelAndView getAllExpenses(@PathVariable String reservationID) {
        final ModelAndView mav = new ModelAndView("expenses");
        mav.addObject("ExpensesList", userService.checkAllExpenses());
        return mav;
    }

//    @GetMapping("/tours")
//    public ModelAndView getAllTours() {
//        final ModelAndView mav = new ModelAndView("expenses");
//        mav.addObject("ToursList", userService.checkServicesUsed());
//        return mav;
//    }

    @GetMapping("/products")
    public ModelAndView getAllProducts(@PathVariable String reservationID, @ModelAttribute("productForm") ProductForm productForm) {
        final ModelAndView mav = new ModelAndView("browseProducts");
        mav.addObject("ProductsList", userService.getProducts());
        return mav;
    }

    @GetMapping("/expenses")
    public ModelAndView boughtProducts(@PathVariable String reservationID) {
        final ModelAndView mav = new ModelAndView("expenses");
        mav.addObject("ProductsList", userService.checkProductsPurchased());
        return mav;
    }

    @PostMapping("/buyProducts")
    public ModelAndView buyProduct(@ModelAttribute("productForm") ProductForm productForm, @PathVariable String reservationID){
        if(productForm != null) {
            final ModelAndView mav = new ModelAndView("buyProducts");
            Charge charge = new Charge(productForm.getProductId(), userService.getReservationID(reservationID));
            mav.addObject("charge", userService.addCharge(charge));
            return mav;
        }
        return new ModelAndView("redirect:/products");
    }

}
