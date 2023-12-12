package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatEditSelenium {

    WebDriver webDriver;

    @FindBy(id = "nameInput")
    WebElement firstNameInput;

    @FindBy(id = "ageInput")
    WebElement ageInput;

    public final static String URL = "http://localhost:8080/editCat/1";

    public CatEditSelenium(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    public void open() {
        webDriver.get(URL);
    }

    public void fillInName() {
        firstNameInput.sendKeys("Cat!");
    }
    public void fillInAge() {
        ageInput.sendKeys("100");
    }
}
