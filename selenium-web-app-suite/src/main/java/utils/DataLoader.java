package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.yaml.snakeyaml.Yaml;

public class DataLoader {
    public Map<String, String> readLocatorYaml(String filePath, String locatorName) {
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        Map<String, HashMap<String, String>> obj = null;
        try {
            inputStream = new FileInputStream(filePath);
            obj = yaml.<Map<String, HashMap<String, String>>>load(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return obj.get(locatorName);
    }

    public static String getAppData(String filePath, String keyName) {
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        Map<String, Object> obj = null;
        try {
            inputStream = new FileInputStream(filePath);
            obj = yaml.load(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(obj == null){
            return null;
        }else{
            return getValueByKey(obj,keyName);
        }
    }

    private static String getValueByKey(Map<String, Object> yamlData, String keyName) {
        String[] keys = keyName.split("\\.");
        Map<String, Object> currentMap = yamlData;
        for (String k : keys) {
            Object obj = currentMap.get(k);
            if (obj instanceof Map) {
                currentMap = (Map<String, Object>) obj;
            } else {
                return obj.toString();
            }
        }
        return null; // Key not found
    }

    public Map<String, String> getTestData(String testName, String filePath, String locatorName) {
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        Map<String, HashMap<String, String>> obj = null;
        try {
            inputStream = new FileInputStream(filePath);
            obj = yaml.<Map<String, HashMap<String, String>>>load(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return obj.get(locatorName);
    }

    public String getValueAfterReplacement(String locatorValue, String ... replacement) {
        String locatorSplitArray[] = locatorValue.split("}", 2);
        String finalLocatorString;
        if(replacement.length ==2) {
            String locator1 = (locatorSplitArray[0].concat("}")).replaceFirst("\\$\\{.*\\}", replacement[0]);
            String locator2 = locatorSplitArray[1].replaceFirst("\\$\\{.*\\}", replacement[1]);
            finalLocatorString = locator1 + locator2;
        }
        else
            finalLocatorString = locatorValue.replaceFirst("\\$\\{.*\\}", replacement[0]);
        return finalLocatorString;
    }

    public By getLocator(String filePath, String locatorName, String ...replacement) {
        Map<String, String> locatorMap = readLocatorYaml(filePath, locatorName);
        String value;
        if(replacement.length!= 0)
            value = getValueAfterReplacement(locatorMap.get("value").toString(), replacement);
        else
            value = locatorMap.get("value").toString();
        return getLocatorType(locatorMap.get("locatorType").toString(), value);
    }

    public By getLocatorMobile(String filePath, String locatorName, String ...replacement) {
        Map<String, String> locatorMap = readLocatorYaml(filePath, locatorName);
        String value;
        if(replacement.length!= 0)
            value = getValueAfterReplacement(locatorMap.get("valueMobile").toString(), replacement);
        else
            value = locatorMap.get("valueMobile").toString();
        return getLocatorType(locatorMap.get("locatorType").toString(), value);
    }

    public By getLocatorType(String yamlLocatorType, String locatorValue) {
        By locator = switch (yamlLocatorType.toLowerCase()) {
            case "xpath" -> By.xpath(locatorValue);
            case "css" -> By.cssSelector(locatorValue);
            case "class" -> By.className(locatorValue);
            case "id" -> By.id(locatorValue);
            case "linktext" -> By.linkText(locatorValue);
            case "name" -> By.name(locatorValue);
            case "partiallinktext" -> By.partialLinkText(locatorValue);
            case "tagname" -> By.tagName(locatorValue);
            default -> By.xpath(locatorValue);
        };
        return locator;
    }

}

