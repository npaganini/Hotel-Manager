package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.dtos.CalificationResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.services.RatingsService;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.dtos.RatingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Controller
@Path("/api/ratings")
public class RatingsController extends SimpleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingsController.class);
    public static final String DEFAULT_FIRST_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "20";

    private final RatingsService ratingsService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getHotelRatings() {
        RatingDTO hotelRating;
        try {
            hotelRating = ratingsService.getHotelRating();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok(hotelRating).build();
    }

    @GET
    @Path("/hotel")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getAllHotelRatings(@QueryParam("page") @DefaultValue(DEFAULT_FIRST_PAGE) int page,
                                       @QueryParam("limit") @DefaultValue(DEFAULT_PAGE_SIZE) int limit) {
        PaginatedDTO<CalificationResponse> cals;
        try {
            cals = ratingsService.getAllHotelRatings(page, limit);
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return sendPaginatedResponse(page, limit, cals.getMaxItems(), cals.getList(), uriInfo.getAbsolutePathBuilder());
    }

    @GET
    @Path("/rooms/{roomNumber}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getRoomRating(@PathParam(value = "roomNumber") int roomNumber) {
        RatingDTO roomRating;
        try {
            roomRating = ratingsService.getRoomRating(roomNumber);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return Response.ok(roomRating).build();
    }

    @GET
    @Path("/rooms/{roomNumber}/all")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getRoomRatings(@PathParam(value ="roomNumber") int roomNumber,
                                   @QueryParam("page") @DefaultValue(DEFAULT_FIRST_PAGE) int page,
                                   @QueryParam("limit") @DefaultValue(DEFAULT_PAGE_SIZE) int limit) {
        PaginatedDTO<CalificationResponse> ratings;
        try {
            ratings = ratingsService.getAllRoomRatings(roomNumber, page, limit);
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        return sendPaginatedResponse(page, limit, ratings.getMaxItems(), ratings.getList(), uriInfo.getAbsolutePathBuilder());
    }
}
