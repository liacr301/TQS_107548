package ua.deti.tqs.backend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.when;


import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ua.deti.tqs.backend.models.Reservation;
import ua.deti.tqs.backend.models.Trip;
import ua.deti.tqs.backend.dao.ReservationRepository;
import ua.deti.tqs.backend.dao.TripRepository;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    
    @Mock(lenient = true)
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;
    
    @Mock
    private TripRepository tripRepository;

    @Test
    public void testSaveReservation() {
        Reservation reservation = new Reservation("token123", "Aveiro", "Porto", "2021-03-01", "10:00", "John", "Doe", "john.doe@example.com");
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedReservation = reservationService.saveReservation(reservation);

        assertEquals("token123", savedReservation.getToken());
        assertEquals("Aveiro", savedReservation.getFromCity());
        assertEquals("Porto", savedReservation.getToCity());
        assertEquals("2021-03-01", savedReservation.getDateTrip());
        assertEquals("10:00", savedReservation.getTimeTrip());
        assertEquals("John", savedReservation.getFirstName());
        assertEquals("Doe", savedReservation.getLastName());
        assertEquals("john.doe@example.com", savedReservation.getEmail());
        assertNotNull(savedReservation.getId());
    }

    @Test
    public void testFindReservationById() {
        String token = "token123";
        Reservation reservation = new Reservation(token, "Aveiro", "Porto", "2021-03-01", "10:00", "John", "Doe", "john.doe@example.com");
        when(reservationRepository.findByToken(token)).thenReturn(reservation);

        Reservation foundReservation = reservationService.findReservationByToken(token);

        assertEquals(token, foundReservation.getToken());
        assertEquals("Aveiro", foundReservation.getFromCity());
        assertEquals("Porto", foundReservation.getToCity());
        assertEquals("2021-03-01", foundReservation.getDateTrip());
        assertEquals("10:00", foundReservation.getTimeTrip());
        assertEquals("John", foundReservation.getFirstName());
        assertEquals("Doe", foundReservation.getLastName());
        assertEquals("john.doe@example.com", foundReservation.getEmail());
        assertNotNull(foundReservation.getId());
    }

    @Test
    public void testGenerateToken() {
        String token = reservationService.generateToken();

        assertEquals(32, token.length());
        assertNotNull(token);
    }

}
