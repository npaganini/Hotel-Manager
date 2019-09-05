package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.RoomService;
import ar.edu.itba.paw.models.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //@RequestMapping(value = "/rooms", method = RequestMethod.GET)
    @GetMapping("")
    public ModelAndView getAllRooms() {
        final ModelAndView mav = new ModelAndView("index");
        List<Room> r = new ArrayList<Room>();
        Room h1 = new Room(1,100);
        r.add(h1);

        Room h2 = new Room(2,102);
        r.add(h2);

        Room h3 = new Room(3,103);
        r.add(h3);

        Room h4 = new Room(4,104);
        r.add(h4);

        mav.addObject("RoomList",r);
        return mav;
    }

    @GetMapping("/room/{id}")
    public ModelAndView getRoom(@PathVariable  long id) {
        final ModelAndView mav = new ModelAndView("romm");
        mav.addObject("RoomSelected", roomService.getRoom(id));
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
