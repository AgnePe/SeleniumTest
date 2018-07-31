package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Agne Pelurityte on 2018-07-30.
 */
public class WixHomePage {

    protected WebDriver driver;

    protected WebDriverWait wait;

    public WixHomePage(WebDriver driver) {
        this.driver = driver;
        // given 5 seconds for page load time out
        this.wait = new WebDriverWait(this.driver, 5);
        // initialize all web elements
        PageFactory.initElements(driver, this);
    }

    // I establish these to be necessary WebElements to be loaded in the home page for it to be valid as correct
    @FindBy(className = "wm-logo-wrapper")
    private WebElement WixLogo;

    @FindBy(className = "signin")
    private WebElement SignInButton;

    private String WixHomePageURL = "https://www.wix.com/";

    public boolean isHomePageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(WixLogo));
            wait.until(ExpectedConditions.visibilityOf(SignInButton));
        } catch (Exception e) {
            // page did not load in time
            return false;
        }
        return driver.getCurrentUrl().equalsIgnoreCase(WixHomePageURL);
    }
}
