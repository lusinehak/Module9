package factory;

import org.openqa.selenium.WebDriver;

public abstract class DriverFactory {
    protected WebDriver driver;

    public abstract WebDriver createDriver();
}
