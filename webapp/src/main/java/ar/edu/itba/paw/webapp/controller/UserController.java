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
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("RoomList", userService.checkAllExpenses());
        return mav;
    }

    @GetMapping("/tours")
    public ModelAndView getAllTours() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("RoomList", userService.checkAllExpenses());
        return mav;
    }

    @GetMapping("/products")
    public ModelAndView getAllProducts() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("RoomList", userService.checkAllExpenses());
        return mav;
    }

//    @PostMapping("/checkin")
//    public void checkIn(long reservationID) {
//        // TODO: mark room as in-use
//    }
//
//    @PostMapping("/checkout")
//    public void checkOut(int roomNumber) {
//        // TODO: mark room as free if it's occupied by current user
//    }
//
//    @PostMapping("/checkout")
//    public void checkOut() {
//        // TODO: mark in-use room as free
//    }
}
