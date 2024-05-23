package pages.customerportal;

import common.constants.FilePath;
import common.constants.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.DataLoader;

public class CustomerPortalHomePage extends BasePage {
    final String customerHomePageLocators = FilePath.CUSTOMER_HOMEPAGE_LOCATORS;
    public CustomerPortalHomePage(WebDriver driver) {
        super(driver);
    }

    public void tapOnAdminTab(){
        hardWait(WaitTime.MEDIUM.getTimeInMillis());
        By addEmployeeTab = DataLoader.getLocator(customerHomePageLocators,"adminTab");
        waitForElementToBeClickable(addEmployeeTab, WaitTime.MEDIUM.getTimeInMillis());
        click(addEmployeeTab);
        hardWait(WaitTime.LOW.getTimeInMillis());
    }

    public void tapOnAddEmployeeFromMenu(){
        By addEmployeeMenu = DataLoader.getLocator(customerHomePageLocators,"addEmployeeFromMenu");
        waitForElementToBeVisible(addEmployeeMenu,WaitTime.LOW.getTimeInMillis());
        click(addEmployeeMenu);
    }
}
