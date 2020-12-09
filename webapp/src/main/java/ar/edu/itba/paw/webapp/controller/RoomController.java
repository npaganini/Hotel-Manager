package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.webapp.form.CheckinForm;
import ar.edu.itba.paw.webapp.form.CheckoutForm;
import ar.edu.itba.paw.webapp.form.RegistrationForm;
import ar.edu.itba.paw.webapp.form.ReservationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@Path("rooms")
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
    @Path("/home")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response getAllRooms() {
        LOGGER.debug("Request received to retrieve whole roomsList");
        final List<Reservation> roomsReserved = reservationService.getRoomsReservedActive();
        return Response.ok(roomsReserved).build();
    }

    @POST
    @Path("/reservation")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Transactional
    public Response reservationPost(final ReservationForm form) throws RequestInvalidException, ParseException {
        LOGGER.debug("Request received to do a reservation on room with id: " + form.getRoomId());
        final Reservation reservation = reservationService.doReservation(form.getRoomId(),
                form.getUserEmail(), fromStringToCalendar(form.getStartDate()),
                fromStringToCalendar(form.getEndDate()));
        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(reservation.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/checkin")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response checkin(final CheckinForm form) {
        return Response.ok(form).build();
    }

    @POST
    @Path("/checkin")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Transactional
    public Response checkinPost(final CheckinForm form) throws RequestInvalidException, EntityNotFoundException {
        LOGGER.debug("Request received to do the check-in on reservation with hash: " + form.getId_reservation());
        return Response.ok(roomService.doCheckin(form.getId_reservation())).build();
    }

    @GET
    @Path("/checkout")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response checkout(final CheckoutForm form) {
        return Response.ok(form).build();
    }

    @POST
    @Path("/checkout")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Transactional
    public Response checkoutPost(final CheckoutForm form) throws RequestInvalidException, EntityNotFoundException {
        CheckoutDTO checkoutDTO = roomService.doCheckout(form.getId_reservation());
        // TODO: make front end show total to pay
        return Response.ok(checkoutDTO.getCharges()).build();
    }

    @GET
    @Path("/free")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response reservation(String startDate, String endDate, final ReservationForm form) throws ParseException {
        if (!(startDate == null || endDate == null) && !(startDate.isEmpty() ||
                endDate.isEmpty()) && LocalDate.parse(startDate).isBefore(LocalDate.parse(endDate))) {
            return Response
                .ok(roomService.findAllFreeBetweenDates(fromStringToCalendar(startDate), fromStringToCalendar(endDate)))
                .build();
        }
        String message = "Expected 'startDate' and 'endDate' in format yyyy-mm-dd.";
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }


    @GET
    @Path("/reservation")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response reservations(String startDate, String endDate, String userEmail, String guest) throws ParseException {
        return Response.ok(
                reservationService.findAllBetweenDatesOrEmailAndSurname(
                    startDate == null || startDate.length() == 0 ? null : fromStringToCalendar(startDate),
                    endDate == null || endDate.length() == 0 ? null : fromStringToCalendar(endDate), userEmail, guest)
        ).build();
    }

    @GET
    @Path("/orders")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response orders() {
        return Response.ok(chargeService.getAllChargesNotDelivered()).build();
    }

    @PUT
    @Path("/orders/{roomId}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response sendOrder(@PathParam(value = "roomId") long roomId) throws Exception {
        LOGGER.debug("Order request sent for room with id: " + roomId);
        chargeService.setChargesToDelivered(roomId);
        return Response.ok().build();
    }

    @GET
    @Path("/occupants")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response registration(final RegistrationForm form) {
        return Response.ok(form).build();
    }

    @POST
    @Path("/occupants")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response registrationPost(final RegistrationForm form) throws EntityNotFoundException {
        LOGGER.debug("Attempted to access registration form");
        if(form != null) {
            LOGGER.debug("Attempted to register occupants on reservation hash " + form.getReservation_hash());
            reservationService.registerOccupants(form.getReservation_hash().trim(), getListOfOccupantsFromForm(form));

            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    // FIXME:  this is disgusting but we couldn't create a list from jsp
    private List<Occupant> getListOfOccupantsFromForm(RegistrationForm form) {
        List<Occupant> occupants = new ArrayList<>();
        if (form.getName_1() != null && form.getName_1().length() > 0
                && form.getLast_name_1() != null && form.getLast_name_1().length() > 0)
            occupants.add(new Occupant(form.getName_1(), form.getLast_name_1().toLowerCase()));

        if (form.getName_2() != null && form.getName_2().length() > 0
                && form.getLast_name_2() != null && form.getLast_name_2().length() > 0)
            occupants.add(new Occupant(form.getName_2(), form.getLast_name_2().toLowerCase()));

        if (form.getName_3() != null && form.getName_3().length() > 0
                && form.getLast_name_3() != null && form.getLast_name_3().length() > 0)
            occupants.add(new Occupant(form.getName_3(), form.getLast_name_3().toLowerCase()));

        if (form.getName_4() != null && form.getName_4().length() > 0
                && form.getLast_name_4() != null && form.getLast_name_4().length() > 0)
            occupants.add(new Occupant(form.getName_4(), form.getLast_name_4().toLowerCase()));

        if (form.getName_5() != null && form.getName_5().length() > 0
                && form.getLast_name_5() != null && form.getLast_name_5().length() > 0)
            occupants.add(new Occupant(form.getName_5(), form.getLast_name_5().toLowerCase()));

        if (form.getName_6() != null && form.getName_6().length() > 0
                && form.getLast_name_6() != null && form.getLast_name_6().length() > 0)
            occupants.add(new Occupant(form.getName_6(), form.getLast_name_6().toLowerCase()));

       return occupants;
    }
}
