package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
import ar.edu.itba.paw.models.occupant.Occupant;
import ar.edu.itba.paw.models.reservation.Reservation;
import ar.edu.itba.paw.webapp.dto.ReservationDTO;
import ar.edu.itba.paw.webapp.form.CheckinForm;
import ar.edu.itba.paw.webapp.form.CheckoutForm;
import ar.edu.itba.paw.webapp.form.RegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
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
    @Produces(value = {MediaType.APPLICATION_XML})
    public Response getAllRooms() {
        LOGGER.debug("Request received to retrieve whole roomsList");
        final List<ReservationDTO> roomsReserved = reservationService.getRoomsReservedActive()
                .stream().map(ReservationDTO::fromReservation).collect(Collectors.toList());
        return Response.ok(new GenericEntity<List<ReservationDTO>>(roomsReserved) {}).build();
//        List<String> l = new LinkedList<>();
//        l.add("pepito1");
//        l.add("pepito2");
//        GenericEntity<List<String>> entity = new GenericEntity<List<String>>(l) {};
//        return Response.ok(entity).build();
    }

    @POST
    @Path("/reservation")
    @Produces(value = {MediaType.APPLICATION_JSON})
//    @Transactional
    public Response reservationPost(@FormParam("startDate") final String startDate,
                                    @FormParam("endDate") final String endDate,
                                    @FormParam("userEmail") final String userEmail,
                                    @FormParam("roomId") final Long roomId)
            throws RequestInvalidException, ParseException {
        LOGGER.debug("Request received to do a reservation on room with id: " + roomId);
        final Reservation reservation = reservationService.doReservation(roomId,
                userEmail, fromStringToCalendar(startDate), fromStringToCalendar(endDate));
        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(reservation.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/checkin")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response checkin() {
        return Response.ok(new CheckinForm()).build();
    }

    @POST
    @Path("/checkin")
    @Produces(value = {MediaType.APPLICATION_JSON})
//    @Transactional
    public Response checkinPost(@FormParam("reservationId") final String reservationId) throws RequestInvalidException,
            EntityNotFoundException {
        LOGGER.debug("Request received to do the check-in on reservation with hash: " + reservationId);
        return Response.ok(roomService.doCheckin(reservationId)).build();
    }

    @GET
    @Path("/checkout")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response checkout() {
        return Response.ok(new CheckoutForm()).build();
    }

    @POST
    @Path("/checkout")
    @Produces(value = {MediaType.APPLICATION_JSON})
//    @Transactional
    public Response checkoutPost(@FormParam("reservationId") final String reservationId) throws RequestInvalidException, EntityNotFoundException {
        CheckoutDTO checkoutDTO = roomService.doCheckout(reservationId);
        // TODO: make front end show total to pay
        return Response.ok(checkoutDTO.getCharges()).build();
    }

    @GET
    @Path("/free")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response reservation(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) throws ParseException {
        if (!(startDate == null || endDate == null) && !(startDate.isEmpty() ||
                endDate.isEmpty()) && LocalDate.parse(startDate).isBefore(LocalDate.parse(endDate))) {
            return Response
                .ok(roomService.findAllFreeBetweenDates(fromStringToCalendar(startDate), fromStringToCalendar(endDate))
                        // todo: add ReservationForm to send
                ).build();
        }
        String message = "Expected 'startDate' and 'endDate' in format yyyy-mm-dd.";
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }

//    @GET
//    @Path("/reservation")
//    @Produces(value = {MediaType.APPLICATION_JSON})
//    public Response reservations(@FormParam("startDate") String startDate,
//                                 @FormParam("endDate") String endDate,
//                                 @FormParam("userEmail") String userEmail,
//                                 @FormParam("guest") String guest) throws ParseException {
//        return Response.ok(
//                reservationService.findAllBetweenDatesOrEmailAndSurname(
//                    startDate == null || startDate.length() == 0 ? null : fromStringToCalendar(startDate),
//                    endDate == null || endDate.length() == 0 ? null : fromStringToCalendar(endDate), userEmail, guest)
//        ).build();
//    }

    @GET
    @Path("/orders")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response orders() {
        return Response.ok(chargeService.getAllChargesNotDelivered()).build();
    }

    @PUT
    @Path("/orders/{roomId}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response sendOrder(@PathParam(value = "roomId") Long roomId) throws Exception {
        LOGGER.debug("Order request sent for room with id: " + roomId);
        chargeService.setChargesToDelivered(roomId);
        return Response.ok().build();
    }

    @GET
    @Path("/occupants")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response registration() {
        return Response.ok(new RegistrationForm()).build();
//        return Response.ok().build();
    }

    @POST
    @Path("/occupants")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response registrationPost(@FormParam("reservationHash") String reservationHash, @FormParam("occupantsList") List<String> occupants) throws EntityNotFoundException {
        LOGGER.debug("Attempted to access registration form");
        if(reservationHash != null && occupants != null && !occupants.isEmpty()) {
            LOGGER.debug("Attempted to register occupants on reservation hash " + reservationHash);
            reservationService.registerOccupants(reservationHash.trim(), getListOfOccupantsFromForm(occupants));

            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    private List<Occupant> getListOfOccupantsFromForm(List<String> occupants) {
        List<Occupant> occupantsList = new LinkedList<>();
        for (int i = 0; i < occupants.size(); i++) {
            String name = occupants.get(i);
            i++;
            if (i >= occupants.size()) {
                throw new IndexOutOfBoundsException();
            }
            String lastName = occupants.get(i);
            occupantsList.add(new Occupant(name, lastName));
        }
        return occupantsList;
    }
}
