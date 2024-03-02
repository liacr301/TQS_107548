package ua.deti.tqs.lab3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.deti.tqs.controllers.CarController;
import ua.deti.tqs.models.Car;
import ua.deti.tqs.services.EmployeeService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car car_1 = new Car("M4", "BMW");

        when( carService.save(Mockito.any()) ).thenReturn(car_1);

        mvc.perform(
                        post("/api/car").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("M4")));

        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car car_1 = new Car("M4", "BMW");
        Car car_2 = new Car("Astra", "Opel");
        Car car_3 = new Car("Qashqai", "Nissan");

        List<Car> allCars = Arrays.asList(car_1, car_2, car_3);

        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
                        get("/api/car").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].model", is(car_2.getModel())))
                .andExpect(jsonPath("$[1].model", is(car_2.getModel())))
                .andExpect(jsonPath("$[2].model", is(car_3.getModel())));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void givenCarId_whenGetCarById_thenReturnJsonArray() throws Exception {
        Car car_1 = new Car("M4", "BMW");
        Car car_2 = new Car("Astra", "Opel");


        when( service.getCarById(car_1.getModel())).thenReturn(car_1.getId());

        mvc.perform(
                        get("/api/car/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].model", is(car_2.getModel())))
                .andExpect(jsonPath("$[1].model", is(car_2.getModel())))
                .andExpect(jsonPath("$[2].model", is(car_3.getModel())));

        verify(service, times(1)).getAllCars();
    }

}