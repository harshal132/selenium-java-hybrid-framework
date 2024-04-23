package config;

import common.constants.Browser;
import common.constants.DriverType;
import common.constants.FilePath;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import tests.BaseTest;
import utils.DataLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {
    //public static Logger logger = Logger.getLogger(WebDriverFactory.class);

    /**
     * return driver instance as per browser
     *
     * @param driverType
     * @param browser
     * @return webDriver
     * @throws Exception
     */
    public static WebDriver getWebDriver(DriverType driverType, Browser browser) throws Exception {

        WebDriver driver = null;
        if (driverType != null && browser != null) {
            if (driverType.isRemoteDriver() || driverType.isLocalDriver()) {
                if (browser.isChrome()) {
                    ChromeOptions chromeOptions = getChromeOptions();
                    driver = driverType.isRemoteDriver() ? getRemoteDriverInstance(chromeOptions)
                            : new ChromeDriver(chromeOptions);
                } else if (browser.isFirefox()) {
                    FirefoxOptions firefoxOptions = getFirefoxOptions();
                    driver = driverType.isRemoteDriver() ? getRemoteDriverInstance(firefoxOptions)
                            : new FirefoxDriver(firefoxOptions);
                } else if (browser.isEdge()) {
                    EdgeOptions edgeOptions = getEdgeOptions();
                    driver = driverType.isRemoteDriver() ? getRemoteDriverInstance(edgeOptions)
                            : new EdgeDriver(edgeOptions);
                } else if (browser.isSafari()) {
                    SafariOptions safariOptions = getSafariOptions();
                    driver = driverType.isRemoteDriver() ? getRemoteDriverInstance(safariOptions)
                            : new SafariDriver(safariOptions);
                } else if (browser.isAndroidChrome()) {
                    ChromeOptions androidEmulatorChromeOptions = getAndroidEmulatorChromeOptions();
                    driver = driverType.isRemoteDriver() ? getRemoteDriverInstance(androidEmulatorChromeOptions)
                            : new ChromeDriver(androidEmulatorChromeOptions);
                } else if (browser.isIphoneChrome()) {
                    ChromeOptions iphoneEmulatorChromeOptions = getIphoneEmulatorChromeOptions();
                    driver = driverType.isRemoteDriver() ? getRemoteDriverInstance(iphoneEmulatorChromeOptions)
                            : new ChromeDriver(iphoneEmulatorChromeOptions);
                } else
                    throw new RuntimeException(
                            "Browser name is not chrome | firefox | edge | safari | android_chrome | iphone_chrome: "
                                    + browser);
            } else
                throw new RuntimeException("Driver type is not remote or local: " + driverType);
        } else if (driverType == null)
            throw new RuntimeException("Driver type is null");
        else if (browser == null)
            throw new RuntimeException("Browser name is null");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(
                Integer.parseInt(DataLoader.getAppData(FilePath.REAL_APP_DATA_FILE_PATH,"implicitTimeOut"))
                ));

        return driver;
    }

    private static WebDriver getRemoteDriverInstance(Capabilities capabilities) throws MalformedURLException {

        WebDriver driver = null;
        try {

            driver = new RemoteWebDriver(new URL("https://127.0.0.1/hub"),capabilities);
            Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw e;
        }
        return driver;
    }

    /**
     * return chrome options
     *
     * @return chromeOptions
     */
    private static ChromeOptions getChromeOptions() {
        //WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("-headed");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--ignore-ssl-errors=yes");
        chromeOptions.addArguments("--ignore-certificate-errors");

        Map<String, Object> prefs = new HashMap<String, Object>();
        if(BaseTest.disableBrowserLocation)
            prefs.put("profile.default_content_setting_values.geolocation", 2); // 1:allow 2:block
        else
            prefs.put("profile.default_content_setting_values.geolocation", 1); // 1:allow 2:block

        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;

    }

    private static FirefoxOptions getFirefoxOptions() {
        //WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("-headed");
        firefoxOptions.addPreference("dom.webnotifications.enabled", false);
        firefoxOptions.addPreference("geo.enabled", true);
        firefoxOptions.addPreference("geo.provider.use_corelocation", true);
        firefoxOptions.addPreference("permissions.default.geo", 1);
        return firefoxOptions;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("-headed");
        return edgeOptions;
    }

    private static SafariOptions getSafariOptions() {
        return new SafariOptions();
    }

    private static ChromeOptions getAndroidEmulatorChromeOptions() {
        //WebDriverManager.chromedriver().setup();
        ChromeOptions androidEmulatorChromeOptions = new ChromeOptions();
        androidEmulatorChromeOptions.addArguments("-headed");
        androidEmulatorChromeOptions.addArguments("--remote-allow-origins=*");
        androidEmulatorChromeOptions.addArguments("--ignore-ssl-errors=yes");
        androidEmulatorChromeOptions.addArguments("--ignore-certificate-errors");
        Map<String, String> androidEmulation = new HashMap<>();
        androidEmulation.put("deviceName", "Galaxy S5");

        Map<String, Object> prefs = new HashMap<String, Object>();
        if(BaseTest.disableBrowserLocation)
            prefs.put("profile.default_content_setting_values.geolocation", 2); // 1:allow 2:block
        else
            prefs.put("profile.default_content_setting_values.geolocation", 1); // 1:allow 2:block

        androidEmulatorChromeOptions.setExperimentalOption("mobileEmulation", androidEmulation);
        androidEmulatorChromeOptions.setExperimentalOption("prefs", prefs);
        return androidEmulatorChromeOptions;
    }

    private static ChromeOptions getIphoneEmulatorChromeOptions() {
        //WebDriverManager.chromedriver().setup();
        HashMap<String, String> iphoneEmulation = new HashMap<String, String>();
        iphoneEmulation.put("deviceName", "iPhone X");
        ChromeOptions iphoneEmulatorChromeOptions = new ChromeOptions();
        iphoneEmulatorChromeOptions.setExperimentalOption("mobileEmulation", iphoneEmulation);
        iphoneEmulatorChromeOptions.addArguments("--remote-allow-origins=*");
        iphoneEmulatorChromeOptions.addArguments("--ignore-ssl-errors=yes");
        iphoneEmulatorChromeOptions.addArguments("--ignore-certificate-errors");
        return iphoneEmulatorChromeOptions;
    }
}
