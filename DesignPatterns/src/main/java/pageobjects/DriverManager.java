package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DriverManager {
    protected WebDriver driver;

    public DriverManager(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    protected void waitForElement(WebDriver driver, List<WebElement> element) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(element));
    }
}
