package ar.edu.itba.paw.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.itba.paw.interfaces.services.RatingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ratings")
public class RatingsController extends SimpleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingsController.class);

    private final RatingsService ratingsService;

    @Autowired
    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @GetMapping("/")
    public ModelAndView getHotelRatings() {
        final ModelAndView mav = new ModelAndView();
        mav.addObject(ratingsService.getHotelRating());
        return mav;
    }

    @GetMapping("/all")
    public ModelAndView getAllHotelRatings() {
        final ModelAndView mav = new ModelAndView();
        mav.addObject(ratingsService.getAllHotelRatings());
        return mav;
    }

    @GetMapping("/rooms")
    public ModelAndView getRoomRating(@RequestParam(value = "roomId") long roomId) {
        final ModelAndView mav = new ModelAndView();
        mav.addObject(ratingsService.getRoomRating(roomId));
        return mav;
    }

    @GetMapping("/rooms/all")
    public ModelAndView getRoomRatings(@RequestParam(value = "roomId") long roomId) {
        final ModelAndView mav = new ModelAndView();
        mav.addObject(ratingsService.getAllRoomRatings(roomId));
        return mav;
    }
}
