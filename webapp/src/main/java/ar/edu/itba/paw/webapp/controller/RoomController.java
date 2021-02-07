package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.dtos.ReservationResponse;
import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.webapp.dtos.OccupantsRequest;
import ar.edu.itba.paw.webapp.dtos.ReservationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Controller
@Path("/rooms")
public class RoomController extends SimpleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    private final RoomService roomService;
    private final ReservationService reservationService;
    private final ChargeService chargeService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public RoomController(RoomService roomService, ReservationService reservationService, ChargeService chargeService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.chargeService = chargeService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Transactional
    public Response getAllRooms() {
        LOGGER.debug("Request received to retrieve whole roomsList");
        final List<ReservationResponse> reservations = reservationService.getRoomsReservedActive();

        return Response.ok(new GenericEntity<List<ReservationResponse>>(reservations) {
        }).build();
    }

    @POST
    @Path("/reservation")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response reservationPost(ReservationRequest reservationRequest)
            throws RequestInvalidException {
        LOGGER.debug("Request received to do a reservation on room with id: " + reservationRequest.getRoomId());
        final Reservation reservation = reservationService.doReservation(reservationRequest.getRoomId(),
                reservationRequest.getUserEmail(), reservationRequest.getStartDate(), reservationRequest.getEndDate());
        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(reservation.getId())).build();
        // FIXME habría que devolver el id de la reservation o un par de cosas mas
        return Response.created(uri).build();
    }

    @POST
    @Path("/checkin/{reservationId}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response checkinPost(@PathParam("reservationId") final String reservationId) throws RequestInvalidException,
            EntityNotFoundException {
        LOGGER.debug("Request received to do the check-in on reservation with hash: " + reservationId);
        return Response.ok(roomService.doCheckin(reservationId)).build();
    }

    @POST
    @Path("/checkout/{reservationId}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response checkoutPost(@PathParam(value = "reservationId") final String reservationId) throws RequestInvalidException, EntityNotFoundException {
        CheckoutDTO checkoutDTO = roomService.doCheckout(reservationId);
        // TODO: make front end show total to pay
        return Response.ok(checkoutDTO.getCharges()).build();
    }

    @GET
    @Path("/free")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response reservation(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) throws ParseException {
        if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)
                && LocalDate.parse(startDate).isBefore(LocalDate.parse(endDate))) {
            return Response
                    .ok(roomService.findAllFreeBetweenDates(fromStringToCalendar(startDate), fromStringToCalendar(endDate)))
                    .build();
        }
        String message = "Expected 'startDate' and 'endDate' in format yyyy-mm-dd.";
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }

    @POST
    @Path("/orders/{roomId}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response sendOrder(@PathParam(value = "roomId") Long roomId) throws Exception {
        LOGGER.debug("Order request sent for room with id: " + roomId);
        chargeService.setChargesToDelivered(roomId);
        return Response.ok().build();
    }

    @POST
    @Path("/occupants/{reservationHash}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response registrationPost(@PathParam("reservationHash") String reservationHash, OccupantsRequest occupantsRequest) throws EntityNotFoundException {
        LOGGER.debug("Attempted to access registration form");
        if (reservationHash != null && !CollectionUtils.isEmpty(occupantsRequest.getOccupants())) {
            LOGGER.debug("Attempted to register occupants on reservation hash " + reservationHash);
            reservationService.registerOccupants(reservationHash.trim(),
                    occupantsRequest.getOccupants()
                            .stream()
                            .map(occupantR -> new Occupant(occupantR.getName(), occupantR.getLastname()))
                            .collect(Collectors.toList()));

            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
