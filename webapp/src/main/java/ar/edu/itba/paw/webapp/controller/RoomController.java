package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public ModelAndView getAllRooms() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("RoomList", roomService.getRoomsList());
        return mav;
    }

    @GetMapping("/room")
    public ModelAndView getRoom(@RequestParam long roomID) {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("RoomNumber", roomService.getRoom(roomID));
        return mav;
    }

    @PostMapping("/checkin")
    public void checkIn(long roomID, Calendar startDate, Calendar endDate) {
        // do mark room as in-use
    }

    @PostMapping("/checkout")
    public void checkOut(long roomID) {
        // do mark room as in-use
    }
}
