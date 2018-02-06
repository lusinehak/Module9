package factory;

import org.openqa.selenium.WebDriver;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class Driver {
    protected WebDriver driver;

    public abstract WebDriver createDriver();

    public void closeDriver() {
        if (driver != null) {}
        driver.close();
    }
    public void start(String url){
        if (driver != null) {
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, SECONDS);
        }
    }
}
