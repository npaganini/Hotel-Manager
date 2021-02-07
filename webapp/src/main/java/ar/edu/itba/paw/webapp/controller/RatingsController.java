package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.RatingsService;
import ar.edu.itba.paw.models.reservation.Calification;
import ar.edu.itba.paw.webapp.dtos.RoomRatingsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Path("/ratings")
public class RatingsController extends SimpleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingsController.class);

    private final RatingsService ratingsService;

    @Autowired
    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getHotelRatings() {
        return Response.ok(ratingsService.getHotelRating()).build();
    }

    @GET
    @Path("/hotel")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getAllHotelRatings() {
        return Response.ok(ratingsService.getAllHotelRatings()).build();
    }

    @GET
    @Path("/rooms/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getRoomRating(@PathParam(value = "id") long roomId) {
        return Response.ok(ratingsService.getRoomRating(roomId)).build();
    }

    @GET
    @Path("/rooms/{id}/all")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getRoomRatings(@PathParam(value ="id") long roomId) {
        List<Calification> ratings = ratingsService.getAllRoomRatings(roomId);
        RoomRatingsDto roomRatingsDto = new RoomRatingsDto(ratings.stream().map(Enum::toString).collect(Collectors.toList()));
        return Response.ok(roomRatingsDto).build();
    }
}
