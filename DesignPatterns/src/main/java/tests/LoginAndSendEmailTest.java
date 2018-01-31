package tests;

import businessobjects.Email;
import businessobjects.User;
import factory.ChromeDriverInstance;
import factory.DriverFactory;
import factory.FirefoxDriverInstance;
import org.testng.annotations.*;
import pageobjects.LoginPage;
import pageobjects.MailActionsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import singleton.WebDriverSingleton;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginAndSendEmailTest {
    private WebDriver driver;
    private User user;
    private Email email;
    private String URL = "https://accounts.google.com";

   // @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void init(/*@Optional("chrome") String browser*/) {
        /*DriverFactory driverInstance = new ChromeDriverInstance();
        driver = driverInstance.createDriver();*/
        driver = WebDriverSingleton.CreateDriver();
        /*if(browser == "chrome") {
            DriverFactory driverInstance = new ChromeDriverInstance();
            driver = driverInstance.createDriver();
        } else if (browser == "firefox") {
            DriverFactory driverInstance = new FirefoxDriverInstance();
            driver = driverInstance.createDriver();
        }*/
        user = new User();
        email = new Email();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    @Test
    public void login() {
        System.out.println("aaaaaaaaaaaaa " + user.getUserName());
        boolean isLoggedIn = new LoginPage().logInAccount(user.getUserName(), user.getPassword()).isLoggedIn();
        Assert.assertTrue(isLoggedIn, "Login issue");
    }

    @Test(dependsOnMethods = "login")
    public void composeAndSave() {
        boolean isSavedInDrafts = new MailActionsPage(driver).composeAndSaveAsDraft(email.getReceiver(), email.getSubject(),
                email.getContent()).goToFolder("Drafts").isItemExists(email.getSubject());
        Assert.assertTrue(isSavedInDrafts, "Item is not saved in drafts");
    }

    @Test(dependsOnMethods = "composeAndSave")
    public void checkFields() {
        new MailActionsPage(driver).selectDraftItem(email.getSubject());
        String receiver = new MailActionsPage(driver).getReceiver(email.getReceiver());
        String subject = new MailActionsPage(driver).getSubject();
        String content = new MailActionsPage(driver).getContent();
        Assert.assertEquals(receiver, email.getReceiver(), "Invalid receiver");
        Assert.assertEquals(subject, email.getSubject(), "Invalid subject");
        Assert.assertEquals(content, email.getContent(), "Invalid content");
    }

    @Test(dependsOnMethods = "checkFields")
    public void send() {
        boolean isSent = new MailActionsPage(driver).sendMail().goToFolder("Sent Mail").isItemExists(email.getSubject());
        Assert.assertTrue(isSent, "Item is not sent");
    }

    @Test(dependsOnMethods = "send")
    public void checkDraft() {
        boolean isAbsent = new MailActionsPage(driver).goToFolder("Drafts").isDraftEmpty();
        Assert.assertTrue(isAbsent, "Item exists in draft folder.");
    }

    @AfterClass
    public void quit() {
        new MailActionsPage(driver).logOut();
        driver.quit();
    }
}
