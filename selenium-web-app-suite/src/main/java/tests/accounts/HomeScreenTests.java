package tests.accounts;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.accounts.HomeScreen;
import pages.accounts.LoginScreen;
import tests.BaseTest;

public class HomeScreenTests extends BaseTest {
    @Test
    public void verifyTapOnLoginEvent(){
        BasePage basePage = new BasePage(getDriver());
        HomeScreen homeScreen = new HomeScreen(getDriver());
        SoftAssert softAssert = new SoftAssert();
        LoginScreen loginScreen = new LoginScreen(getDriver());
        
        basePage.loadBaseUrl();
        homeScreen.tapOnLoginLink();
        loginScreen.closeLoginPopup();


    }
}
