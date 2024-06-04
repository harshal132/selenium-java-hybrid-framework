package pages.customerportal;

import common.constants.FilePath;
import common.constants.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import utils.DataLoader;
import java.util.List;
import java.util.Map;

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

    public void setOfficialEmail(String officialEmail){
        By officialEmailLocator = DataLoader.getLocator(addEmployeePageLocators,"officialEmail");
        enterText(officialEmail, officialEmailLocator);
        hardWait(WaitTime.LOW.getTimeInMillis());
    }

    public void setPhoto(String filePath){
        By photoLocator = DataLoader.getLocator(addEmployeePageLocators,"photo");
        getElement(photoLocator).sendKeys(filePath);
        hardWait(WaitTime.LOW.getTimeInMillis());
    }

    public void setMobileNumber(String mobileNumber){
        By mobileNumberLocator = DataLoader.getLocator(addEmployeePageLocators,"mobileNumber");
        getElement(mobileNumberLocator).sendKeys(mobileNumber);
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
            if(element.getText().contains(option)){
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

            enterText(dateOfBirth + Keys.RETURN, dateOfBirthLocator);
        }
        else{
            enterText(dateOfBirth + Keys.RETURN, dateOfBirthLocator);
        }
    }

    public void setMaritalStatus(String maritalStatus) {
        By addEmployeeForm = DataLoader.getLocator(addEmployeePageLocators,"addEmployeeForm");
        By maritalStatusLocator = DataLoader.getLocator(addEmployeePageLocators,"maritalStatusTextBox");
        By maritalListMenuOptions = DataLoader.getLocator(addEmployeePageLocators,"maritalStatusDropDownOptions");

        if(!isDisplayed(true,maritalStatusLocator)){
            jsVerticalScrollTillElementVisible(addEmployeeForm,maritalStatusLocator);
            hardWait(WaitTime.LOW.getTimeInMillis());
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

    public void setPresentAddress(String addressLineValue) {
        By addEmployeeForm = DataLoader.getLocator(addEmployeePageLocators,"addEmployeeForm");
        By addressLine1 = DataLoader.getLocator(addEmployeePageLocators,"presentAddressLine1");
        By addressLine2 = DataLoader.getLocator(addEmployeePageLocators,"presentAddressLine2");

        By city = DataLoader.getLocator(addEmployeePageLocators,"presentAddressCity");
        By state = DataLoader.getLocator(addEmployeePageLocators,"presentAddressState");
        By postalCode = DataLoader.getLocator(addEmployeePageLocators,"presentAddressPostalCode");

        By country = DataLoader.getLocator(addEmployeePageLocators,"presentAddressCountry");
        //By countrySearch = DataLoader.getLocator(addEmployeePageLocators,"presentAddressCountrySearchBox");

        if(!isDisplayed(true,addressLine1)){
            jsVerticalScrollTillElementVisible(addEmployeeForm,addressLine1);
        }
        enterText(addressLineValue,addressLine1);
        enterText(addressLineValue,addressLine2);
        enterText(addressLineValue,city);
        enterText(addressLineValue,state);
        enterText("123",postalCode);
        click(country);
        hardWait(WaitTime.LOW.getTimeInMillis());

        //enterText("India",countrySearch);
        hardWait(WaitTime.LOW.getTimeInMillis());

        By countryOptions = DataLoader.getLocator(addEmployeePageLocators,"presentAddressCountryList");
        tapOnMenuOptionIfPresent(getElements(countryOptions),"India");

    }

    public void setBloodGroup(String bloodGroupValue){
        By addEmployeeForm = DataLoader.getLocator(addEmployeePageLocators,"addEmployeeForm");
        By bloodGroupLocator = DataLoader.getLocator(addEmployeePageLocators,"bloodGroupTextBox");
        By bloodGroupDropDownOptions = DataLoader.getLocator(addEmployeePageLocators,"bloodGroupDropDownOptions");

        if(!isDisplayed(true,bloodGroupLocator)){
            jsVerticalScrollTillElementVisible(addEmployeeForm,bloodGroupLocator);
            hardWait(WaitTime.LOW.getTimeInMillis());
            click(bloodGroupLocator);
            List<WebElement> listOptions = getElements(bloodGroupDropDownOptions);
            tapOnMenuOptionIfPresent(listOptions,bloodGroupValue);
        }
        else{
            click(bloodGroupLocator);
            List<WebElement> listOptions = getElements(bloodGroupDropDownOptions);
            tapOnMenuOptionIfPresent(listOptions,bloodGroupValue);
        }
    }

    public void enterEducationDetails(List<Map<String, Object>> educationDetails) {
        By courseName;
        By instituteName;
        By marksScored;
        By yearOfCompletion;
        By addEmployeeForm = DataLoader.getLocator(addEmployeePageLocators,"addEmployeeForm");

        By addNewEducationButton = DataLoader.getLocator(addEmployeePageLocators,"addNewEducationDetails");
        if(!isDisplayed(true,addNewEducationButton)){
            jsVerticalScrollTillElementVisible(addEmployeeForm,addNewEducationButton);
            hardWait(WaitTime.LOW.getTimeInMillis());
        }

        click(addNewEducationButton);
        hardWait(WaitTime.LOW.getTimeInMillis());

        By addNewEducationRow = DataLoader.getLocator(addEmployeePageLocators,"addNewEducationDetailsLine");

        int count = 1;
        if(educationDetails.size()>0){
            for(Map<String, Object> singleEntry: educationDetails){
                courseName = DataLoader.getLocator(addEmployeePageLocators,"courseDetails", String.valueOf(count));
                enterText(singleEntry.get("course").toString(),courseName);

                instituteName = DataLoader.getLocator(addEmployeePageLocators,"instituteDetails", String.valueOf(count));
                enterText(singleEntry.get("instituteName").toString(),instituteName);

                marksScored = DataLoader.getLocator(addEmployeePageLocators,"marksScored", String.valueOf(count));
                enterText(singleEntry.get("marksScored").toString(),marksScored);

                yearOfCompletion = DataLoader.getLocator(addEmployeePageLocators,"yearOfCompletion", String.valueOf(count));
                enterText(singleEntry.get("yearOfCompletion").toString(),yearOfCompletion);

                if(count < educationDetails.size()){
                    click(addNewEducationRow);
                    hardWait(WaitTime.LOW.getTimeInMillis());
                }
                count++;
            }
        }
    }

    public void enterExperienceDetails(List<Map<String, Object>> workExperience) {
        By addEmployeeForm = DataLoader.getLocator(addEmployeePageLocators,"addEmployeeForm");
        By workExperienceView = DataLoader.getLocator(addEmployeePageLocators,"workExperienceScrollView");
        By previousCompanyName;
        By jobTitle;
        By fromDate;
        By toDate;

        By addNewWorkExperienceButton = DataLoader.getLocator(addEmployeePageLocators,"addNewWorkExperience");
        By addNewWorkExperienceRow = DataLoader.getLocator(addEmployeePageLocators,"addNewWorkExperienceRow");

        if(!isDisplayed(true,addNewWorkExperienceButton)){
            jsVerticalScrollTillElementVisible(addEmployeeForm,addNewWorkExperienceButton);
            hardWait(WaitTime.LOW.getTimeInMillis());
        }

        click(addNewWorkExperienceButton);
        hardWait(WaitTime.LOW.getTimeInMillis());
        int count = 1;
        if(workExperience.size() > 0){
            for(Map<String, Object> singleEntry: workExperience){
                previousCompanyName = DataLoader.getLocator(addEmployeePageLocators,"previousCompanyName", String.valueOf(count));
                if(!isDisplayed(true, previousCompanyName)){
                    jsVerticalScrollTillElementVisible(workExperienceView,previousCompanyName);
                }
                enterText(singleEntry.get("previousCompany").toString(),previousCompanyName);
                hardWait(WaitTime.VLOW.getTimeInMillis());

                jobTitle = DataLoader.getLocator(addEmployeePageLocators,"jobTitle", String.valueOf(count));
                enterText(singleEntry.get("jobTitle").toString(),jobTitle);
                hardWait(WaitTime.VLOW.getTimeInMillis());

                fromDate = DataLoader.getLocator(addEmployeePageLocators,"fromDate", String.valueOf(count));
                enterText(singleEntry.get("fromDate").toString()+Keys.RETURN,fromDate);
                hardWait(WaitTime.VLOW.getTimeInMillis());

                toDate = DataLoader.getLocator(addEmployeePageLocators,"toDate", String.valueOf(count));
                enterText(singleEntry.get("toDate").toString()+Keys.RETURN,toDate);
                hardWait(WaitTime.VLOW.getTimeInMillis());

                if(count < workExperience.size()){
                    click(addNewWorkExperienceRow);
                    hardWait(WaitTime.LOW.getTimeInMillis());
                }
                count++;
            }
        }
    }

    public void enterDependentDetails(List<Map<String, Object>> dependants) {
        By addEmployeeForm = DataLoader.getLocator(addEmployeePageLocators,"addEmployeeForm");
        By addDependentButton = DataLoader.getLocator(addEmployeePageLocators,"addNewDependantButton");
        By firstName;
        By lastName;
        By genderDropdown;
        By genderDropdownOptions;
        By relationshipDropdown;
        By relationshipDropdownOptions;
        By dob;

        By addNewDependentRow = DataLoader.getLocator(addEmployeePageLocators,"addNewDependantRow");

        if(!isDisplayed(true,addDependentButton)){
            jsVerticalScrollTillElementVisible(addEmployeeForm,addDependentButton);
            hardWait(WaitTime.LOW.getTimeInMillis());
        }
        click(addDependentButton);

        int count = 1;
        for(Map<String, Object> singleEntry: dependants){
            firstName = DataLoader.getLocator(addEmployeePageLocators,"dependantFirstName", String.valueOf(count));
            enterText(singleEntry.get("firstName").toString(),firstName);
            hardWait(WaitTime.VLOW.getTimeInMillis());

            lastName = DataLoader.getLocator(addEmployeePageLocators,"dependantLastName", String.valueOf(count));
            enterText(singleEntry.get("lastName").toString(),lastName);
            hardWait(WaitTime.VLOW.getTimeInMillis());

            genderDropdown = DataLoader.getLocator(addEmployeePageLocators,"genderDropdown",String.valueOf(count));
            click(genderDropdown);
            hardWait(WaitTime.VLOW.getTimeInMillis());
            genderDropdownOptions = DataLoader.getLocator(addEmployeePageLocators,"genderDropDownOptions");
            List<WebElement> genderDropdownSelection = getElements(genderDropdownOptions);
            tapOnMenuOptionIfPresent(genderDropdownSelection,singleEntry.get("gender").toString());



            relationshipDropdown = DataLoader.getLocator(addEmployeePageLocators,"relationshipDropdown", String.valueOf(count));
            click(relationshipDropdown);
            hardWait(WaitTime.VLOW.getTimeInMillis());
            relationshipDropdownOptions = DataLoader.getLocator(addEmployeePageLocators,"relationshipDropdownOption");
            List<WebElement> relationshipDropdownSelection = getElements(relationshipDropdownOptions);
            tapOnMenuOptionIfPresent(relationshipDropdownSelection,singleEntry.get("relationship").toString());

            dob = DataLoader.getLocator(addEmployeePageLocators,"dependantDateOfBirth", String.valueOf(count));
            enterText(singleEntry.get("dob").toString()+Keys.ENTER,dob);
            hardWait(WaitTime.VLOW.getTimeInMillis());

            if(count < dependants.size()){
                click(addNewDependentRow);
                hardWait(WaitTime.LOW.getTimeInMillis());
            }
            count++;
        }
    }

    public void clickOnSubmitButton(){
        By addEmployeeForm = DataLoader.getLocator(addEmployeePageLocators,"addEmployeeForm");
        By submitButton = DataLoader.getLocator(addEmployeePageLocators,"submitButton");

        if(!isDisplayed(true,submitButton)){
            jsVerticalScrollTillElementVisible(addEmployeeForm,submitButton);
            hardWait(WaitTime.LOW.getTimeInMillis());
        }
        click(submitButton);
    }
}
