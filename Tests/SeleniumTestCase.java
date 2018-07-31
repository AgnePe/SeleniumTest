package Tests;

import PageObjects.GoogleHomePage;
import PageObjects.GoogleSearchResultPage;
import PageObjects.WixHomePage;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by Agne Pelurityte on 2018-07-30.
 */
public class SeleniumTestCase {

    public WebDriver driver;

    // to setup needed configuration before the test
    @BeforeSuite
    public void setupBeforeTest() {
        // The drivers' paths are included in Environment's Path variable
        //this.driver = new ChromeDriver();
        this.driver = new FirefoxDriver();
    }

    // to clean up after the test is completed
    @AfterSuite
    public void cleanUpAfterTest() {
        driver.close();
    }

    // main test case to be executed
    @Test
    public void searchForWixInGoogleAndLoadMainPage() {

        // in the browser created, open the Google home page
        driver.get("http://www.google.com");

        // create a page object for Google home page
        GoogleHomePage gHomePage = new GoogleHomePage(driver);
        Assert.assertTrue(gHomePage.isPageLoaded());

        // search for wix in google and get a result page
        gHomePage.enterSearchTextToTextField("Wix");
        // this is needed to take focus off the search field, as a drop down list appears, and search button becomes invisible
        // there is a search button in the drop down lisr as well, but developer tools did not help to get details on that element
        gHomePage.clickOnLogo();

        GoogleSearchResultPage gSearchResultPage = gHomePage.searchForEnteredText();
        Assert.assertTrue(gSearchResultPage.isPageLoaded());

        // at the begining, page is not loaded
        WixHomePage wixHomePage = null;
        // click the first link that is not an ad
        for (WebElement element : gSearchResultPage.getAllSearchResults()) {
            if (element.getAttribute("href").contains("wix.com")) {
                element.click();
                wixHomePage = new WixHomePage(driver);
                break;
            }
        }

        // verify that Home page is loaded
        Assert.assertTrue(wixHomePage.isHomePageLoaded());
    }

}
