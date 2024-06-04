package tests.customerportal;

import common.constants.FilePath;
import common.constants.WaitTime;
import org.testng.annotations.Test;
import pages.customerportal.AddEmployeePage;
import pages.customerportal.CustomerLoginPage;
import pages.customerportal.CustomerPortalHomePage;
import tests.BaseTest;
import utils.DataLoader;
import utils.TestDataProvider;

import java.util.List;
import java.util.Map;

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

    @Test(description = "Complete add employee form", dataProviderClass = TestDataProvider.class, dataProvider = "customerportal")
    public void completeAddEmployeeForm(Map<String, Object> testData) throws InterruptedException{

        AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        customerLoginPage.performLogin(DataLoader.getAppData(commonDataFile,"userCredentials.qa.zohoAdminUser.username"),DataLoader.getAppData(commonDataFile,"userCredentials.qa.zohoAdminUser.password"));

        CustomerPortalHomePage customerPortalHomePage = new CustomerPortalHomePage(getDriver());
        customerPortalHomePage.tapOnAdminTab();
        customerPortalHomePage.tapOnAddEmployeeFromMenu();

        // Basic information
        addEmployeePage.setFirstName(testData.get("firstName").toString());
        addEmployeePage.setLastName(testData.get("lastName").toString());
        addEmployeePage.setPersonalEmail(testData.get("personalEmail").toString());
        addEmployeePage.setOfficialEmail(testData.get("officialEmail").toString());
        addEmployeePage.setGender(testData.get("gender").toString());
        addEmployeePage.setMobileNumber(testData.get("mobileNumber").toString());
        addEmployeePage.setPhoto(FilePath.IMAGES_DATA_DIR+testData.get("photo").toString());

        // Work information
        addEmployeePage.setEmployeeId(testData.get("employeeId").toString());
        addEmployeePage.setLocation(testData.get("location").toString());
        addEmployeePage.setEmployeeType(testData.get("employeeType").toString());
        addEmployeePage.setDepartment(testData.get("department").toString());
        addEmployeePage.setDesignation(testData.get("designation").toString());


        //Personal Information
        addEmployeePage.setDateOfBirth(testData.get("dob").toString());
        addEmployeePage.setMaritalStatus(testData.get("maritalStatus").toString());
        addEmployeePage.setPresentAddress(testData.get("presentAddress").toString());
        addEmployeePage.setBloodGroup(testData.get("bloodGroup").toString());

        // Education details
        addEmployeePage.enterEducationDetails((List<Map<String, Object>>) testData.get("educationDetails"));

        // Work Experience
       addEmployeePage.enterExperienceDetails((List<Map<String, Object>>) testData.get("workExperience"));
        // Dependents
        addEmployeePage.enterDependentDetails((List<Map<String, Object>>) testData.get("dependants"));

        // Submit Form
        addEmployeePage.clickOnSubmitButton();
        Thread.sleep(WaitTime.VHIGH.getTimeInMillis());
    }

}
