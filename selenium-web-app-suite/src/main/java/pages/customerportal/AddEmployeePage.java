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

    public void setEmployeeId(String number) {
        By employeeIdLocators = DataLoader.getLocator(addEmployeePageLocators,"employeeId");
        enterText(number, employeeIdLocators);
    }

    public boolean tapOnMenuOptionIfPresent(List<WebElement> elements, String option){
        if(elements.isEmpty()){
            return false;
        }
        for(WebElement element: elements){
            if(element.getText().equals(option)){
                click(element);
                return true;
            }
        }
        return false;
    }

    public void setLocation(String location) {
        // Tap on location
        By locationFieldTextBox = DataLoader.getLocator(addEmployeePageLocators,"locationTextBox");
        click(locationFieldTextBox);

        // Get available locations
        By listMenuOptions = DataLoader.getLocator(addEmployeePageLocators,"locationDropDownOptions");
        List<WebElement> listOptions = getElements(listMenuOptions);

        if(!tapOnMenuOptionIfPresent(listOptions,location)){
            // if location not already added -> add new location
            addNewEntryInDataBase(location);
        }
    }



    private void addNewEntryInDataBase(String newValue) {
        By addNewLocation = DataLoader.getLocator(addEmployeePageLocators,"addNewButton");
        click(addNewLocation);
        hardWait(WaitTime.LOW.getTimeInMillis());

        By newLocationTextBox = DataLoader.getLocator(addEmployeePageLocators,"addNewValueTextBoxPopup");
        waitForElementToBeClickable(newLocationTextBox,WaitTime.MEDIUM.getTimeInMillis());
        enterText(newValue,newLocationTextBox);
        hardWait(WaitTime.LOW.getTimeInMillis());

        By submitPopupForm = DataLoader.getLocator(addEmployeePageLocators,"submitAddNewPopupForm");
        click(submitPopupForm);
        hardWait(WaitTime.LOW.getTimeInMillis());
    }

    public void setEmployeeType(String employeeType) {
        // Tap on employee type
        By locationFieldTextBox = DataLoader.getLocator(addEmployeePageLocators,"employeeTypeTextBox");
        click(locationFieldTextBox);

        // Get available employee type
        By listMenuOptions = DataLoader.getLocator(addEmployeePageLocators,"employeeTypeDropDownOptions");
        List<WebElement> listOptions = getElements(listMenuOptions);

        if(!tapOnMenuOptionIfPresent(listOptions,employeeType)){
            // if employee type not already added -> add new employee type
            addNewEntryInDataBase(employeeType);
        }
    }

    public void setDepartment(String departmentName) {
        By departmentFieldTextBox = DataLoader.getLocator(addEmployeePageLocators,"departmentNameTextBox");
        click(departmentFieldTextBox);

        // Get available employee type
        By listMenuOptions = DataLoader.getLocator(addEmployeePageLocators,"departmentDropDownOptions");
        List<WebElement> listOptions = getElements(listMenuOptions);

        if(!tapOnMenuOptionIfPresent(listOptions,departmentName)){
            // if employee type not already added -> add new employee type
            addNewEntryInDataBase(departmentName);
        }
    }

    public void setDesignation(String designation) {
        By departmentFieldTextBox = DataLoader.getLocator(addEmployeePageLocators,"designationTextBox");
        click(departmentFieldTextBox);

        // Get available employee type
        By listMenuOptions = DataLoader.getLocator(addEmployeePageLocators,"designationDropDownOptions");
        List<WebElement> listOptions = getElements(listMenuOptions);

        if(!tapOnMenuOptionIfPresent(listOptions,designation)){
            // if employee type not already added -> add new employee type
            addNewEntryInDataBase(designation);
        }
    }

    public void setDateOfBirth(String dateOfBirth) {
        By dateOfBirthLocator = DataLoader.getLocator(addEmployeePageLocators,"dateOfBirth");
        By addEmployeeForm = DataLoader.getLocator(addEmployeePageLocators,"addEmployeeForm");
        if(!isDisplayed(true,dateOfBirthLocator)){
            jsVerticalScrollTillElementVisible(addEmployeeForm,dateOfBirthLocator);
            enterText(dateOfBirth, dateOfBirthLocator);
        }
        else{
            enterText(dateOfBirth, dateOfBirthLocator);
        }
    }

    public void setMaritalStatus(String maritalStatus) {
        By addEmployeeForm = DataLoader.getLocator(addEmployeePageLocators,"addEmployeeForm");
        By maritalStatusLocator = DataLoader.getLocator(addEmployeePageLocators,"maritalStatusTextBox");
        By maritalListMenuOptions = DataLoader.getLocator(addEmployeePageLocators,"maritalStatusDropDownOptions");

        if(!isDisplayed(true,maritalStatusLocator)){
            jsVerticalScrollTillElementVisible(addEmployeeForm,maritalStatusLocator);
            click(maritalStatusLocator);
            List<WebElement> listOptions = getElements(maritalListMenuOptions);
            tapOnMenuOptionIfPresent(listOptions,maritalStatus);
        }
        else{
            click(maritalStatusLocator);
            List<WebElement> listOptions = getElements(maritalListMenuOptions);
            tapOnMenuOptionIfPresent(listOptions,maritalStatus);
        }
    }
}
