package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.EntityNotFoundException;
import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.ChargeService;
import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.dtos.CheckoutDTO;
import ar.edu.itba.paw.models.occupant.Occupant;
import form.CheckinForm;
import form.CheckoutForm;
import form.RegistrationForm;
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
import java.util.ArrayList;
import java.util.List;

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
        mav.addObject("ReservationsList", reservationService.getRoomsReservedActive());
        return mav;
    }

    @PostMapping("/reservationPost")
    @Transactional
    public ModelAndView reservationPost(@ModelAttribute("reservationForm") final ReservationForm form) throws RequestInvalidException, ParseException {
        final ModelAndView mav = new ModelAndView("reservationPost");
        LOGGER.debug("Request received to do a reservation on room with id: " + form.getRoomId());
        mav.addObject("reserva", reservationService.doReservation(form.getRoomId(),
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
                                     @RequestParam(value = "guest", required = false) String guest) throws ParseException {
        final ModelAndView mav = new ModelAndView("reservations");
        mav.addObject("reservations",
                reservationService.findAllBetweenDatesOrEmailAndSurname(
                        startDate == null || startDate.length() == 0 ? null : fromStringToCalendar(startDate),
                        endDate == null || endDate.length() == 0 ? null : fromStringToCalendar(endDate), userEmail, guest)
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

    @GetMapping("/registration")
    public ModelAndView registration(@ModelAttribute("registrationForm") final RegistrationForm form) {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("registered", false);
        return mav;
    }

    @PostMapping("/registrationPost")
    public ModelAndView registrationPost(@ModelAttribute("registrationForm") final RegistrationForm form) throws EntityNotFoundException {
        ModelAndView mav = new ModelAndView("registration");
        LOGGER.debug("Attempted to access registration form");
        if(form != null) {
            LOGGER.debug("Attempted to register occupants on reservation hash " + form.getReservation_hash());
            reservationService.registerOccupants(form.getReservation_hash().trim(), getListOfOccupantsFromForm(form));
            mav.addObject("registered", true);
        }
        return mav;
    }

    // FIXME this is disgusting but we couldnt crate a list from jsp
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
