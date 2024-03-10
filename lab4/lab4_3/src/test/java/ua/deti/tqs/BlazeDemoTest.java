package ua.deti.tqs;


import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Dimension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoTest {
    private WebDriver driver;

    private static String url = "https://blazedemo.com/";

    @FindBy(name ="fromPort")
    private WebElement fromPortButton;

    @FindBy(xpath = "//option[. = 'Mexico City']")
    private WebElement mexicoOption;

    @FindBy(css = ".form-inline:nth-child(1) > option:nth-child(6)")
    private WebElement button_1;

    @FindBy(name = "toPort")
    private WebElement toPortButton;

    @FindBy(xpath = "//option[. = 'New York']")
    private WebElement newYorkOption;

    @FindBy(css = ".form-inline:nth-child(4) > option:nth-child(5)")
    private WebElement button_2;

    @FindBy(css = ".btn-primary")
    private WebElement button_3;

    @FindBy(css = "tr:nth-child(3) .btn")
    private WebElement button_4;

    @FindBy(name = "inputName")
    private WebElement inputNameInput;

    @FindBy(name = "address")
    private WebElement addressInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "state")
    private WebElement stateInput;

    @FindBy(name = "zipCode")
    private WebElement zipCodeInput;

    @FindBy(name = "cardType")
    private WebElement cardTypeButton;

    @FindBy(xpath = "//option[. = 'Diner\'s Club']" )
    private WebElement DinnersClubOption;

    @FindBy(css = "option:nth-child(3)")
    private WebElement button_5;

    @FindBy(name = "creditCardNumber")
    private WebElement creditCardNumberInput;

    @FindBy(name = "creditCardMonth")
    private WebElement creditCardMonthInput;

    @FindBy(name = "creditCardYear")
    private WebElement creditCardYearInput;

    @FindBy(name = "nameOnCard")
    private WebElement nameOnCardInput;

    @FindBy(css = "body")
    private WebElement confirmationPage;

    @FindBy(xpath = "//option[. = '\"BlazeDemo Confirmation\"']")
    private WebElement confirmationPageTitle;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    public void cleanUp(){
        driver.quit();
    }

    @Test
    public void test(){
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1848, 1053));
        PageFactory.initElements(driver, this);

        fromPortButton.click();
        mexicoOption.click();
        button_1.click();
        toPortButton.click();
        newYorkOption.click();
        button_2.click();
        button_3.click();
        button_4.click();
        inputNameInput.sendKeys("Lia");
        addressInput.sendKeys("Rua Dom Jose");
        cityInput.sendKeys("Aveiro");
        stateInput.sendKeys("Aveiro");
        zipCodeInput.sendKeys("345655");
        cardTypeButton.click();
        button_5.click();

        creditCardNumberInput.sendKeys("75869504");
        creditCardMonthInput.sendKeys("10");
        creditCardYearInput.sendKeys("2019");

        nameOnCardInput.sendKeys("Lia Lima");

        button_3.click();

        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }

}
