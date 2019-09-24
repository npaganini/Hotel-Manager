package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.webapp.form.ProductsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/{userId}")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ModelAndView getAllExpenses(@PathVariable String userId) {
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
    public ModelAndView getAllProducts(@PathVariable String userId) {
        final ModelAndView mav = new ModelAndView("browseProducts");
        mav.addObject("ProductsList", userService.getProducts());
        return mav;
    }

    @GetMapping("/expenses")
    public ModelAndView boughtProducts(@PathVariable String userId) {
        final ModelAndView mav = new ModelAndView("expenses");
        mav.addObject("ProductsList", userService.checkProductsPurchased());
        return mav;
    }

    @PostMapping("/buyProducts")
    public ModelAndView buyProducts(@ModelAttribute("reservationForm") final ProductsForm form, @PathVariable String userId) {
        final ModelAndView mav = new ModelAndView("buyProducts");
        Charge charge = new Charge(form.getProductId(), userService.getReservation(Long.parseLong(userId)));
        mav.addObject("charge", userService.addCharge(charge));
        return mav;
    }

}
