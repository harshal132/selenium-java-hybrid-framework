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
    @DataProvider(name="adminportal")
    public Object[][] getTestDataForAdminPortal(Method method, ITestContext context){
        testDataFilePath = FilePath.adminPortalTestData;
        return parseMapToArray(getTestDataFromYml(testDataFilePath, method.getName(),context));
    }

    @DataProvider(name="publicportal")
    public Object[][] getTestDataForPublicPortal(Method method, ITestContext context){
        testDataFilePath = FilePath.publicPortalTestData;
        return parseMapToArray(getTestDataFromYml(testDataFilePath, method.getName(),context));
    }

    @DataProvider(name="customerPortal")
    public Object[][] getTestDataForCustomerPortal(Method method, ITestContext context){
        testDataFilePath = FilePath.customerPortalTestData;
        return parseMapToArray(getTestDataFromYml(testDataFilePath, method.getName(),context));
    }

    private List<Map<String, Object>> getTestDataFromYml(String testDataFilePath, String testCaseName, ITestContext context) {
        Map<String, Object> yamlData = null;
        String testEnvironment = context.getCurrentXmlTest().getParameter("EnvType");
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader(testDataFilePath)) {
            yamlData = yaml.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(testEnvironment!=null && testEnvironment.equals("prod")){
            yamlData = (Map<String, Object>) yamlData.get(testCaseName);
            return (List<Map<String, Object>>) yamlData.get("prod");
        }
        else{
            yamlData = (Map<String, Object>) yamlData.get(testCaseName);
            return (List<Map<String, Object>>) yamlData.get("qa");
        }
    }

    public static Object[][] parseMapToArray(List<Map<String, Object>> interimResults) {
        Object[][] results = new Object[interimResults.size()][1];
        int index = 0;
        for (Map<String, Object> interimResult : interimResults) {
            results[index++] = new Object[] {interimResult};
        }
        return results;
    }
}
