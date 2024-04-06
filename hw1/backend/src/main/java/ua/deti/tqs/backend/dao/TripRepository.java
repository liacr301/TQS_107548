package ua.deti.tqs.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.deti.tqs.backend.models.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    Trip findById(int id);
    List<Trip> findByFromCityAndToCityAndDateTrip(String fromCity, String toCity, String date_trip);
}
