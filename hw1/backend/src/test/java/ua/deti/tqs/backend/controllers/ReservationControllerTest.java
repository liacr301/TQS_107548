package ua.deti.tqs.backend.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ua.deti.tqs.backend.models.Reservation;
import ua.deti.tqs.backend.services.ReservationService;
import ua.deti.tqs.backend.services.TripService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
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

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenGetReservationByToken_thenReturnReservationJson() throws Exception {
        String token = "testToken123";
        Reservation reservation = new Reservation(1, token, "Aveiro", "Porto", "2021-03-01", "10:00", "John", "Doe", "john.doe@example.com");

        when(reservationService.findReservationById(token)).thenReturn(reservation);

        mvc.perform(get("/api/reservations/{Token}", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is(reservation.getToken())))
                .andExpect(jsonPath("$.fromCity", is(reservation.getFromCity())))
                .andExpect(jsonPath("$.toCity", is(reservation.getToCity())))
                .andExpect(jsonPath("$.dateTrip", is(reservation.getDateTrip())))
                .andExpect(jsonPath("$.time", is(reservation.getTime())))
                .andExpect(jsonPath("$.firstName", is(reservation.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(reservation.getLastName())))
                .andExpect(jsonPath("$.email", is(reservation.getEmail())));
    }

    @Test
    public void whenSaveReservation_thenReturnSavedReservationJson() throws Exception {
        Reservation reservation = new Reservation(1, "token123", "Aveiro", "Porto", "2021-03-01", "10:00", "John", "Doe", "john.doe@example.com");

        when(reservationService.saveReservation(any(Reservation.class))).thenReturn(reservation);

        mvc.perform(post("/api/reservations/save")
                .param("fromCity", reservation.getFromCity())
                .param("toCity", reservation.getToCity())
                .param("date", reservation.getDateTrip())
                .param("time", reservation.getTime())
                .param("firstName", reservation.getFirstName())
                .param("lastName", reservation.getLastName())
                .param("email", reservation.getEmail())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is(reservation.getToken())))
                .andExpect(jsonPath("$.fromCity", is(reservation.getFromCity())))
                .andExpect(jsonPath("$.toCity", is(reservation.getToCity())))
                .andExpect(jsonPath("$.dateTrip", is(reservation.getDateTrip())))
                .andExpect(jsonPath("$.time", is(reservation.getTime())))
                .andExpect(jsonPath("$.firstName", is(reservation.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(reservation.getLastName())))
                .andExpect(jsonPath("$.email", is(reservation.getEmail())));
    }

    @Test
    public void whenSaveReservationWithMissingParams_thenBadRequest() throws Exception {
        mvc.perform(post("/api/reservations/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest());
    }
}
