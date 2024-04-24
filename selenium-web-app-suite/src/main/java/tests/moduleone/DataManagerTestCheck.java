package tests.moduleone;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.TestDataProvider;

import java.util.List;
import java.util.Map;

public class DataManagerTestCheck extends BaseTest {
    WebDriver driver;
    String testDataFilePath;
    @Test(dataProvider = "module1", dataProviderClass = TestDataProvider.class)
    public void checkTestDataOne(Map<String, Object> testData){
        driver = getDriver();
        driver.get(testData.get("url").toString());
        Assert.assertEquals(driver.getTitle(),testData.get("expectedTitle"));
    }

    @Ignore
    @Test(dataProvider = "module1", dataProviderClass = TestDataProvider.class)
    public void checkTestDataTwo(Map<String, Object> testData){
        System.out.println(testData.get("text"));
    }
}
