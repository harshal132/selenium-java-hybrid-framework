package tests.customerportal;

import common.constants.FilePath;
import common.constants.WaitTime;
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

        // Basic information
        addEmployeePage.setFirstName("Harshal");
        addEmployeePage.setLastName("Chavan");
        addEmployeePage.setPersonalEmail("harshal@yopmail.com");
        addEmployeePage.setGender("Male");
        addEmployeePage.setMobileNumber(556939352);
        addEmployeePage.setPhoto(FilePath.IMAGES_DATA_DIR+"test.jpg");

        // Work information
        addEmployeePage.setEmployeeId("189861");
        addEmployeePage.setLocation("Delhi");
        addEmployeePage.setEmployeeType("Full Time");
        addEmployeePage.setDepartment("Management");
        addEmployeePage.setDesignation("Software Tester");

//        addEmployeePage.setEmployeeStatus("");
//        addEmployeePage.setEmployeeLevel("");
//        addEmployeePage.setCtc("");
//        addEmployeePage.setDateOfJoining("");
//        addEmployeePage.setSeatLocation("");
//        addEmployeePage.setWorkExperience("");
//        addEmployeePage.setIsReportingManager(false);

        //Personal Information
        addEmployeePage.setDateOfBirth("24-July-1998");
        addEmployeePage.setMaritalStatus("Single");
//        addEmployeePage.setPresentAddress();
//        addEmployeePage.setBloodGroup();
        Thread.sleep(WaitTime.VHIGH.getTimeInMillis());
    }

}
