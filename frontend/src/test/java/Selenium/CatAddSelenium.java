package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatAddSelenium {
    WebDriver webDriver;



//    @FindBy(id = "gender")
//    WebElement genderDropdown;
//
//    @FindBy(id = "red")
//    WebElement redRadioButton;
//
//    @FindBy(id = "blue")
//    WebElement blueRadioButton;

    @FindBy(id = "nameInput")
    WebElement firstNameInput;

    @FindBy(id = "ageInput")
    WebElement ageInput;
    public final static String URL = "http://localhost:8080/addCat";

    public CatAddSelenium(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    public void open() {
        webDriver.get(URL);
    }

    public void fillInName() {
        firstNameInput.sendKeys("Cat");
    }
    public void fillInAge() {
        ageInput.sendKeys("20");
    }

//    public void selectDropdownOption() {
//        Select select = new Select(genderDropdown);
//        select.selectByVisibleText("Female");
//    }
//
//    public void clickRedRadioButton() {
//        redRadioButton.click();
//    }
//
//    public void clickBlueRadioButton() {
//        blueRadioButton.click();
//    }
}
