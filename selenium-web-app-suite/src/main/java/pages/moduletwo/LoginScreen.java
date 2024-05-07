package pages.moduletwo;

import common.constants.FilePath;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import tests.BaseTest;
import utils.DataLoader;

import static common.constants.FilePath.REAL_LOCATORS_MODULE_TWO_LOGIN_PAGE;

public class LoginScreen extends BasePage {
    WebDriver driver;
    String loginScreenLocators = REAL_LOCATORS_MODULE_TWO_LOGIN_PAGE;
    public LoginScreen(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void openLoginScreen(){
        if(BaseTest.isProdTest()){
            loadUrl(true,DataLoader.getAppData(FilePath.REAL_APP_DATA_FILE_PATH,"baseUrl.prod"));
        }else{
            loadUrl(true,DataLoader.getAppData(FilePath.REAL_APP_DATA_FILE_PATH,"baseUrl.qa"));
        }
        BaseTest.logTestStep("Login page loaded");
    }

    public void enterUserName(String username) {
        enterText(username, DataLoader.getLocator(loginScreenLocators, "userNameTextBox"));
        BaseTest.logTestStep("Entered username");
    }

    public void enterPassword(String password) {
        enterText(password, DataLoader.getLocator(loginScreenLocators, "passwordTextBox"));
        BaseTest.logTestStep("Entered password");
    }

    public void tapOnLoginButton() {
        click(DataLoader.getLocator(loginScreenLocators, "loginButton"));
        BaseTest.logTestStep("Clicked on login button");
    }
}
