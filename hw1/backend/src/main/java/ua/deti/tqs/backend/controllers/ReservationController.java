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

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/{token}")
    ResponseEntity<Reservation> getReservationByToken(@PathVariable(value = "token") String token){
        return ResponseEntity.ok().body(reservationService.findReservationByToken(token));
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveReservation(@RequestParam int tripId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        if (firstName == null || lastName == null || email == null || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        String token = reservationService.generateToken();
        Trip dataParts;
        try {
            dataParts = tripService.findTripById(tripId);
            if (dataParts.getAvailableSeats() <= 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "No available seats"));
            }
            dataParts.setAvailableSeats(dataParts.getAvailableSeats() - 1);
            tripService.saveTrip(dataParts);
        } catch (EntityNotFoundException e) {
            logger.error("Trip not found for id: {}", tripId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        Reservation reservation = new Reservation(token, dataParts.getFromCity(), dataParts.getToCity(), dataParts.getDateTrip(), dataParts.getTimeTrip(), firstName, lastName, email);
        Reservation savedReservation = reservationService.saveReservation(reservation);
        
        

        Map<String, Object> response = new HashMap<>();
        response.put("reservation", savedReservation);
        response.put("token", token);
        
        return ResponseEntity.ok().body(response);
    }
}
