package ua.deti.tqs.lab3_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import ua.deti.tqs.lab3_2.services.CarManagerService;

@WebMvcTest
public class CarControllerTest {

    @MockBean
    CarManagerService carService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void whenGetCars_thenStatus200() {
        RestAssuredMockMvc.when()
                .get("/api/cars")
                .then()
                .statusCode(200);
    }


}
