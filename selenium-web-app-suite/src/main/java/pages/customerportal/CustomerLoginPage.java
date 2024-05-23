package pages.customerportal;

import common.constants.FilePath;
import common.constants.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.DataLoader;

public class CustomerLoginPage extends BasePage {
    final String customerLoginPageLocators = FilePath.CUSTOMER_LOGINPAGE_LOCATORS;
    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    // REMOVE CODE

    public void performLogin(String userName, String passWord) {
        By iFrameLocator = DataLoader.getLocator(customerLoginPageLocators,"signInFrame");

        waitForElementToBeVisible(iFrameLocator,WaitTime.MEDIUM.getTimeInMillis());
        driver.switchTo().frame(getElement(iFrameLocator));

        By userEmail = DataLoader.getLocator(customerLoginPageLocators,"userEmailTextBox");
        waitForElementToBeVisible(userEmail,WaitTime.MEDIUM.getTimeInMillis());

        // Login steps
        enterUserEmail(userName); // Enters user email
        tapOnNextButton(); // Tap on next button
        enterPassword(passWord); // Enters user password
        tapOnNextButton(); // Tap on sign in button
        driver.switchTo().defaultContent();
    }

    private void enterPassword(String passWord) {
        By password = DataLoader.getLocator(customerLoginPageLocators,"passwordTextBox");
        waitForElementToBeVisible(password,WaitTime.LOW.getTimeInMillis());
        enterText(passWord, password);
        hardWait(WaitTime.VLOW.getTimeInMillis());
    }

    public void tapOnNextButton() {
        By nextButton = DataLoader.getLocator(customerLoginPageLocators,"nextButton");
        waitForElementToBeClickable(nextButton,WaitTime.LOW.getTimeInMillis());
        click(nextButton);
        hardWait(WaitTime.VLOW.getTimeInMillis());
    }

    public void enterUserEmail(String userName){
        By userEmail = DataLoader.getLocator(customerLoginPageLocators,"userEmailTextBox");
        enterText(userName, userEmail);
        hardWait(WaitTime.VLOW.getTimeInMillis());
    }

    public void waitForEmployeeManagementPageToLoad(){
        By webAppHeading = DataLoader.getLocator(customerLoginPageLocators,"employeeManagementHeading");
        waitForElementToBeVisible(webAppHeading, WaitTime.HIGH.getTimeInMillis());
    }

    public String getWebAppHeading(){
        By webAppHeading = DataLoader.getLocator(customerLoginPageLocators,"employeeManagementHeading");
        return getText(webAppHeading);
    }

    // REMOVE CODE

}
