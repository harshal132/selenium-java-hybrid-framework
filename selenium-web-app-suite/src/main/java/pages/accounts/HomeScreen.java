package pages.accounts;

import common.constants.FilePath;
import common.constants.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.DataLoader;
import static tests.BaseTest.logTestStep;

public class HomeScreen extends BasePage {

    public final String homeScreenLocators = FilePath.LOCATOR_ACCOUNTS_HOMEPAGE;
    public final String appData = FilePath.REAL_APP_DATA_FILE_PATH;
    public HomeScreen(WebDriver driver) {
        super(driver);
    }

    public void tapOnLoginLink(){
        By loginLink = DataLoader.getLocator(homeScreenLocators,"loginLink");
        waitForElementToBeClickable(loginLink, WaitTime.LOW.getTimeInMillis());
        click(loginLink);
        logTestStep("Tap on login link");
    }
}
