package ua.deti.tqs.backend.page_object_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReservationPage {
    private WebDriver driver;

    @FindBy(css = ".input:nth-child(1)")
    private WebElement firstNameInput;

    @FindBy(css = ".input:nth-child(2)")
    private WebElement lastNameInput;

    @FindBy(css = ".input:nth-child(3)")
    private WebElement emailInput;

    @FindBy(css = ".btn-primary")
    private WebElement purchaseButton;

    public ReservationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFirstReserveButton(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr:nth-child(1) .btn"))).click();
    }

    public void makeReservation(String firstName, String lastName, String email) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        purchaseButton.click();
    }
}