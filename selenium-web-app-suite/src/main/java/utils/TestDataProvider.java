package utils;

import common.constants.FilePath;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class TestDataProvider {
    String testDataFilePath;
    static DataLoader dataLoader;

    public TestDataProvider(){
        dataLoader = new DataLoader();
    }
    @DataProvider(name="publicPortal")
    public Object[][] getTestDataForPublicPortal(Method method, ITestContext context){
        testDataFilePath = FilePath.TEST_DATA_MODULE_ONE;
        return dataLoader.parseMapToArray(dataLoader.getTestDataFromYml(testDataFilePath, method.getName(),context));
    }

    @DataProvider(name="customerPortal")
    public Object[][] getTestDataForCustomerPortal(Method method, ITestContext context){
        testDataFilePath = FilePath.TEST_DATA_MODULE_TWO;
        return dataLoader.parseMapToArray(dataLoader.getTestDataFromYml(testDataFilePath, method.getName(),context));
    }

    @DataProvider(name="adminPortal")
    public Object[][] getTestDataForAdminPortal(Method method, ITestContext context){
        testDataFilePath = FilePath.TEST_DATA_MODULE_TWO;
        return dataLoader.parseMapToArray(dataLoader.getTestDataFromYml(testDataFilePath, method.getName(),context));
    }
}
