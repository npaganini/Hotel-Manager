package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
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

import java.text.ParseException;
import java.time.LocalDate;

@Controller
@RequestMapping("rooms")
public class RoomController extends SimpleController {

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
        mav.addObject("ReservationsList", roomService.getRoomsReservedActive());
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
    public ModelAndView reservationPost(@ModelAttribute("reservationForm") final ReservationForm form) throws RequestInvalidException, ParseException {
        final ModelAndView mav = new ModelAndView("reservationPost");
        LOGGER.debug("Request received to do a reservation on room with id: " + form.getRoomId());
        mav.addObject("reserva", roomService.doReservation(form.getRoomId(),
                form.getUserEmail(), fromStringToCalendar(form.getStartDate()),
                fromStringToCalendar(form.getEndDate())));
        return mav;
    }

    @GetMapping("/checkin")
    public ModelAndView checkin(@ModelAttribute("checkinForm") final CheckinForm form) {
        return new ModelAndView("checkin");
    }

    @PostMapping("/checkinPost")
    @Transactional
    public ModelAndView checkinPost(@ModelAttribute("checkinForm") final CheckinForm form) throws RequestInvalidException, EntityNotFoundException {
        final ModelAndView mav = new ModelAndView("checkinPost");
        LOGGER.debug("Request received to do the check-in on reservation with hash: " + form.getId_reservation());
        roomService.doCheckin(form.getId_reservation());
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
        CheckoutDTO checkoutDTO = roomService.doCheckout(form.getId_reservation());

        mav.addObject("charges", checkoutDTO.getCharges());
        mav.addObject("totalCharge", checkoutDTO.getSumCharges());
        return mav;
    }

    @GetMapping("/reservation")
    public ModelAndView reservation(@RequestParam(value = "startDate", required = false) String startDate,
                                    @RequestParam(value = "endDate", required = false) String endDate,
                                    @ModelAttribute("reservationForm") final ReservationForm form) throws ParseException {
        final ModelAndView mav = new ModelAndView("reservation");
        if (!(startDate == null || endDate == null) && !(startDate.isEmpty() ||
                endDate.isEmpty()) && LocalDate.parse(startDate).isBefore(LocalDate.parse(endDate))) {
            mav.addObject("allRooms", roomService.findAllFreeBetweenDates(
                    fromStringToCalendar(startDate), fromStringToCalendar(endDate))
            );
            mav.addObject("ListState","visible");
        }
        else
            mav.addObject("ListState","hidden");
        return mav;
    }


    @GetMapping("/reservations")
    public ModelAndView reservations(@RequestParam(value = "startDate", required = false) String startDate,
                                     @RequestParam(value = "endDate", required = false) String endDate,
                                     @RequestParam(value = "userEmail", required = false) String userEmail,
                                     @RequestParam(value = "guest",required = false) String guest) throws ParseException {
        final ModelAndView mav = new ModelAndView("reservations");
        mav.addObject("reservations",
                roomService.findAllBetweenDatesAndEmail(
                        startDate == null || startDate.length() == 0 ? null : fromStringToCalendar(startDate),
                        endDate == null || endDate.length() == 0 ? null : fromStringToCalendar(endDate), userEmail)
        );
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
