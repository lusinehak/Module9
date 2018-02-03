package tests;

import businessobjects.Email;
import businessobjects.User;
import decorator.ExtendedEmail;
import decorator.SimpleMail;
import factory.ChromeDriverInstance;
import factory.DriverFactory;
import factory.FirefoxDriverInstance;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MailActionsPage;
import singleton.Logger;
import templates.MailTemplates;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginAndSendEmailTest {
    private static WebDriver driver;
    private User user;
    private Email email;
    private String URL = "https://accounts.google.com";


    @BeforeClass()
    @Parameters({"browserType"})
    public void init(@Optional("chrome") String browser) {
        if(browser.equals("chrome")) {
            DriverFactory driverInstance = new ChromeDriverInstance();
            driver = driverInstance.createDriver();
        } else if (browser.equals("firefox")) {
            DriverFactory driverInstance = new FirefoxDriverInstance();
            driver = driverInstance.createDriver();
        } else {
            Logger.error("Invalid Browser");
        }
        user = new User();
        email = new Email();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    @Test
    public void login() {
        Logger.info("Login test is starting");
        boolean isLoggedIn = new LoginPage(driver).logInAccount(user.getUserName(),
                user.getPassword()).isLoggedIn();
        Assert.assertTrue(isLoggedIn, "Login issue");
        Logger.info("login successfully completed");
    }

    @Test(dependsOnMethods = "login")
    public void composeAndSend() {
        String content = new ExtendedEmail(new SimpleMail(MailTemplates.content).getContent())
                .setGreetingsAndSignature(email.getReceiver(), user.getUserName());
        Logger.info("Compose and send mail test is starting");
        boolean isSent = new MailActionsPage(driver).composeAndSaveAsDraft(email.getReceiver(),
                email.getSubject(), content).goToFolder("Sent").isItemExists(email.getSubject());
        Assert.assertTrue(isSent, "Item is not sent");
        Logger.info("composeAndSend successfully completed");
    }

    @AfterClass
    public void quit() {
        Logger.info("Finished");
        new MailActionsPage(driver).logOut();
        driver.quit();
    }
}
