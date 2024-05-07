package tests.moduletwo;

import common.constants.FilePath;
import org.testng.annotations.Test;
import pages.moduletwo.LoginScreen;
import tests.BaseTest;
import utils.DataLoader;
import utils.TestDataProvider;

import java.util.Map;

public class OrangeHRMLoginTest extends BaseTest{
    LoginScreen loginScreen;
    @Test(dataProvider = "module2", dataProviderClass = TestDataProvider.class)
    public void performLoginWithValidCredentials(Map<String, Object> testData){
        loginScreen = new LoginScreen(BaseTest.getDriver());
        loginScreen.openLoginScreen();
        loginScreen.enterUserName(testData.get("username").toString());
        loginScreen.enterPassword(testData.get("password").toString());
        loginScreen.tapOnLoginButton();
    }
}
