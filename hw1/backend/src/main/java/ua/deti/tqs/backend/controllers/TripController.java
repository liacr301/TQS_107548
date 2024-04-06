package ua.deti.tqs.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.deti.tqs.backend.services.TripService;
import ua.deti.tqs.backend.models.Trip;

@CrossOrigin
@RestController
@RequestMapping("/api/trips")
public class TripController {
    private static final Logger logger = LoggerFactory.getLogger(
    TripController.class
    );

    @Autowired
    private TripService tripService;


    @GetMapping("/{id}")
    ResponseEntity<Trip> getTripById(@PathVariable(value = "id") int id){
        return ResponseEntity.ok().body(tripService.findTripById(id));
    }

    @GetMapping("/all_for_search")
    public ResponseEntity<List<Trip>> searchTrips(@RequestParam String fromCity, @RequestParam String toCity, @RequestParam String date) {
        return ResponseEntity.ok().body(tripService.findTripByFromCityToCityAndDateTrip(fromCity, toCity, date));
    }    
    

}
