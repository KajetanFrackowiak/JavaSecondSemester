package Selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CatEditSeleniumTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new EdgeDriver();

    }

    @Test
    public void fillInForm() {
        CatEditSelenium page = new CatEditSelenium(driver);
        page.open();
        page.fillInName();
        page.fillInAge();
    }
}
