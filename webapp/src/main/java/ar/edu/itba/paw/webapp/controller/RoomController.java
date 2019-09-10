package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.RoomService;
import ar.edu.itba.paw.models.room.Room;
import ar.edu.itba.paw.models.room.RoomType;
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

    @GetMapping("")
    public ModelAndView getAllRooms() {
        final ModelAndView mav = new ModelAndView("index");
        List<Room> r = new ArrayList<Room>();
        Room h1 = new Room(RoomType.SIMPLE,100);
        r.add(h1);

        Room h2 = new Room(RoomType.DOUBLE,102);
        r.add(h2);

        Room h3 = new Room(RoomType.DOUBLE,103);
        r.add(h3);

        Room h4 = new Room(RoomType.TRIPLE,104);
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
