package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MailActionsPage extends DriverManager {

    private static final String GO_TO_MAIL = ".WaidBe";
    private static final String COMPOSE_MAIL = "//*[contains(@class, 'T-I-KE')]";
    private static final String SET_RECEIVER = "to";
    private static final String SET_SUBJECT = "subjectbox";
    private static final String CONTENT = "//div[@aria-label='Message Body']";
    private static final String SEND_EMAIL = "//div[text()='Send']";
    private static final String CLICK_ON_FOLDER = "//a[contains(@title, '%s')]";
    private static final String SELECT_ITEM = "//div[@class='y6']//span[contains(text(), '%s')]";
    private static final String GOOGLE_ACCOUNT = "//span[@class='gb_ab gbii']";
    private static final String LOG_OFF = "//a[text()='Sign out']";
    private static final String MESSAGE_WINDOW = "//div[@class='nH Hd']";

    public MailActionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = GO_TO_MAIL)
    private WebElement goToMail;

    @FindBy (xpath =  COMPOSE_MAIL)
    private WebElement composeEmail;

    @FindBy (name = SET_RECEIVER)
    private WebElement setReceiver;

    @FindBy (name = SET_SUBJECT)
    private WebElement setSubject;

    @FindBy (xpath =  CONTENT)
    private WebElement content;

    @FindBy (xpath =  SEND_EMAIL)
    private WebElement sendEmail;

    @FindBy (xpath =  GOOGLE_ACCOUNT)
    private WebElement googleAccount;

    @FindBy (xpath =  LOG_OFF)
    private WebElement logOff;

    @FindBy (xpath =  MESSAGE_WINDOW)
    private List<WebElement> messageWindow;

    public MailActionsPage composeAndSaveAsDraft(String receiver, String sbj, String cnt) {
        goToMail.click();
        composeEmail.click();
        waitForElement(driver, messageWindow);
        setReceiver.sendKeys(receiver);
        setSubject.sendKeys(sbj);
        content.sendKeys(cnt);
        sendEmail.click();
        return new MailActionsPage(driver);
    }

    public MailActionsPage goToFolder(String name) {
        String folder = String.format(CLICK_ON_FOLDER, name);
        WebElement clickOnFolder = driver.findElement(By.xpath(folder));
        clickOnFolder.click();
        return new MailActionsPage(driver);
    }

    public boolean isItemExists(String name) {
        String item = String.format(SELECT_ITEM, name);
        WebElement selectItem = driver.findElement(By.xpath(item));
        return selectItem.isDisplayed();
    }

    public void logOut() {
        googleAccount.click();
        logOff.click();
    }
}
