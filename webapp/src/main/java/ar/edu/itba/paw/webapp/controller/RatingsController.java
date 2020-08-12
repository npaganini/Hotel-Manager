package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.itba.paw.interfaces.services.RatingsService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Controller
@RequestMapping("/ratings")
public class RatingsController extends SimpleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingsController.class);

    private final RatingsService ratingsService;

    @Autowired
    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @GET
    @Path("/")
    public Response getHotelRatings() {
        return Response.ok(ratingsService.getHotelRating()).build();
    }

    @GET
    @Path("/hotel")
    public Response getAllHotelRatings() {
        return Response.ok(ratingsService.getAllHotelRatings()).build();
    }

    @GET
    @Path("/rooms/{id}")
    public Response getRoomRating(@PathParam(value = "id") long roomId) {
        return Response.ok(ratingsService.getRoomRating(roomId)).build();
    }

    @GET
    @Path("/rooms")
    public Response getRoomRatings(@RequestParam(value = "roomId") long roomId) {
        return Response.ok(ratingsService.getAllRoomRatings(roomId)).build();
    }
}
