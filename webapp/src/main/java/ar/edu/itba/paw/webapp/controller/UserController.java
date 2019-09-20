package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ModelAndView getAllExpenses() {
        final ModelAndView mav = new ModelAndView("expenses");
        mav.addObject("ExpensesList", userService.checkAllExpenses());
        return mav;
    }

    @GetMapping("/tours")
    public ModelAndView getAllTours() {
        final ModelAndView mav = new ModelAndView("expenses");
        mav.addObject("ToursList", userService.checkServicesUsed());
        return mav;
    }

    @GetMapping("/products")
    public ModelAndView getAllProducts() {
        final ModelAndView mav = new ModelAndView("expenses");
        mav.addObject("ProductsList", userService.checkProductsPurchased());
        return mav;
    }
}
