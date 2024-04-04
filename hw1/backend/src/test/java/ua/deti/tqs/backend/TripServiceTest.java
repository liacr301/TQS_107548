package ua.deti.tqs.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import ua.deti.tqs.backend.models.Trip;
import ua.deti.tqs.backend.dao.TripRepository;
import ua.deti.tqs.backend.services.TripService;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    
    @Mock(lenient = true)
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    @Test
    @BeforeEach
    public void setUp(){
        Trip trip1 = new Trip(409, "Aveiro", "Lisboa", "2021-03-08", "10:00", "20.0", "50");
        trip1.setId(123);
        Trip trip2 = new Trip(500, "Porto", "Lisboa", "2021-05-08", "12:00", "30.0", "30");
        Trip trip3 = new Trip(600, "Viseu", "Coimbra", "2021-03-10", "15:00", "20.0", "25");
        Trip trip4 = new Trip(700, "Aveiro", "Lisboa", "2021-03-08", "15:00", "20.0", "30");

        List<Trip> trips_Aveiro_Lisboa = new ArrayList<>(Arrays.asList(trip1, trip4));
        List<Trip> trips_Porto_Lisboa = new ArrayList<>(Arrays.asList(trip2));
        List<Trip> trips_Viseu_Coimbra = new ArrayList<>(Arrays.asList(trip3));

        when(tripRepository.findById(123)).thenReturn(trip1);
        when(tripRepository.findById(9)).thenReturn(null);
        when(tripRepository.findByFromCityAndToCityAndDate("Aveiro", "Lisboa", "2021-03-08")).thenReturn(trips_Aveiro_Lisboa);
        when(tripRepository.findByFromCityAndToCityAndDate("Porto", "Lisboa", "2021-05-08")).thenReturn(trips_Porto_Lisboa);
        when(tripRepository.findByFromCityAndToCityAndDate("Viseu", "Coimbra", "2021-03-10")).thenReturn(trips_Viseu_Coimbra);
    }

    @Test
    void whenSearchValidId_thenTripShouldBeFound() {
        int id = 123;
        Trip found = tripService.findTripById(id);
        assertThat(found.getBus()).isEqualTo(409);
        assertThat(found.getFromCity()).isEqualTo("Aveiro");
        assertThat(found.getToCity()).isEqualTo("Lisboa");
        assertThat(found.getDate()).isEqualTo("2021-03-08");
        assertThat(found.getTime()).isEqualTo("10:00");
        assertThat(found.getPrice()).isEqualTo("20.0");
    }



}
