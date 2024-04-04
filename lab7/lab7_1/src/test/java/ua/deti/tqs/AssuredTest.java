package ua.deti.tqs;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class AssuredTest {

    @Test
    public void getAllToDos(){
        get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200);
    }

    @Test
    public void Query4Response(){
        get("https://jsonplaceholder.typicode.com/todos/4").then().assertThat()
                .body("title", equalTo("et porro tempora"));
    }

    @Test
    public void givenUrl_whenJsonResponseHasArrayWithGivenValuesUnderKey_thenCorrect() {
        get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .assertThat()
                .body("id", hasItems(198, 199));
    }

    @Test
    public void whenMeasureResponseTime_LessThan2Seconds() {
        when().get("https://jsonplaceholder.typicode.com/todos").then().time(lessThan(2000L));
    }


}
