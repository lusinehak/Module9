package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import singleton.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ChromeDriverInstance extends Driver {

    @Override
    public WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }
}
