package ua.deti.tqs.lab3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.deti.tqs.lab3_2.controllers.CarController;
import ua.deti.tqs.lab3_2.models.Car;
import ua.deti.tqs.lab3_2.services.CarManagerService;

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
    private CarManagerService carService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostCar_thenCreateCar( ) throws Exception {
        Car car_1 = new Car("BMW", "M4");

        when( carService.createCar(Mockito.any()) ).thenReturn(car_1);

        mvc.perform(
                        post("/api/car").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("M4")));

        verify(carService, times(1)).createCar(Mockito.any());
    }

    @Test
    public void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car car_1 = new Car("BMW", "M4");
        Car car_2 = new Car("Opel", "Astra");
        Car car_3 = new Car("Nissan", "Qashqai");

        List<Car> allCars = Arrays.asList(car_1, car_2, car_3);

        when( carService.getAllCars()).thenReturn(allCars);

        mvc.perform(
                        get("/api/car").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].model", is(car_1.getModel())))
                .andExpect(jsonPath("$[1].model", is(car_2.getModel())))
                .andExpect(jsonPath("$[2].model", is(car_3.getModel())));

        verify(carService, times(1)).getAllCars();
    }

    @Test
    public void givenCarId_whenGetCarById_thenReturnJsonArray() throws Exception {
        Car car_1 = new Car("BMW", "M4");

        when( carService.getCarById(anyLong())).thenReturn(car_1);

        mvc.perform(
                        get("/api/car/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is(car_1.getModel())));

        verify(carService, times(1)).getCarById(anyLong());
    }

}