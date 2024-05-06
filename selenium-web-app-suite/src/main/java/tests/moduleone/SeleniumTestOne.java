package tests.moduleone;

import common.constants.FilePath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.BasePage;
import tests.BaseTest;
import utils.DataLoader;


public class SeleniumTestOne extends BaseTest {
    String moduleOnePageOneLocator = FilePath.REAL_LOCATORS_MODULE_ONE_PAGE_ONE;
    @Ignore
    @Test(description = "Check Framework functions", groups = { "regression", "sanity" })
    public void testOne(){
        // System.out.println(SeleniumManager.getInstance().getDriverPath(options,true));
        WebDriver driver = getDriver();
        driver.get("https://www.facebook.com");
        logTestStep("Web page opened with title - "+driver.getTitle());
    }

    @Test(description = "Check Framework functions", groups = { "regression", "sanity" })
    public void testTwo(){
        WebDriver driver = getDriver();
        driver.get("https://www.google.com");
        logTestStep("Web page opened with title - "+driver.getTitle());
    }

    @Test(description = "Get Screenshot of Particular element", groups = { "regression", "sanity" })
    public void testFour(){
        WebDriver driver = getDriver();
        driver.get("https://www.google.com");

        logTestStep("Web page opened with title - "+driver.getTitle());
        if(BaseTest.isMobileWeb()){
            logTestStep("Screenshot captured for particular element", DataLoader.getLocatorMobile(moduleOnePageOneLocator,"googleVoiceSearch"));
        }else{
            logTestStep("Screenshot captured for particular element", DataLoader.getLocator(moduleOnePageOneLocator,"googleVoiceSearch"));
        }
    }

    @Test(description = "Check management of different tabs", groups = { "sanity" })
    public void testThree() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        logTestStep("Web page opened with title - "+driver.getTitle());

        driver.findElement(By.cssSelector("#openwindow")).click();
        Thread.sleep(2000);
        for(String s: driver.getWindowHandles()){
            if(!s.equals(driver.getWindowHandle())){
                driver.switchTo().window(s);
                System.out.println(driver.getTitle());
            }
        }

    }

//    /**
//     * @author Arzoo Hingorani
//     */
//    @Test(description = "C5567 | To check \"Report\" a review form Reviews section on the business page logged in from a different user account",
//            groups = { "businessPageTests", "regression", "noprod" })
//    public void validateReportAReviewOperation() {
//        BasePage basePage = new BasePage(getDriver());
//        HomePage homePage = new HomePage(getDriver());
//        HeaderPage headerPage = new HeaderPage(getDriver());
//        BusinessPage businessPage = new BusinessPage(getDriver());
//
//        // Variables
//        int reviewIndex = 1;
//
//        basePage.loadUrl(true, getData(businessPageData, "business.url.fast.link.accurate.testing.lab"));
//        homePage.clickCloseForAllPopups();
//        homePage.clickDoLaterOnNotificationPopup();
//
//        // Login to HiDubai PP
//        headerPage.clickLoginLink();
//        headerPage.performLogin(getData(applicationData, "no.claimed.biz.user3.email"), getData(applicationData, "password"));
//        homePage.clickChatPopUpCloseButton();
//        testassert.assertTrue(headerPage.validateProfileAvatarIsDisplayed(true),
//                "Profile icon displayed");
//
//        businessPage.clickBusinessMenuTab(getLocator(businessPageData, "review.tab"));
//        businessPage.clickSpecifiedReviewsOptionButton(reviewIndex);
//        testassert.assertTrue(businessPage.clickOnOptionFromReviewMenuOptions(reviewIndex, getLocator(businessPageData,"review.options.menu.report")),
//                "Clicked on Report button for 1st random review");
//
//        testassert.assertTrue(businessPage.selectOptionToReportReview(getLocator(businessPageData,"review.report.option.inappropriate")),
//                "The option '" + getLocator(businessPageData,"review.report.option.inappropriate") + " is selected");
//
//        testassert.assertTrue(businessPage.clickSendButtonOnReportReviewPopup(),
//                "Clicked on Send button on Report Review popup");
//
//        testassert.assertTrue(businessPage.validatePopupMessageIsDisplayed(getLocator(businessPageData, "ui.notification.report.review.message")),
//                "Verified a message popup is displayed with text - " + getLocator(businessPageData, "ui.notification.report.review.message"));
//
//    }
}
