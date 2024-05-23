package common.constants;

import java.io.File;

public class FilePath {

    //////////////////////////// REAL PATH FOR MY TEST FRAMEWORK ////////////////////////////


    // Resources
    private static final String REAL_RESOURCES_PATH = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator;
    // Directory
    public static final String DATA_DIR = REAL_RESOURCES_PATH + "data" + File.separator;
    public static final String REPORTS_DIR = REAL_RESOURCES_PATH + "reports" + File.separator;
    public static final String TEST_SCREENSHOT_DIR = REPORTS_DIR + "screenshots" + File.separator;
    public static final String IMAGES_DATA_DIR = DATA_DIR + "images"+ File.separator;
    public static final String LOCATORS_DIR = REAL_RESOURCES_PATH + "locators" + File.separator;

    // Locator Sub directories
    public static final String CUSTOMER_PORTAL_LOCATORS = LOCATORS_DIR + "customerportal" + File.separator;
    public static final String ADMIN_PORTAL_LOCATORS = LOCATORS_DIR + "adminportal" + File.separator;
    public static final String PUBLIC_PORTAL_LOCATORS = LOCATORS_DIR + "publicportal" + File.separator;

    // Data Sub directories
    public static final String CUSTOMER_PORTAL_TEST_DATA_DIR = DATA_DIR + "customerportal" + File.separator;
    public static final String ADMIN_PORTAL_TEST_DATA_DIR = DATA_DIR + "adminportal" + File.separator;
    public static final String PUBLIC_PORTAL_TEST_DATA_DIR = DATA_DIR + "publicportal" + File.separator;

    // Add test data and locators files below as per defined modules

    // Test data
    public static final String APP_DATA_FILE_PATH = DATA_DIR + "application" + File.separator + "app-data.yml";
    public static final String COMMON_TEST_DATA_FILE_PATH = DATA_DIR + "application" + File.separator + "common.yml";

    public static final String customerPortalTestData = CUSTOMER_PORTAL_TEST_DATA_DIR + "testdata.yml";
    public static final String adminPortalTestData = ADMIN_PORTAL_TEST_DATA_DIR + "testdata.yml";
    public static final String publicPortalTestData = PUBLIC_PORTAL_TEST_DATA_DIR + "testdata.yml";

    // Locators
    public static final String CUSTOMER_LOGINPAGE_LOCATORS = CUSTOMER_PORTAL_LOCATORS + "customerportalloginpage.yml";
    public static final String CUSTOMER_HOMEPAGE_LOCATORS = CUSTOMER_PORTAL_LOCATORS + "customerportalhomepage.yml";
    public static final String CUSTOMER_ADD_EMPLOYEE_LOCATORS = CUSTOMER_PORTAL_LOCATORS + "addemployeepage.yml";
    public FilePath() {

    }
}
