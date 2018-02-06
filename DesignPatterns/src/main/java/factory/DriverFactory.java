package factory;

import singleton.Logger;

public class DriverFactory {

    public static Driver getDriver(DriverType type) {

        Driver driver = null;

        switch (type) {
            case CHROME:
                driver = new ChromeDriverInstance();
                break;
            case FIREFOX:
                driver = new FirefoxDriverInstance();
                break;
            default:
                Logger.getInstance().error("Please specify the driver type");
                break;
        }
        return driver;
    }


}
