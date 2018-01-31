package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import singleton.WebDriverSingleton;

import java.util.List;

public class LoginPage extends DriverManager {
   // private WebDriver driver;

    /*public LoginPage(WebDriver driver) {
        this.driver = driver;
    }*/

    private static final String USERNAME_FIELD = "identifierId";
    private static final String PASSDOWR_FIELD = "password";
    private static final String NEXT_BTN = "//div[@id='identifierNext']|//div[@id='passwordNext']";
    private static final String VERIFY_ACCOUNT = "paz5i";

    @FindBy(id = USERNAME_FIELD)
    private WebElement username;

    @FindBy(name = PASSDOWR_FIELD)
    private List<WebElement> password;

    @FindBy(xpath = NEXT_BTN)
    private WebElement nextButton;

    @FindBy(className = VERIFY_ACCOUNT)
    private WebElement verifyAccount;

    public LoginPage logInAccount(String uName, String passwd) {
        username.sendKeys(uName);
        nextButton.click();
        waitForElement(WebDriverSingleton.CreateDriver(), password);
        password.get(0).sendKeys(passwd);
        nextButton.click();
        return new LoginPage();
    }

    public boolean isLoggedIn() {
        return verifyAccount.isDisplayed();
    }
}

