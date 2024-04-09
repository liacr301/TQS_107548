package bdd_selenium;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

import java.time.Duration;

public class ReservationSteps {

    WebDriver driver;

    @Given("the user navigates to trips page by opening Firefox")
    public void theUserNavigatesToTripsPage() {
        // Assuming geckodriver is set in system path
        driver = new FirefoxDriver();
        driver.get("http://localhost:5173/trips"); // Adjust the URL based on your application
    }

    @When("the user selects a trip from {string} to {string} and the date {string}")
    public void theUserSelectsATrip(String fromCity, String toCity, String date) {
        driver.findElement(By.id("fromCity")).sendKeys(fromCity);
        driver.findElement(By.id("toCity")).sendKeys(toCity);
        driver.findElement(By.id("dateTrip")).sendKeys(date);
        driver.findElement(By.id("searchButton")).click();
    }

    @And("selects the option with the time {string} and price {string}")
    public void selectsTheOptionWithTheTimeAndPrice(String time, String price) {
        driver.findElement(By.cssSelector("button.reserve")).click();
    }

    @And("the user fills in the reservation details with {string}, {string}, {string}")
    public void theUserFillsInTheReservationDetails(String firstName, String lastName, String email) {
        // Wait until the input fields are present and interactable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='First Name']")));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Last Name']")));
        lastNameInput.sendKeys(lastName);

        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Email']")));
        emailInput.sendKeys(email);

        WebElement purchaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Purchase']")));
        purchaseButton.click();
    }

    @Then("the reservation is successful")
    public void theReservationIsSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Assuming the success message is shown in an alert, adjust as needed based on your application's behavior
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        assertTrue(alertText.contains("Reservation successful! Here's your token:"));
        driver.switchTo().alert().accept();
    }

    // Assuming there is a link or button on the UserDetails page to navigate to the reservation search page
    @When("the user navigates to the reservation search page")
    public void theUserNavigatesToTheReservationSearchPage() {
        WebElement reservationsLink = driver.findElement(By.xpath("//a[text()='See your reservations']"));
        reservationsLink.click();
    }

    // Assuming the reservation search page has an input field and a button for searching by token
    @And("the user enters the reservation token {string}")
    public void theUserEntersTheReservationToken(String token) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement tokenInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter your reservation token']"))); // Adjust the XPath based on actual implementation
        tokenInput.sendKeys(token);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']"))); // Adjust the button text if necessary
        searchButton.click();
    }
}
