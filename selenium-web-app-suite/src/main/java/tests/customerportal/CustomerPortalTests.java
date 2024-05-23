package tests.customerportal;

import common.constants.FilePath;
import common.constants.WaitTime;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.customerportal.AddEmployeePage;
import pages.customerportal.CustomerLoginPage;
import pages.customerportal.CustomerPortalHomePage;
import tests.BaseTest;
import utils.DataLoader;

public class CustomerPortalTests extends BaseTest {
    final String commonDataFile = FilePath.COMMON_TEST_DATA_FILE_PATH;
    final String appDataFile = FilePath.APP_DATA_FILE_PATH;
    @Test(description = "Verify employee management title")
    public void loadCustomerPortal(){
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        customerLoginPage.performLogin(DataLoader.getAppData(commonDataFile,"userCredentials.qa.zohoAdminUser.username"),DataLoader.getAppData(commonDataFile,"userCredentials.qa.zohoAdminUser.password"));
        customerLoginPage.waitForEmployeeManagementPageToLoad();
        softassert.assertEquals(DataLoader.getAppData(appDataFile,"appHeading"), customerLoginPage.getWebAppHeading(), "Verified web app heading");
    }

    @Test(description = "Complete add employee form")
    public void completeAddEmployeeForm() throws InterruptedException{

        AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        customerLoginPage.performLogin(DataLoader.getAppData(commonDataFile,"userCredentials.qa.zohoAdminUser.username"),DataLoader.getAppData(commonDataFile,"userCredentials.qa.zohoAdminUser.password"));

        CustomerPortalHomePage customerPortalHomePage = new CustomerPortalHomePage(getDriver());
        customerPortalHomePage.tapOnAdminTab();
        customerPortalHomePage.tapOnAddEmployeeFromMenu();

        addEmployeePage.setFirstName("Harshal");
        addEmployeePage.setLastName("Chavan");
        addEmployeePage.setPersonalEmail("harshal@yopmail.com");
        addEmployeePage.setGender("Male");
        addEmployeePage.setMobileNumber(556939352);
        addEmployeePage.setPhoto(FilePath.IMAGES_DATA_DIR+"test.jpg");

        Thread.sleep(WaitTime.VHIGH.getTimeInMillis());
    }

}
