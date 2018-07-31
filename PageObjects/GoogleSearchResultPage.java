package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Agne Pelurityte on 2018-07-30.
 */
public class GoogleSearchResultPage {

    protected WebDriver driver;

    protected WebDriverWait wait;

    public GoogleSearchResultPage(WebDriver driver) {
        this.driver = driver;
        // given 5 seconds for page load time out
        this.wait = new WebDriverWait(this.driver, 5);
        // initialize all web elements
        PageFactory.initElements(driver, this);
    }

    @FindBys({
        // all non-ad links have this ID, advertisements are stored under different ID
        @FindBy(id = "res"),
        // find all links from the non-ad links from the results
        @FindBy(tagName = "a")
    })
    private List<WebElement> AllNonAdSearchResultLinks;

    // I establish these to be necessary WebElements to be loaded in the home page for it to be valid as correct
    @FindBy(id = "logo")
    private WebElement GoogleLogo;

    @FindBy(id = "resultStats")
    private WebElement SearchResultStatistics;

    public List<WebElement> getAllSearchResults() {
        return AllNonAdSearchResultLinks;
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(GoogleLogo));
            wait.until(ExpectedConditions.visibilityOf(SearchResultStatistics));
        } catch (Exception e) {
            // page did not load in time
            return false;
        }
        return true;
    }
}
