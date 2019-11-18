package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.HelpService;
import ar.edu.itba.paw.interfaces.services.UserService;
import form.BuyProductForm;
import form.HelpForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController extends SimpleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ModelAndView getLandingPage(Authentication authentication) {
        final ModelAndView mav = new ModelAndView("userIndex");
        LOGGER.debug("Request received to user's landing page");
        mav.addObject("ReservationsList",
                userService.findActiveReservations(getUsername(authentication)));
        return mav;
    }

    @GetMapping("/expenses")
    public ModelAndView boughtProducts(Authentication authentication, @RequestParam(value = "reservationId") long reservationId) {
        final ModelAndView mav = new ModelAndView("expenses");
        LOGGER.debug("Request received to retrieve all expenses on reservation with id " + reservationId);
        mav.addObject("ProductsList",
                userService.checkProductsPurchasedByUserByReservationId(getUsername(authentication), reservationId));
        return mav;
    }

    @GetMapping("/products")
    public ModelAndView getAllProducts(@ModelAttribute("buyProductForm") BuyProductForm productForm,
                                       @RequestParam(value = "reservationId") long reservationId) {
        final ModelAndView mav = new ModelAndView("browseProducts");
        LOGGER.debug("Request received to retrieve all products list");
        mav.addObject("ProductsList", userService.getProducts());
        return mav;
    }

    @PostMapping("/buyProducts")
    public ModelAndView buyProduct(@ModelAttribute("buyProductForm") BuyProductForm buyProductForm, @RequestParam(value = "reservationId") long reservationId) {
        LOGGER.debug("Request received to buy products on reservation with id " + reservationId);
        if(buyProductForm != null) {
            final ModelAndView mav = new ModelAndView("buyProducts");
            mav.addObject("charge", userService.addCharge(buyProductForm.getProductId(), reservationId));
            return mav;
        }
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/needHelp")
    public ModelAndView getHelpPage(@ModelAttribute("getHelpForm") HelpForm helpForm,
                                    @RequestParam(value = "reservationId") long reservationId) {
        final ModelAndView mav = new ModelAndView("askHelpPage");
        LOGGER.debug("Request received to get help page");
        return mav;
    }

    @PostMapping("/helpUser")
    public ModelAndView requestHelp(@ModelAttribute("getHelpForm") HelpForm helpForm, @RequestParam(value = "reservationId") long reservationId) {
        LOGGER.debug("Help request made on reservation with id " + reservationId);
        if(helpForm != null) {
            final ModelAndView mav = new ModelAndView("requestHelp");
            mav.addObject("helpRequest", userService.requestHelp(helpForm.getText(), reservationId));
            return mav;
        }
        return new ModelAndView("redirect:/needHelp");
    }
}
