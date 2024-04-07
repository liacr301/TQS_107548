package ua.deti.tqs.backend.services;

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

import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ua.deti.tqs.backend.models.Reservation;
import ua.deti.tqs.backend.dao.ReservationRepository;
import ua.deti.tqs.backend.services.ReservationService;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    
    @Mock(lenient = true)
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void testSaveReservation() {
        Reservation reservation = new Reservation(1, "token123", "Aveiro", "Porto", "2021-03-01", "10:00", "John", "Doe", "john.doe@example.com");
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedReservation = reservationService.saveReservation(reservation);

        assertEquals("token123", savedReservation.getToken());
        assertEquals("Aveiro", savedReservation.getFromCity());
        assertEquals("Porto", savedReservation.getToCity());
        assertEquals("2021-03-01", savedReservation.getDateTrip());
        assertEquals("10:00", savedReservation.getTime());
        assertEquals("John", savedReservation.getFirstName());
        assertEquals("Doe", savedReservation.getLastName());
        assertEquals("john.doe@example.com", savedReservation.getEmail());
        assertNotNull(savedReservation.getId());
    }

    @Test
    public void testFindReservationById() {
        String token = "token123";
        Reservation reservation = new Reservation(1, token, "Aveiro", "Porto", "2021-03-01", "10:00", "John", "Doe", "john.doe@example.com");
        when(reservationRepository.findByToken(token)).thenReturn(reservation);

        Reservation foundReservation = reservationService.findReservationById(token);

        assertEquals(token, foundReservation.getToken());
        assertEquals("Aveiro", foundReservation.getFromCity());
        assertEquals("Porto", foundReservation.getToCity());
        assertEquals("2021-03-01", foundReservation.getDateTrip());
        assertEquals("10:00", foundReservation.getTime());
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
