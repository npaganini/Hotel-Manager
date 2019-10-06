package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.ReservationService;
import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.reservation.Reservation;
import form.CheckinForm;
import form.CheckoutForm;
import form.ReservationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
@RequestMapping("rooms")
public class RoomController {
    private final RoomService roomService;
    private final ReservationService reservationService;

    @Autowired
    public RoomController(RoomService roomService,ReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping("/home")
    public ModelAndView getAllRooms() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("RoomList", roomService.getRoomsList());
        return mav;
    }

    @GetMapping("/room/{id}")
    public ModelAndView getRoom(@PathVariable long id) {
        final ModelAndView mav = new ModelAndView("room");
        mav.addObject("RoomSelected", roomService.getRoom(id));
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
        Reservation reserva = new Reservation(form.getRoomId(),
                form.getUserEmail(), Date.valueOf(form.getStartDate()).toLocalDate(),
                Date.valueOf(form.getEndDate()).toLocalDate(), 0L);
        roomService.doReservation(reserva);
        mav.addObject("reserva", reserva);
        return mav;
    }

    @GetMapping("/checkin")
    public ModelAndView chackin(@ModelAttribute("checkinForm") final CheckinForm form){
        final ModelAndView mav = new ModelAndView("checkin");
        return  mav;
    }

    @PostMapping("/checkinPost")
    public ModelAndView checkinPost(@ModelAttribute("checkinForm") final CheckinForm form){
        final ModelAndView mav = new ModelAndView("checkinPost");
        Reservation reser = reservationService.getReservationByHash(form.getId_reservation());
        roomService.reservateRoom(reser.getRoomId());
        reservationService.activeReservation(reservationService.getReservationByHash(form.getId_reservation()).getId());
        return mav;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(@ModelAttribute("checkoutForm") final CheckoutForm form) {
        final ModelAndView mav = new ModelAndView("checkout");
        return mav;
    }

    @PostMapping("/checkoutPost")
    public ModelAndView checkoutPost(@ModelAttribute("checkoutForm") final CheckoutForm form){
        final ModelAndView mav = new ModelAndView("checkoutPost");
        mav.addObject("charges",roomService.getRoomsList());
        roomService.freeRoom(reservationService.getReservationByHash(form.getId_reservation()).getRoomId());
        reservationService.inactiveReservation(reservationService.getReservationByHash(form.getId_reservation()).getId());
        return mav;
    }


//    @GetMapping("/rooms/reservations")
//    public ModelAndView reservations(@ModelAttribute("reservationFilter") final ReservationFilter form) {
//        final ModelAndView mav = new ModelAndView("reservations");
//        mav.addObject("reservations",reservationService.getAll());
//        return mav;
//    }
//
//    @PostMapping("/rooms/reservations")
//    public ModelAndView reservationsPost(@ModelAttribute("reservationFilter") final ReservationFilter form) {
//        final ModelAndView mav = new ModelAndView("reservations");
//        return mav;
//    }
}
