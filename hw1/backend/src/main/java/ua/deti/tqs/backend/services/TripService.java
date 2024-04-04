package ua.deti.tqs.backend.services;

import ua.deti.tqs.backend.models.Trip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.deti.tqs.backend.dao.TripRepository;

public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip findTripById(int id) {
        return tripRepository.findById(id);
    }

    public List<Trip> findTripByFromCityToCityAndDate(String fromCity, String toCity, String date) {
        return tripRepository.findByFromCityAndToCityAndDate(fromCity, toCity, date);
    }
    
}
