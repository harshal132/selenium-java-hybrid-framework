package tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import common.constants.Browser;
import common.constants.DriverType;
import common.constants.Environment;
import common.constants.FilePath;
import config.WebDriverFactory;

public class BaseTest {
    protected final String applicationData = (FilePath.APPLICATION_DATA);
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
    public static Environment testEnvType;
    public static Browser browserName;
    public static DriverType driverType;
    public static boolean disableBrowserLocation=false;
    public static List<String> disableBrowserLocationTestCases = new ArrayList<String>();

    /**
     * Will be run before suite to clear screenshots folder
     */
    @Parameters({ "EnvType", "DriverType" })
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("qa") String EnvType, @Optional("local") String testDriverType) {
        try {
//            final String path = FilePath.FAILED_TEST_SCREENSHOT_DIR;
//            final String path = FilePath.FAILED_TEST_SCREENSHOT_DIR;
//            if (Files.exists(Paths.get(path))) {
//                FileUtils.cleanDirectory(new File(path));
//                FileUtils.deleteDirectory(new File(path));
//            }
//            PropertyConfigurator.configure(FilePath.LOG4J_PROPERTIES);
//            logger = Logger.getLogger(BaseTest.class);
            testEnvType = Environment.get(EnvType);
            driverType = DriverType.get(testDriverType);
            //logger.info("Suite running on environment: " + EnvType);
            //logger.info("Suite running on driver type: " + testDriverType);

            disableBrowserLocationTestCases = Arrays.asList(getLocator(applicationData, "tests.for.disabled.browser.location").split(","));
        } catch (IOException e) {
            //logger.error(e);
        }
    }

    /**
     * Will be run before every test to initiate driver
     *
     * @throws Exception
     */
    @Parameters({ "Browser" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method m, @Optional("chrome") String browser, ITestContext context) throws Exception {
        browserName = Browser.get(browser);
        // logger.info("Test running on browser: " + browser);
        if(isBrowserLocationDisabledForTestCase(m.getName()))
            disableBrowserLocation=true;
        else
            disableBrowserLocation=false;

        WebDriver driver = WebDriverFactory.getWebDriver(driverType, browserName);
        setDriver(driver);
        // logger.info("Before Test: " + m.getName() + " with Thread ID: " + Thread.currentThread().getId());
    }

    /**
     * set thread-safe driver
     */
    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    /**
     * get thread-safe driver
     */
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    /**
     * remove thread-safe driver
     */
    public static void removeDriver() {
        threadLocalDriver.remove();
    }

    /**
     * Will generate failed screenshots after every failed test Will close driver
     * after every passed test
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        disableBrowserLocation=false;

        if (result.isSuccess()) {
            if (getDriver() != null) {
                getDriver().quit();
                // logger.info("After Test: " + result.getName() + " with Thread ID: " + Thread.currentThread().getId());
                removeDriver();
            }
        }
    }

    public static boolean isMobileWeb() {
        try {
            boolean flag;
            flag = (browserName.isAndroidChrome()) || browserName.isIphoneChrome() ? true : false;
            return flag;
        } catch (Exception e) {
            // logger.error("Exception reached: Could not get mobile test browser status");
            // logger.error(e.toString());
            throw e;
        }
    }

    public static boolean isQaTest() {
        try {
            boolean flag;
            flag = (testEnvType.isQaEnv()) ? true : false;
            return flag;
        } catch (Exception e) {
            // logger.error("Exception reached: Could not get QA environment status");
            // logger.error(e.toString());
            throw e;
        }
    }

    public static boolean isProdTest() {
        try {
            boolean flag;
            flag = (testEnvType.isProdEnv()) ? true : false;
            return flag;
        } catch (Exception e) {
            // logger.error("Exception reached: Could not get Prod environment status");
            // logger.error(e.toString());
            throw e;
        }
    }

    public static boolean isBrowserLocationDisabledForTestCase(String testcaseName) {
        try {
            if(disableBrowserLocationTestCases.indexOf(testcaseName) != -1)
                return true;
        } catch (Exception e) {
            // logger.error("Exception reached: Could not get Browser Location On/Off status");
            // logger.error(e.toString());
            throw e;
        }
        return false;
    }
}
