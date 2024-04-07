package ua.deti.tqs.backend.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ua.deti.tqs.backend.models.Trip;
import ua.deti.tqs.backend.services.TripService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TripController.class)
public class TripControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TripService tripService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void givenTripId_whenGetTripById_thenReturnJsonArray() throws Exception {
        Trip trip1 = new Trip(409, "Aveiro", "Lisboa", "2021-03-08", "10:00", 20.0, 50);

        when( tripService.findTripById(anyInt())).thenReturn(trip1);

        mvc.perform(
                        get("/api/trips/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bus", is(trip1.getBus())))
                .andExpect(jsonPath("$.toCity", is(trip1.getToCity())))
                .andExpect(jsonPath("$.fromCity", is(trip1.getFromCity())))
                .andExpect(jsonPath("$.dateTrip", is(trip1.getDateTrip())))
                .andExpect(jsonPath("$.timeTrip", is(trip1.getTimeTrip())))
                .andExpect(jsonPath("$.price", is(trip1.getPrice())))
                .andExpect(jsonPath("$.availableSeats", is(trip1.getAvailableSeats())));

        verify(tripService, times(1)).findTripById(anyInt());
    }

    @Test
    public void givenTrip_whenSearchTrips_thenReturnJsonArray() throws Exception {
        Trip trip1 = new Trip(409, "Aveiro", "Lisboa", "2021-03-08", "10:00", 20.0, 50);
        Trip trip2 = new Trip(700, "Aveiro", "Lisboa", "2021-03-08", "15:00", 20.0, 30);

        List<Trip> allTripsWithSpecifications = Arrays.asList(trip1, trip2);

        when(tripService.findTripByFromCityToCityAndDateTrip(anyString(), anyString(), anyString())).thenReturn(allTripsWithSpecifications);

        mvc.perform(
                get("/api/trips/all_for_search?fromCity=Aveiro&toCity=Lisboa&date=2021-03-08").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].bus", is(trip1.getBus())))
                .andExpect(jsonPath("$[0].toCity", is(trip1.getToCity())))
                .andExpect(jsonPath("$[0].fromCity", is(trip1.getFromCity())))
                .andExpect(jsonPath("$[0].dateTrip", is(trip1.getDateTrip())))
                .andExpect(jsonPath("$[0].timeTrip", is(trip1.getTimeTrip())))
                .andExpect(jsonPath("$[0].price", is(trip1.getPrice())))
                .andExpect(jsonPath("$[0].availableSeats", is(trip1.getAvailableSeats())))
                .andExpect(jsonPath("$[1].bus", is(trip2.getBus())))
                .andExpect(jsonPath("$[1].toCity", is(trip2.getToCity())))
                .andExpect(jsonPath("$[1].fromCity", is(trip2.getFromCity())))
                .andExpect(jsonPath("$[1].dateTrip", is(trip2.getDateTrip())))
                .andExpect(jsonPath("$[1].timeTrip", is(trip2.getTimeTrip())))
                .andExpect(jsonPath("$[1].price", is(trip2.getPrice())))
                .andExpect(jsonPath("$[1].availableSeats", is(trip2.getAvailableSeats())));
            verify(tripService, times(1)).findTripByFromCityToCityAndDateTrip(anyString(), anyString(), anyString());
    }

    @Test
    public void whenSearchTripsWithNullParameters_thenBadRequest() throws Exception {
        mvc.perform(get("/api/trips/all_for_search")
                .param("fromCity", (String) null)
                .param("toCity", "Porto")
                .param("dateTrip", "2021-03-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/trips/all_for_search")
                .param("fromCity", "Aveiro")
                .param("toCity", (String) null)
                .param("dateTrip", "2021-03-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/trips/all_for_search")
                .param("fromCity", "Aveiro")
                .param("toCity", "Porto")
                .param("dateTrip", (String) null)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenSearchTripsWithEmptyParameters_thenBadRequest() throws Exception {
        mvc.perform(get("/api/trips/all_for_search")
                .param("fromCity", "")
                .param("toCity", "Porto")
                .param("dateTrip", "2021-03-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/trips/all_for_search")
                .param("fromCity", "Aveiro")
                .param("toCity", "")
                .param("dateTrip", "2021-03-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/trips/all_for_search")
                .param("fromCity", "Aveiro")
                .param("toCity", "Porto")
                .param("dateTrip", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenSearchTripsWithSameFromAndToCity_thenBadRequest() throws Exception {
        mvc.perform(get("/api/trips/all_for_search")
                .param("fromCity", "Aveiro")
                .param("toCity", "Aveiro")
                .param("dateTrip", "2021-03-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    }
