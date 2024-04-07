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

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.deti.tqs.backend.services.ReservationService;
import ua.deti.tqs.backend.services.TripService;
import ua.deti.tqs.backend.models.Reservation;
import ua.deti.tqs.backend.models.Trip;

@CrossOrigin
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    private static final Logger logger = LoggerFactory.getLogger(
    ReservationController.class
    );

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TripService tripService;

    @GetMapping("/{Token}")
    ResponseEntity<Reservation> getReservationByToken(@PathVariable(value = "Token") String Token){
        return ResponseEntity.ok().body(reservationService.findReservationById(Token));
    }

    @PostMapping("/save")
public ResponseEntity<Reservation> saveReservation(@RequestParam int tripId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
    if (firstName == null || lastName == null || email == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    String token = reservationService.generateToken();
    Trip dataParts;
    try {
        dataParts = tripService.findTripById(tripId);
    } catch (EntityNotFoundException e) {
        logger.error("Trip not found for id: {}", tripId, e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    String fromCity = dataParts.getFromCity();
    String toCity = dataParts.getToCity();
    String date = dataParts.getDateTrip();
    String time = dataParts.getTimeTrip();
    Reservation reservation = new Reservation(token, fromCity, toCity, date, time, firstName, lastName, email);
    return ResponseEntity.ok().body(reservationService.saveReservation(reservation));
}

}
