package ua.deti.tqs.backend;

import ua.deti.tqs.backend.models.Trip;
import ua.deti.tqs.backend.dao.TripRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TripRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TripRepository tripRepository;

    @BeforeEach
    public void resetDatabase() {
        entityManager.clear();
    }

    @Test
    @DisplayName("Test TripRepository Return Trip By Id")
    public void testTripRepositoryReturnTripById() {
     Trip trip = new Trip(120, "Aveiro", "Porto", "2021-03-01", "10:00", 10.0, 50);
        entityManager.persistAndFlush(trip);

        Trip found = tripRepository.findById(trip.getId());
        assertThat(found.getId()).isEqualTo(trip.getId());
    }


    @Test
    @DisplayName("Test TripRepository Return Invalid Trip By Id")
    public void testTripRepositoryReturnInvalidTripById() {
        Trip found = tripRepository.findById(-1);
        assertThat(found).isNull();
    }

    @Test
    @DisplayName("Test TripRepository Return Trip By FromCity, ToCity and Date")
    public void testTripRepositoryReturnTripByFromCityToCityAndDate() {
        Trip trip1 = new Trip(409, "Aveiro", "Lisboa", "2021-03-08", "10:00", 20.0, 50);
        Trip trip2 = new Trip(500, "Aveiro", "Lisboa", "2021-03-08", "12:00", 20.0, 30);
        entityManager.persistAndFlush(trip1);
        entityManager.persistAndFlush(trip2);
        List<Trip> foundTrips = new ArrayList<Trip>();
        foundTrips = tripRepository.findByFromCityAndToCityAndDateTrip("Aveiro", "Lisboa", "2021-03-08");


        assertThat(foundTrips).isNotEmpty();
        assertThat(foundTrips).containsExactlyInAnyOrder(trip1, trip2);

    }

    @Test
    @DisplayName("Test TripRepository Return Invalid Trip By FromCity, ToCity and Date")
    public void testTripRepositoryReturnInvalidTripByFromCityToCityAndDate() {
        List<Trip> foundTrips = new ArrayList<Trip>();
        foundTrips = tripRepository.findByFromCityAndToCityAndDateTrip("Aveiro", "Viseu", "2021-03-08");

        assertThat(foundTrips).isEmpty();
    }

}
