package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.reservation.Reservation;
import form.CheckinForm;
import form.CheckoutForm;
import form.ReservationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    public RoomController(RoomService roomService, ReservationService reservationService, ChargeService chargeService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.chargeService = chargeService;
    }

    @GetMapping("/home")
    public ModelAndView getAllRooms() {
        final ModelAndView mav = new ModelAndView("index");
        LOGGER.debug("Request received to retrieve whole roomsList");
        mav.addObject("RoomList", roomService.getRoomsReservedActive());
        return mav;
    }

    @GetMapping("/room/{id}")
    public ModelAndView getRoom(@PathVariable long id) {
        final ModelAndView mav = new ModelAndView("room");
        mav.addObject("RoomSelected", roomService.getRoom(id));
        return mav;
    }

    @PostMapping("/reservationPost")
    @Transactional
    public ModelAndView reservationPost(@ModelAttribute("reservationForm") final ReservationForm form) throws EntityNotFoundException, RequestInvalidException {
        final ModelAndView mav = new ModelAndView("reservationPost");
        LOGGER.debug("Request received to do a reservation on room with id: " + form.getRoomId());
        mav.addObject("reserva", roomService.doReservation(form.getRoomId(),
                form.getUserEmail(), Date.valueOf(form.getStartDate()).toLocalDate(),
                Date.valueOf(form.getEndDate()).toLocalDate()));
        return mav;
    }

    @GetMapping("/checkin")
    public ModelAndView chackin(@ModelAttribute("checkinForm") final CheckinForm form) {
        return new ModelAndView("checkin");
    }

    @PostMapping("/checkinPost")
    @Transactional
    public ModelAndView checkinPost(@ModelAttribute("checkinForm") final CheckinForm form) throws RequestInvalidException, EntityNotFoundException {
        final ModelAndView mav = new ModelAndView("checkinPost");
        LOGGER.debug("Request received to do the check-in on reservation with hash: " + form.getId_reservation());
        Reservation reservation = reservationService.getReservationByHash(form.getId_reservation());
        if (reservation.isActive()) {
            throw new RequestInvalidException();
        }
        roomService.reserveRoom(reservation.getRoom().getId(), reservation);
        reservationService.activeReservation(reservation.getId());
        return mav;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(@ModelAttribute("checkoutForm") final CheckoutForm form) {
        return new ModelAndView("checkout");
    }

    @PostMapping("/checkoutPost")
    @Transactional
    public ModelAndView checkoutPost(@ModelAttribute("checkoutForm") final CheckoutForm form) throws RequestInvalidException, EntityNotFoundException {
        final ModelAndView mav = new ModelAndView("checkoutPost");
        Reservation reservation = reservationService.getReservationByHash(form.getId_reservation().trim());
        if (!reservation.isActive()) {
            throw new RequestInvalidException();
        }
        LOGGER.debug("Request received to do the check-out on reservation with hash: " + form.getId_reservation());
        mav.addObject("charges", chargeService.getAllChargesByReservationId(reservation.getId()));
        mav.addObject("totalCharge", chargeService.sumCharge(reservation.getId()));
        roomService.freeRoom(reservation.getRoom().getId());
        reservationService.inactiveReservation(reservation.getId());
        return mav;
    }

    @GetMapping("/reservation")
    public ModelAndView reservation(@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                    @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                                    @ModelAttribute("reservationForm") final ReservationForm form) throws RequestInvalidException {
        final ModelAndView mav = new ModelAndView("reservation");
        mav.addObject("allRooms", roomService.findAllFreeBetweenDates(startDate, endDate));
        return mav;
    }


    @GetMapping("/reservations")
    public ModelAndView reservations(@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                                     @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                                     @RequestParam(value = "userEmail", required = false, defaultValue = "") String userEmail) throws RequestInvalidException {
        final ModelAndView mav = new ModelAndView("reservations");
        mav.addObject("reservations", roomService.findAllBetweenDatesAndEmail(startDate,
                endDate, userEmail));
        return mav;
    }

    @GetMapping("/orders")
    public ModelAndView orders() {
        final ModelAndView mav = new ModelAndView("orders");
        mav.addObject("orders", chargeService.getAllChargesNotDelivered());
        return mav;
    }

    @GetMapping("/orders/sendOrder")
    public ModelAndView sendOrder(@RequestParam(value = "chargeId", required = false) long chargeId) throws Exception {
        final ModelAndView mav = new ModelAndView("orderFinished");
        chargeService.setChargeToDelivered(chargeId);
        return mav;
    }

}
