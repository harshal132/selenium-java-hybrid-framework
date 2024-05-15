package tests.accounts;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.accounts.HomeScreen;
import tests.BaseTest;

public class HomeScreenTests extends BaseTest {
    @Test
    public void verifyTapOnLoginEvent(){
        BasePage basePage = new BasePage(getDriver());
        HomeScreen homeScreen = new HomeScreen(getDriver());
        SoftAssert softAssert = new SoftAssert();
    }
}
