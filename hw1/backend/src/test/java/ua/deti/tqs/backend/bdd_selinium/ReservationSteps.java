package ua.deti.tqs.backend.bdd_selinium;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;

public class ReservationSteps {
    private FirefoxDriver driver;
    private String token;

    @Given("the user has a reservation token {string}")
    public void the_user_has_a_reservation_token(String token) {
        this.token = token;
    }

    @When("the user navigates to the reservation search page")
    public void the_user_navigates_to_the_reservation_search_page() {
        driver.findElement(By.linkText("See your reservations")).click();
    }

    @When("the user enters the reservation token")
    public void the_user_enters_the_reservation_token() {
        driver.findElement(By.cssSelector(".input")).sendKeys(token);
        driver.findElement(By.cssSelector("button[type='submit']")).click(); // Adjust the selector as per your application
    }

    @Then("the user sees the reservation details")
    public void the_user_sees_the_reservation_details() {
        // assertTrue(driver.findElement(By.cssSelector("your-reservation-details-selector")).isDisplayed());

        driver.quit();
    }
}
