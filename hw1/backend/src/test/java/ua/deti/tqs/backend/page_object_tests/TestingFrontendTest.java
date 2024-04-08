package ua.deti.tqs.backend.page_object_tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class TestingFrontendTest {

    @Test
    public void testingFrontend(FirefoxDriver driver) {
        HomePage homePage = new HomePage(driver);

        homePage.navigateToHomePage("http://localhost:5173/");
        homePage.findBusTrip();
        homePage.selectFromCity("//option[. = 'Aveiro']");
        homePage.selectToCity("//option[. = 'Viseu']");
        homePage.selectDateAndSearch();
    }
}
