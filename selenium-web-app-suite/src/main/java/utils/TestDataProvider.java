package utils;

import common.constants.FilePath;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class TestDataProvider {
    String testDataFilePath;
    @DataProvider(name="module1")
    public Object[][] getTestDataForModule1(Method method){
        testDataFilePath = FilePath.TEST_DATA_MODULE_ONE;
        return parseMapToArray(getTestDataFromYml(testDataFilePath, method.getName()));
    }

    @DataProvider(name="module2")
    public Object[][] getTestDataForModule2(Method method){
        testDataFilePath = FilePath.TEST_DATA_MODULE_TWO;
        return parseMapToArray(getTestDataFromYml(testDataFilePath, method.getName()));
    }

    private List<Map<String, Object>> getTestDataFromYml(String testDataFilePath, String testCaseName) {
        Map<String, Object> yamlData = null;
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader(testDataFilePath)) {
            yamlData = yaml.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<Map<String, Object>>) yamlData.get(testCaseName);
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
