package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.reservation.Reservation;
import form.ReservationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public ModelAndView getAllRooms() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("RoomList", roomService.getRoomsList());
        return mav;
    }

    @GetMapping("/rooms/room/{id}")
    public ModelAndView getRoom(@PathVariable long id) {
        final ModelAndView mav = new ModelAndView("room");
        mav.addObject("RoomSelected", roomService.getRoom(id));
        return mav;
    }

    @GetMapping("/rooms/checkin")
    public ModelAndView checkIn(@ModelAttribute("reservationForm") final ReservationForm form) {
        final ModelAndView mav = new ModelAndView("checkin");
        mav.addObject("allRooms", roomService.getRoomsList());
        return mav;
    }

    @PostMapping("/rooms/checkinPost")
    public ModelAndView checkInPost(@ModelAttribute("reservationForm") final ReservationForm form) {
        final ModelAndView mav = new ModelAndView("checkinPost");
        Reservation reserva = new Reservation(form.getRoomId(),
                form.getUserEmail(), Date.valueOf(form.getStartDate()).toLocalDate(),
                Date.valueOf(form.getEndDate()).toLocalDate(), 0L);
        roomService.doReservation(reserva);
        mav.addObject("reserva", reserva);
        return mav;
    }

    @PostMapping("/rooms/checkout")
    public void checkOut(long roomID) {
        // do mark room as in-use
    }

}
