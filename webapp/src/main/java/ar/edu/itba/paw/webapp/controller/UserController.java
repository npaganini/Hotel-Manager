package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.help.Help;
import ar.edu.itba.paw.webapp.form.BuyProductForm;
import ar.edu.itba.paw.webapp.form.HelpForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

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
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response redirectToLanding(Authentication authentication) {
        final URI uri = uriInfo.getAbsolutePathBuilder().path("/home").build();
        return Response.temporaryRedirect(uri).build();
    }

    @GET
    @Path("/home")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getLandingPage(Authentication authentication) {
        // todo: mav was "userIndex.jsp"
        LOGGER.debug("Request received to user's landing page");
        return Response.ok(userService.findActiveReservations(getUsername(authentication))).build();
    }

    @GET
    @Path("/expenses/{reservationId}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response boughtProducts(Authentication authentication, @PathParam(value = "reservationId") long reservationId) {
        // todo: mav was "expenses.jsp"
        LOGGER.debug("Request received to retrieve all expenses on reservation with id " + reservationId);
        return Response.ok(userService.checkProductsPurchasedByUserByReservationId(getUsername(authentication), reservationId)).build();
    }

    @GET
    @Path("/products")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getAllProducts(BuyProductForm productForm, long reservationId) {
        // todo: mav was "browseProducts.jsp"
        LOGGER.debug("Request received to retrieve all products list");
        return Response.ok(userService.getProducts()).build();
    }

    @POST
    @Path("/products")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response buyProduct(BuyProductForm buyProductForm, long reservationId) throws EntityNotFoundException {
        LOGGER.debug("Request received to buy products on reservation with id " + reservationId);
        if(buyProductForm != null) {
            // todo: mav was "buyProducts.jsp"
            Charge charge = userService.addCharge(buyProductForm.getProductId(), reservationId);
            URI uri = uriInfo.getAbsolutePathBuilder().path("/" + charge.getId()).build();
            return Response.created(uri).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/help")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getHelpPage(HelpForm helpForm, long reservationId) {
        // todo: mav was "askHelpPage.jsp"
        LOGGER.debug("Request received to get help page");
        return Response.ok(helpForm).build();
    }

    @POST
    @Path("/help")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response requestHelp(HelpForm helpForm, long reservationId) throws EntityNotFoundException {
        LOGGER.debug("Help request made on reservation with id " + reservationId);
        if(helpForm != null) {
            // todo: mav was "requestHelp.jsp"
            Help helpRequested = userService.requestHelp(helpForm.getText(), reservationId);
            URI uri = uriInfo.getAbsolutePathBuilder().path("/" + helpRequested.getId()).build();
            return Response.created(uri).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/ratings/{hash}/rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response rateStay(String rate, @PathParam("hash") String hash) throws RequestInvalidException, EntityNotFoundException {
        // todo: mav was "thanksMessage.jsp"
        userService.rateStay(rate, hash);
        return Response.ok().build();
    }
}
