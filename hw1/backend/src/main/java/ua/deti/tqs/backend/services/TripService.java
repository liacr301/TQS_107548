package ua.deti.tqs.backend.services;

import ua.deti.tqs.backend.models.Trip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.deti.tqs.backend.dao.TripRepository;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip findTripById(int id) {
        return tripRepository.findById(id);
    }

    public List<Trip> showAllTrips() {
        return tripRepository.findAll();
    }

    public List<Trip> findTripByFromCityToCityAndDateTrip(String fromCity, String toCity, String dateTrip) {
        System.out.println("fromCity: " + fromCity + " toCity: " + toCity + " dateTrip: " + dateTrip);
        System.out.println(tripRepository.findByFromCityAndToCityAndDateTrip(fromCity, toCity, dateTrip));
        return tripRepository.findByFromCityAndToCityAndDateTrip(fromCity, toCity, dateTrip);
    }
    
}
