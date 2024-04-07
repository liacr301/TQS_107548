package ua.deti.tqs.backend.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.persistence.EntityNotFoundException;
import ua.deti.tqs.backend.models.Reservation;
import ua.deti.tqs.backend.models.Trip;
import ua.deti.tqs.backend.services.ReservationService;
import ua.deti.tqs.backend.services.TripService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private TripService tripService;

    private Trip mockTrip;
    private Reservation mockReservation;

    @BeforeEach
    public void setUp() {
        mockTrip = new Trip(1, "Aveiro", "Porto", "2021-03-01", "10:00", 10.0, 50);
        mockReservation = new Reservation("token123", "Aveiro", "Porto", "2021-03-01", "10:00", "John", "Doe", "john@example.com");
        mockReservation.setId(1);
    }

    @Test
    public void whenSaveReservationWithValidInput_thenCreateReservation() throws Exception {
        when(tripService.findTripById(1)).thenReturn(mockTrip);
        when(reservationService.saveReservation(any(Reservation.class))).thenReturn(mockReservation);

        mvc.perform(post("/api/reservations/save")
                .param("tripId", "1")
                .param("firstName", "John")
                .param("lastName", "Doe")
                .param("email", "john@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is(mockReservation.getToken())))
                .andExpect(jsonPath("$.fromCity", is(mockReservation.getFromCity())))
                .andExpect(jsonPath("$.toCity", is(mockReservation.getToCity())))
                .andExpect(jsonPath("$.dateTrip", is(mockReservation.getDateTrip())))
                .andExpect(jsonPath("$.timeTrip", is(mockReservation.getTimeTrip())))
                .andExpect(jsonPath("$.firstName", is(mockReservation.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(mockReservation.getLastName())))
                .andExpect(jsonPath("$.email", is(mockReservation.getEmail())));
    }

    @Test
    public void whenSaveReservationWithInvalidTripId_thenHandleGracefully() throws Exception {
        when(tripService.findTripById(anyInt())).thenThrow(new EntityNotFoundException("Trip not found"));

        mvc.perform(post("/api/reservations/save")
                .param("tripId", "999")
                .param("firstName", "John")
                .param("lastName", "Doe")
                .param("email", "john@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenSaveReservationWithMissingParameters_thenBadRequest() throws Exception {
        mvc.perform(post("/api/reservations/save")
                .param("tripId", "1")
                .param("firstName", "")
                .param("lastName", "Doe")
                .param("email", "john@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
