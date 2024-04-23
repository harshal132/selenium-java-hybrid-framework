package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import common.constants.Browser;
import common.constants.DriverType;
import common.constants.Environment;
import common.constants.FilePath;
import config.WebDriverFactory;
import utils.DataLoader;

public class BaseTest {
    protected final String applicationData = (FilePath.APPLICATION_DATA);
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
    public static Environment testEnvType;
    public static Browser browserName;
    public static DriverType driverType;
    public static boolean disableBrowserLocation=false;
    public static List<String> disableBrowserLocationTestCases = new ArrayList<String>();

    @Parameters({ "EnvType", "DriverType" })
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("qa") String EnvType, @Optional("local") String testDriverType) throws IOException {
        testEnvType = Environment.get(EnvType);
        driverType = DriverType.get(testDriverType);
        System.out.println("Suite running on environment: " + EnvType);
        System.out.println("Suite running on driver type: " + testDriverType);
        disableBrowserLocationTestCases = Arrays.asList(DataLoader.getAppData(FilePath.REAL_APP_DATA_FILE_PATH, "disableBrowserLocationTestCases").split(","));
    }

    @Parameters({ "Browser" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method m, @Optional("chrome") String browser, ITestContext context) throws Exception {
        browserName = Browser.get(browser);
        System.out.println("Test running on browser: " + browser);
        if(isBrowserLocationDisabledForTestCase(m.getName()))
            disableBrowserLocation=true;
        else
            disableBrowserLocation=false;

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
            if(disableBrowserLocationTestCases.indexOf(testcaseName) != -1)
                return true;
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Browser Location On/Off status");
            System.out.println(e.toString());
            throw e;
        }
        return false;
    }
}
