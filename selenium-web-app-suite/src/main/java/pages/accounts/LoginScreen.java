package pages.accounts;

import common.constants.FilePath;
import common.constants.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.DataLoader;

import java.util.Objects;

import static tests.BaseTest.logTestStep;

public class LoginScreen extends BasePage {
    public final String loginScreenLocators = FilePath.LOCATOR_ACCOUNTS_LOGIN_POPUP;
    public final String appData = FilePath.REAL_APP_DATA_FILE_PATH;

    public LoginScreen(WebDriver driver) {
        super(driver);
    }

    public void closeLoginPopup(){
        By crossIcon = DataLoader.getLocator(loginScreenLocators,"crossIcon");
        waitForElementToBeClickable(crossIcon, WaitTime.LOW.getTimeInMillis());
        click(crossIcon);
        System.out.println("WaitTime: "+WaitTime.LOW.getTimeInMillis());
        logTestStep("Tap on cross icon");
    }
}
