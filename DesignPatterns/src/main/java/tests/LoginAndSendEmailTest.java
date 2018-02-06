package tests;

import businessobjects.Email;
import businessobjects.User;
import decorator.*;
import factory.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;
import pageobjects.MailActionsPage;
import singleton.Logger;

public class LoginAndSendEmailTest {
    private static WebDriver webDriver;
    private Driver driver;
    private User user;
    private Email email;
    private String URL = "https://accounts.google.com";


    @BeforeClass()
    public void init() {

        driver = DriverFactory.getDriver(DriverType.CHROME);
        webDriver = driver.createDriver();
        if (webDriver == null) {
            System.exit(1);
        }
        driver.start(URL);
        user = new User();
        email = new Email();
    }

    @Test
    public void login() {
        Logger.getInstance().info("Login test is starting");
        boolean isLoggedIn = new LoginPage(webDriver).logInAccount(user.getUserName(),
                user.getPassword()).isLoggedIn();
        Assert.assertTrue(isLoggedIn, "Login issue");
        Logger.getInstance().info("login successfully completed");
    }

    @Test(dependsOnMethods = "login")
    public void composeAndSend() {
        EMail mail = new SimpleEmail();
        String content = new SignatureEmailDecorator(new ChristmasEmailDecorator(mail)).getContent();

        Logger.getInstance().info("Compose and send mail test is starting");
        boolean isSent = new MailActionsPage(webDriver).composeAndSaveAsDraft(email.getReceiver(),
                email.getSubject(), content).goToFolder("Sent").isItemExists(email.getSubject());
        Assert.assertTrue(isSent, "Item is not sent");
        Logger.getInstance().info("composeAndSend successfully completed");
    }

    @AfterClass
    public void quit() {
        Logger.getInstance().info("Finished");
        new MailActionsPage(webDriver).logOut();
        driver.closeDriver();
    }
}
