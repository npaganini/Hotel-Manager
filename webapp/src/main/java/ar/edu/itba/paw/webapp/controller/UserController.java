package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.dtos.ActiveReservationResponse;
import ar.edu.itba.paw.interfaces.dtos.ChargesByUserResponse;
import ar.edu.itba.paw.interfaces.dtos.ProductResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.MessageSourceExternalizer;
import ar.edu.itba.paw.interfaces.services.UserService;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.help.Help;
import ar.edu.itba.paw.webapp.dtos.ActiveReservationsResponse;
import ar.edu.itba.paw.webapp.dtos.HelpRequest;
import ar.edu.itba.paw.webapp.dtos.RateReservationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Controller
@Path("/user")
public class UserController extends SimpleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    public static final String DEFAULT_FIRST_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "20";

    private final MessageSourceExternalizer messageSourceExternalizer;
    private final UserService userService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public UserController(MessageSourceExternalizer messageSourceExternalizer, UserService userService) {
        this.messageSourceExternalizer = messageSourceExternalizer;
        this.userService = userService;
    }


    @GET
    @Path("/{reservationId}/expenses")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response boughtProducts(@PathParam(value = "reservationId") long reservationId,
                                   @QueryParam("page") @DefaultValue(DEFAULT_FIRST_PAGE) int page,
                                   @QueryParam("limit") @DefaultValue(DEFAULT_PAGE_SIZE) int limit,
                                   @Context SecurityContext securityContext) {
        // todo: mav was "expenses.jsp"
        LOGGER.debug("Request received to retrieve all expenses on reservation with id " + reservationId);
        List<ChargesByUserResponse> chargesByUser = userService.checkProductsPurchasedByUserByReservationId(getUserEmailFromJwt(securityContext), reservationId);
        System.out.println(chargesByUser);
        System.out.println(reservationId);

        return Response.ok(new GenericEntity<List<ChargesByUserResponse>>(chargesByUser) {
        }).build();
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getActiveReservations(@QueryParam("page") @DefaultValue(DEFAULT_FIRST_PAGE) int page,
                                          @QueryParam("limit") @DefaultValue(DEFAULT_PAGE_SIZE) int limit,
                                          @Context SecurityContext securityContext) {
        List<ActiveReservationResponse> activeReservations = userService.findActiveReservations(getUserEmailFromJwt(securityContext));
        return Response.ok(new ActiveReservationsResponse(activeReservations)).build();
    }

    @GET
    @Path("/{reservationId}/products")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getAllProducts(@PathParam("reservationId") long reservationId,
                                   @QueryParam("page") @DefaultValue(DEFAULT_FIRST_PAGE) int page,
                                   @QueryParam("limit") @DefaultValue(DEFAULT_PAGE_SIZE) int limit) {
        // todo: mav was "browseProducts.jsp"
        LOGGER.debug("Request received to retrieve all products list");
        PaginatedDTO<ProductResponse> productList;
        try {
            productList = userService.getProducts(page, limit);
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return sendPaginatedResponse(page, limit, productList.getMaxItems(), new GenericEntity<List<ProductResponse>>(productList.getList()) {
        }, uriInfo.getAbsolutePathBuilder());
    }

    @POST
    @Path("/{reservationId}/products/{productId}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response buyProduct(@PathParam("reservationId") long reservationId, @PathParam("productId") Long productId) throws EntityNotFoundException {
        LOGGER.debug("Request received to buy products on reservation with id " + reservationId);
        if (productId != null) {
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
    public Response requestHelp(@PathParam("reservationId") long reservationId,
                                @RequestBody HelpRequest helpRequest) throws EntityNotFoundException {
        LOGGER.debug("Help request made on reservation with id " + reservationId);
        if (helpRequest.getHelpDescription() != null) {
            // todo: mav was "requestHelp.jsp"
            System.out.println("id ---> " + reservationId);

            System.out.println("help ---> " + helpRequest.getHelpDescription());
            Help helpRequested = userService.requestHelp(helpRequest.getHelpDescription(), reservationId);
            // do something with this help object
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/ratings/{reservationHash}/rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public void rateStay(@PathParam("reservationHash") String reservationHash,
                         @QueryParam("rate") RateReservationRequest rateRequest) {
        try {
            userService.rateStay(rateRequest.getRate(), reservationHash);
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
        }
    }
}
