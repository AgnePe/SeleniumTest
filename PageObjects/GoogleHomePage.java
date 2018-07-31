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
public class GoogleHomePage {

    protected WebDriver driver;

    protected WebDriverWait wait;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        // given 5 seconds for page load time out
        this.wait = new WebDriverWait(this.driver, 5);
        // initialize all web elements
        PageFactory.initElements(driver, this);
    }

    // I establish these to be necessary WebElements to be loaded in the home page for it to be valid as correct
    @FindBy(id = "lst-ib")
    private WebElement GoogleSearchField;

    @FindBy(name = "btnK")
    private WebElement GoogleSearchButton;

    @FindBy(id = "hplogo")
    private WebElement GoogleLogo;

    public void enterSearchTextToTextField(String searchText) {
        //this.GoogleSearchField.clear();
        this.GoogleSearchField.sendKeys(searchText);
    }

    public void clickOnLogo() {
        GoogleLogo.click();
    }

    public GoogleSearchResultPage searchForEnteredText() {
        this.GoogleSearchButton.click();
        return new GoogleSearchResultPage(driver);
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(GoogleSearchButton));
            wait.until(ExpectedConditions.visibilityOf(GoogleSearchField));
            wait.until(ExpectedConditions.visibilityOf(GoogleLogo));
        } catch (Exception e) {
            // page did not load in time
            return false;
        }
        return true;
    }
}
