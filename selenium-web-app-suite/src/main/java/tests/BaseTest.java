package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import common.listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import common.constants.Browser;
import common.constants.DriverType;
import common.constants.Environment;
import common.constants.FilePath;
import config.WebDriverFactory;
import org.testng.annotations.Optional;
import utils.DataLoader;
import utils.ExtentTestManager;

@Listeners(TestListener.class)
public class BaseTest {
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    public static Environment testEnvType;
    public static Browser browserName;
    public static DriverType driverType;
    public static boolean disableBrowserLocation=false;
    public static List<String> disableBrowserLocationTestCases = new ArrayList<>();

    @Parameters({ "EnvType", "DriverType" })
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("qa") String EnvType, @Optional("local") String testDriverType) {
        testEnvType = Environment.get(EnvType);
        driverType = DriverType.get(testDriverType);
        System.out.println("Suite running on environment: " + EnvType);
        System.out.println("Suite running on driver type: " + testDriverType);
        disableBrowserLocationTestCases = Arrays.asList(Objects.requireNonNull(DataLoader.getAppData(FilePath.REAL_APP_DATA_FILE_PATH, "disableBrowserLocationTestCases")).split(","));
    }

    @Parameters({ "Browser" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method m, @Optional("chrome") String browser, ITestContext context) throws Exception {
        browserName = Browser.get(browser);
        System.out.println("Test running on browser: " + browser);
        System.out.println("Method name: " + m.getName()); // Prints name of the test
        disableBrowserLocation= isBrowserLocationDisabledForTestCase(m.getName());
        WebDriver driver = WebDriverFactory.getWebDriver(driverType, browserName);
        setDriver(driver);
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void removeDriver() {
        threadLocalDriver.remove();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        disableBrowserLocation=false;

        if (result.isSuccess()) {
            if (getDriver() != null) {
                getDriver().quit();
                removeDriver();
            }
        }
    }

    public static boolean isMobileWeb() {
        try {
            boolean flag;
            flag = browserName.isAndroidChrome() || browserName.isIphoneChrome();
            return flag;
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get mobile test browser status");
            System.out.println(e.toString());
            throw e;
        }
    }

    public static boolean isQaTest() {
        try {
            boolean flag;
            flag = testEnvType.isQaEnv();
            return flag;
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get QA environment status");
            System.out.println(e.toString());
            throw e;
        }
    }

    public static boolean isProdTest() {
        try {
            boolean flag;
            flag = testEnvType.isProdEnv();
            return flag;
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Prod environment status");
            System.out.println(e.toString());
            throw e;
        }
    }

    public static boolean isBrowserLocationDisabledForTestCase(String testcaseName) {
        try {
            if(disableBrowserLocationTestCases.contains(testcaseName))
                return true;
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Browser Location On/Off status");
            System.out.println(e.toString());
            throw e;
        }
        return false;
    }

    public static void logTestStep(String description) {
        try{
            ExtentTestManager.getTest().log(Status.INFO,description, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build());
        }catch(IOException e){
            System.out.println("Failed to capture screenshot"+e.getMessage());
        }
    }

    public static void logTestStep(String description, By locator) {
        try{
            ExtentTestManager.getTest().log(Status.INFO,description, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotOfElement(locator)).build());
        }catch(IOException e){
            System.out.println("Failed to capture screenshot"+e.getMessage());
        }
    }

    public static String takeScreenshot() throws IOException {
        File screenshotFile = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);
        FileInputStream fileInputStream = new FileInputStream(screenshotFile);
        byte[] bytes = new byte[(int) screenshotFile.length()];
        fileInputStream.read(bytes);
        return new String(Base64.getEncoder().encode((bytes)));
    }

    public static String takeScreenshotOfElement(By locator) throws IOException {
        File screenshotFile = ((TakesScreenshot) BaseTest.getDriver().findElement(locator)).getScreenshotAs(OutputType.FILE);
        FileInputStream fileInputStream = new FileInputStream(screenshotFile);
        byte[] bytes = new byte[(int) screenshotFile.length()];
        fileInputStream.read(bytes);
        return new String(Base64.getEncoder().encode((bytes)));
    }
}
