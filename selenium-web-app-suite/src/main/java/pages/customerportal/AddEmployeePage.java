package pages.customerportal;

import common.constants.FilePath;
import common.constants.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import utils.DataLoader;

import java.util.List;

public class AddEmployeePage extends BasePage {
    final String addEmployeePageLocators = FilePath.CUSTOMER_ADD_EMPLOYEE_LOCATORS;
    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName){
        By firstNameLocator = DataLoader.getLocator(addEmployeePageLocators,"firstName");
        enterText(firstName, firstNameLocator);
        hardWait(WaitTime.LOW.getTimeInMillis());
    }

    public void setLastName(String lastName){
        By lastNameLocator = DataLoader.getLocator(addEmployeePageLocators,"lastName");
        enterText(lastName, lastNameLocator);
        hardWait(WaitTime.LOW.getTimeInMillis());
    }

    public void setPersonalEmail(String personalEmail){
        By personalEmailLocator = DataLoader.getLocator(addEmployeePageLocators,"personalEmail");
        enterText(personalEmail, personalEmailLocator);
        hardWait(WaitTime.LOW.getTimeInMillis());
    }

    public void setPhoto(String filePath){
        By photoLocator = DataLoader.getLocator(addEmployeePageLocators,"photo");
        getElement(photoLocator).sendKeys(filePath);
        hardWait(WaitTime.LOW.getTimeInMillis());
    }

    public void setMobileNumber(long mobileNumber){
        By mobileNumberLocator = DataLoader.getLocator(addEmployeePageLocators,"mobileNumber");
        getElement(mobileNumberLocator).sendKeys(Long.toString(mobileNumber));
        hardWait(WaitTime.LOW.getTimeInMillis());
    }

    public void setGender(String gender){
        By genderRadio = DataLoader.getLocator(addEmployeePageLocators,"gender");
        List<WebElement> genderRadioButton = getElements(genderRadio);
        for(WebElement element: genderRadioButton){
            if(element.getAttribute("value").equals(gender) && !element.isSelected()){
                element.click();
                break;
            }
        }
    }

    public void setMobileNumber(String number) {
    }
}
