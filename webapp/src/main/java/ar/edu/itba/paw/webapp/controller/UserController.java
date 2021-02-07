package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.help.Help;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Controller
@Path("/user")
public class UserController extends SimpleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/expenses/{reservationId}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response boughtProducts(@PathParam(value = "reservationId") long reservationId) {
        // todo: mav was "expenses.jsp"
        LOGGER.debug("Request received to retrieve all expenses on reservation with id " + reservationId);
        return Response.ok(userService.checkProductsPurchasedByUserByReservationId(getUserEmailFromJwt(), reservationId)).build();
    }

    // TODO This is bad, this should be in another endpoint
    @GET
    @Path("/{reservationId}/products")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getAllProducts(@PathParam("reservationId") long reservationId) {
        // todo: mav was "browseProducts.jsp"
        LOGGER.debug("Request received to retrieve all products list");
        return Response.ok(userService.getProducts()).build();
    }

    @POST
    @Path("/{reservationId}/products")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response buyProduct(@FormParam("productId") Long productId,
                               @PathParam("reservationId") long reservationId) throws EntityNotFoundException {
        LOGGER.debug("Request received to buy products on reservation with id " + reservationId);
        if(productId != null) {
            // todo: mav was "buyProducts.jsp"
            Charge charge = userService.addCharge(productId, reservationId);
            URI uri = uriInfo.getAbsolutePathBuilder().path("/" + charge.getId()).build();
            return Response.created(uri).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    // TODO FIXME
    @POST
    @Path("/{reservationId}/help")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response requestHelp(@FormParam("helpFormText") String helpForm,
                                @PathParam("reservationId") long reservationId) throws EntityNotFoundException {
        LOGGER.debug("Help request made on reservation with id " + reservationId);
        if(helpForm != null) {
            // todo: mav was "requestHelp.jsp"
            Help helpRequested = userService.requestHelp(helpForm, reservationId);
            URI uri = uriInfo.getAbsolutePathBuilder().path("/" + helpRequested.getId()).build();
            return Response.created(uri).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    // TODO FIXME
    @POST
    @Path("/ratings/{hash}/rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response rateStay(@FormParam("rating") String rate, @PathParam("hash") String hash) throws RequestInvalidException, EntityNotFoundException {
        // todo: mav was "thanksMessage.jsp"
        userService.rateStay(rate, hash);
        return Response.ok().build();
    }
}
