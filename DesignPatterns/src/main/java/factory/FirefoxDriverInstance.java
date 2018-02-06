package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import singleton.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FirefoxDriverInstance extends Driver {

    @Override
    public WebDriver createDriver() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        return driver;
    }

}
