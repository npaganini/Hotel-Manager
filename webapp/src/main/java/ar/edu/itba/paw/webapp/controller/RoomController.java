package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.reservation.Reservation;
import form.CheckinForm;
import form.CheckoutForm;
import form.ReservationFilter;
import form.ReservationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
@RequestMapping("rooms")
public class RoomController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    private final RoomService roomService;
    private final ReservationService reservationService;
    private final ChargeService chargeService;

    @Autowired
    public RoomController(RoomService roomService,ReservationService reservationService,ChargeService chargeService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.chargeService = chargeService;
    }

    @GetMapping("/home")
    public ModelAndView getAllRooms() {
        final ModelAndView mav = new ModelAndView("index");
        LOGGER.debug("Request received to retrieve whole roomsList");
        mav.addObject("RoomList", roomService.getRoomsList());
        return mav;
    }

    @GetMapping("/reservation")
    public ModelAndView reservation(@ModelAttribute("reservationForm") final ReservationForm form) {
        final ModelAndView mav = new ModelAndView("reservation");
        mav.addObject("allRooms", roomService.getRoomsList());
        return mav;
    }

    @PostMapping("/reservationPost")
    public ModelAndView reservationPost(@ModelAttribute("reservationForm") final ReservationForm form) {
        final ModelAndView mav = new ModelAndView("reservationPost");
        LOGGER.debug("Request received to do a reservation on room with id: " + form.getRoomId());
        Reservation reserva = new Reservation(form.getRoomId(),
                form.getUserEmail(), Date.valueOf(form.getStartDate()).toLocalDate(),
                Date.valueOf(form.getEndDate()).toLocalDate(), 0L);
        roomService.doReservation(reserva);
        LOGGER.debug("Response about to be sent, reservation made, with hash: " + reserva.getHash());
        mav.addObject("reserva", reserva);
        return mav;
    }

    @GetMapping("/checkin")
    public ModelAndView checkin(@ModelAttribute("checkinForm") final CheckinForm form){
        return new ModelAndView("checkin");
    }

    @PostMapping("/checkinPost")
    public ModelAndView checkinPost(@ModelAttribute("checkinForm") final CheckinForm form){
        final ModelAndView mav = new ModelAndView("checkinPost");
        LOGGER.debug("Request received to do the check-in on reservation with hash: " + form.getId_reservation());
        Reservation reser = reservationService.getReservationByHash(form.getId_reservation());
        roomService.reservateRoom(reser.getRoomId());
        reservationService.activeReservation(reservationService.getReservationByHash(form.getId_reservation()).getId());
        return mav;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(@ModelAttribute("checkoutForm") final CheckoutForm form) {
        return new ModelAndView("checkout");
    }

    @PostMapping("/checkoutPost")
    public ModelAndView checkoutPost(@ModelAttribute("checkoutForm") final CheckoutForm form){
        final ModelAndView mav = new ModelAndView("checkoutPost");
        LOGGER.debug("Request received to do the check-out on reservation with hash: " + form.getId_reservation());
        mav.addObject("charges",chargeService.getAllChargesByReservationId(reservationService.getReservationByHash(form.getId_reservation()).getId()));
        roomService.freeRoom(reservationService.getReservationByHash(form.getId_reservation()).getRoomId());
        reservationService.inactiveReservation(reservationService.getReservationByHash(form.getId_reservation()).getId());
        return mav;
    }


    @GetMapping("/reservations")
    public ModelAndView reservations(@ModelAttribute("reservationFilter") final ReservationFilter form) {
        final ModelAndView mav = new ModelAndView("reservations");
        LOGGER.debug("Request received to retrieve all reservation");
        mav.addObject("reservations",reservationService.getAll());
        return mav;
    }

    @PostMapping("/reservations")
    public ModelAndView reservationsPost(@ModelAttribute("reservationFilter") final ReservationFilter form) {
        LOGGER.debug("Request received to retrieve all reservation with filters");
        return new ModelAndView("reservations");
    }
}
