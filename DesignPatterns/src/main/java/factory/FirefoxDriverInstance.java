package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import singleton.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FirefoxDriverInstance extends Driver {

    @Override
    public WebDriver createDriver() {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        driver = new FirefoxDriver();
        return driver;
    }

    public void close() {
        driver.close();
        Logger.getInstance().info("Firefox web browser is closed");
    }

    public void quit() {
        driver.quit();
        Logger.getInstance().info("Firefox web browser is quited");

    }

    public void start(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, SECONDS);
    }
}
