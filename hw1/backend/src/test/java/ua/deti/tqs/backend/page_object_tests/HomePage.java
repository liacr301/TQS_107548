package ua.deti.tqs.backend.page_object_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(css = ".btn")
    private WebElement findBusTripButton;

    @FindBy(css = "#fromCity > .select")
    private WebElement fromCityDropdown;

    @FindBy(css = "#toCity > .select")
    private WebElement toCityDropdown;

    @FindBy(css = ".react-calendar__tile:nth-child(12) > abbr")
    private WebElement selectDate;

    @FindBy(css = ".btn-primary")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public void findBusTrip() {
        findBusTripButton.click();
    }

    public void selectFromCity(String xpath) {
        fromCityDropdown.click();
        WebElement cityOption = fromCityDropdown.findElement(By.xpath(xpath));
        cityOption.click();
    }

    public void selectToCity(String xpath) {
        toCityDropdown.click();
        WebElement cityOption = toCityDropdown.findElement(By.xpath(xpath));
        cityOption.click();
    }

    public void selectDateAndSearch() {
        selectDate.click();
        searchButton.click();
    }
}
