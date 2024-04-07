package ua.deti.tqs.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.deti.tqs.backend.services.ReservationService;
import ua.deti.tqs.backend.models.Reservation;


@CrossOrigin
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    private static final Logger logger = LoggerFactory.getLogger(
    ReservationController.class
    );

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{Token}")
    ResponseEntity<Reservation> getReservationByToken(@PathVariable(value = "Token") String Token){
        return ResponseEntity.ok().body(reservationService.findReservationById(Token));
    }

    @PostMapping("/save")
    public ResponseEntity<Reservation> saveReservation(@RequestParam String fromCity, @RequestParam String toCity, @RequestParam String date, @RequestParam String time, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        if (fromCity == null || toCity == null || date == null || time == null || firstName == null || lastName == null || email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (fromCity.isEmpty() || toCity.isEmpty() || date.isEmpty() || time.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String Token = reservationService.generateToken();
        Reservation reservation = new Reservation(0, Token, fromCity, toCity, date, time, firstName, lastName, email);
        return ResponseEntity.ok().body(reservationService.saveReservation(reservation));
    }
}
