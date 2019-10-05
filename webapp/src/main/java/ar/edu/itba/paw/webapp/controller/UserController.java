package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController extends SimpleController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ModelAndView getLandingPage(Authentication authentication) {
        final ModelAndView mav = new ModelAndView("userLanding");
        mav.addObject("ReservationsList",
                userService.findActiveReservation(getUsername(authentication)));
        return mav;
    }

    @GetMapping("/products")
    public ModelAndView getAllProducts(@ModelAttribute("productForm") ProductForm productForm,
                                       @RequestParam(value = "reservationId") long reservationId) {
        final ModelAndView mav = new ModelAndView("browseProducts");
        mav.addObject("ProductsList", userService.getProducts());
        return mav;
    }

    @GetMapping("/expenses")
    public ModelAndView boughtProducts(Authentication authentication, @RequestParam(value = "reservationId") long reservationId) {
        final ModelAndView mav = new ModelAndView("expenses");
        mav.addObject("ProductsList",
                userService.checkProductsPurchasedByUserByReservationId(getUsername(authentication), reservationId));
        return mav;
    }

    @PostMapping("/buyProducts")
    public ModelAndView buyProduct(@ModelAttribute("productForm") ProductForm productForm, @RequestParam(value = "reservationId") long reservationId) {
        if (productForm != null) {
            final ModelAndView mav = new ModelAndView("buyProducts");
            Charge charge = new Charge(productForm.getProductId(), reservationId);
            mav.addObject("charge", userService.addCharge(charge));
            return mav;
        }
        return new ModelAndView("redirect:/products");
    }

}
