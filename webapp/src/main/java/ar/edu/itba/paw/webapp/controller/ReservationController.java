package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping("/reservation")
    public ResponseEntity<?> getReservation(@RequestParam("hash") String hash) {
        return new ResponseEntity<>(reservationService.getReservationByHash(hash).toString(), HttpStatus.OK);
    }
}
