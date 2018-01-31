package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MailActionsPage extends DriverManager {
    private WebDriver driver;

    private static final String GO_TO_MAIL = ".WaidBe";
    private static final String COMPOSE_MAIL = "//*[contains(@class, 'T-I-KE')]";
    private static final String SET_RECEIVER = "to";
    private static final String SET_SUBJECT = "subjectbox";
    private static final String CONTENT = "//div[@aria-label='Message Body']";
    private static final String GET_RECEIVER = "//span[contains(text(),'%s')]";
    private static final String GET_SUBJECT = "//input[@name='subject']";
    private static final String SEND_EMAIL = "//div[text()='Send']";
    private static final String CLOSE_ITEM = "//img[@aria-label='Save & Close']";
    private static final String CLICK_ON_FOLDER = "//a[contains(@title, '%s')]";
    private static final String SELECT_ITEM = "//span[contains(text(), '%s')]";
    private static final String SELECT_DRAFT_EMAIL = "//span[contains(text(), '%s')]";
    private static final String GOOGLE_ACCOUNT = "//span[@class='gb_ab gbii']";
    private static final String LOG_OFF = "//a[text()='Sign out']";
    private static final String MESSAGE_WINDOW = "//div[@class='nH Hd']";
    private static final String EMPTY_DRAFT = "//td[@class='TC']";

    public MailActionsPage(WebDriver driver) {
        this.driver = driver;
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

    @FindBy (xpath = GET_SUBJECT)
    private WebElement getSubject;

    @FindBy (xpath =  SEND_EMAIL)
    private WebElement sendEmail;

    @FindBy (xpath =  CLOSE_ITEM)
    private WebElement closeItem;

    @FindBy (xpath =  GOOGLE_ACCOUNT)
    private WebElement googleAccount;

    @FindBy (xpath =  LOG_OFF)
    private WebElement logOff;

    @FindBy (xpath =  MESSAGE_WINDOW)
    private List<WebElement> messageWindow;

    @FindBy (xpath =  EMPTY_DRAFT)
    private WebElement emptyDraft;

    public MailActionsPage composeAndSaveAsDraft(String receiver, String sbj, String cnt) {
        goToMail.click();
        composeEmail.click();
        setReceiver.sendKeys(receiver);
        setSubject.sendKeys(sbj);
        content.sendKeys(cnt);
        closeItem.click();
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

    public void selectDraftItem(String sbj) {
        String email = String.format(SELECT_DRAFT_EMAIL, sbj);
        WebElement selectDraftEmail = driver.findElement(By.xpath(email));
        selectDraftEmail.click();
        waitForElement(driver, messageWindow);
    }

    public String getReceiver(String name) {
        String receiver = String.format(GET_RECEIVER, name);
        WebElement getReceiver = driver.findElement(By.xpath(receiver));
        return getReceiver.getText().toString();
    }

    public String getSubject() {
        return getSubject.getAttribute("value").toString();
    }

    public String getContent() {
        return content.getText().toString();
    }

    public MailActionsPage sendMail() {
        sendEmail.click();
        return new MailActionsPage(driver);
    }

    public boolean isDraftEmpty() {
        return emptyDraft.isDisplayed();
    }

    public void logOut() {
        googleAccount.click();
        logOff.click();
    }
}
