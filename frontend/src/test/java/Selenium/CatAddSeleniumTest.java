package Selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CatAddSeleniumTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new EdgeDriver();

    }

    @Test
    public void fillInForm() {
        CatAddSelenium page = new CatAddSelenium(driver);
        page.open();
        page.fillInName();
        page.fillInAge();
    }
}
