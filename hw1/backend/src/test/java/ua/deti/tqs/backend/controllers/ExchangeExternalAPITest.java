package ua.deti.tqs.backend.controllers;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class ExchangeExternalAPITest {

    private final String apiKey = "a439deea942651e54ad0ed50";
    private final String baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/EUR";

    @Test
    public void getExchangeRate() {
        given().when().get(baseUrl).then().assertThat()
            .statusCode(200)
            .body("result", equalTo("success"))
            .body("conversion_rates.USD", notNullValue())
            .body("conversion_rates.CAD", notNullValue())
            .body("conversion_rates.AUD", notNullValue())
            .body("conversion_rates.USD", equalTo(1.085f))
            .body("conversion_rates.CAD", equalTo(1.4733f))
            .body("conversion_rates.AUD", equalTo(1.6444f))
            .body("conversion_rates.GBP", equalTo(0.8578f))
            .body("conversion_rates.GBP", notNullValue());
    }
}
