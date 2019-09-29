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
    public ModelAndView getAllProducts(@PathVariable String reservationID) {
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
    public ModelAndView buyProducts(@ModelAttribute("productForm") final ProductsForm form, @PathVariable String reservationID, BindingResult result, ModelMap model) {
// Field error in object 'ProductForm' on field 'productId': rejected value [null];
// codes [typeMismatch.ProductForm.productId,typeMismatch.productId,typeMismatch.long,typeMismatch];
// arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [ProductForm.productId,productId];
// arguments []; default message [productId]]; default message [Failed to convert value of type 'null' to required type 'long';
// nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type [null] to type [long] for value 'null';
// nested exception is java.lang.IllegalArgumentException: A null value cannot be assigned to a primitive type]]


// The request sent by the client was syntactically incorrect. BAD REQUEST STATUS 400
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/products");
        }
        final ModelAndView mav = new ModelAndView("buyProducts");
//        model.addAttribute("productID", form.getProductId());
        if(form != null) {
//            Charge charge = new Charge((Long) model.get("productID"), userService.getReservation(reservationID));
            Charge charge = new Charge(form.getProductId(), userService.getReservation(reservationID));
            mav.addObject("charge", userService.addCharge(charge));
            return mav;
        }
        return new ModelAndView("redirect:/products");
    }

}
