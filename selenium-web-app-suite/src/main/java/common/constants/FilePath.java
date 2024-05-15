package common.constants;

import java.io.File;

public class FilePath {

    //////////////////////////// REAL PATH FOR MY TEST FRAMEWORK ////////////////////////////


    // Resources
    private static final String REAL_RESOURCES_PATH = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator;
    // Directory
    public static final String REAL_DATA_FILE_PATH = REAL_RESOURCES_PATH + "data" + File.separator;
    public static final String REAL_REPORTS_FILE_PATH = REAL_RESOURCES_PATH + "reports" + File.separator;
    public static final String REAL_TEST_SCREENSHOT_DIR = REAL_REPORTS_FILE_PATH + "screenshots" + File.separator;
    public static final String IMAGES_DATA_FILE_PATH = REAL_DATA_FILE_PATH + "images";

    // Locators
    public static final String REAL_LOCATORS = REAL_RESOURCES_PATH + "locators" + File.separator;
    public static final String LOCATORS_ACCOUNTS = REAL_LOCATORS + "accounts" + File.separator;
    public static final String LOCATOR_ACCOUNTS_HOMEPAGE = LOCATORS_ACCOUNTS + "homescreen.yml";
    public static final String LOCATOR_ACCOUNTS_LOGIN_POPUP = LOCATORS_ACCOUNTS + "loginscreen.yml";

    // Data
    public static final String REAL_APP_DATA_FILE_PATH = REAL_DATA_FILE_PATH + "application" + File.separator + "app-data.yml";
    public static final String REAL_COMMON_DATA_FILE_PATH = REAL_DATA_FILE_PATH + "application" + File.separator + "common.yml";
    public static final String ACCOUNT_TEST_DATA = REAL_DATA_FILE_PATH + "accounts" + File.separator + "accounts.yml";
    public static final String CATEGORIES_TEST_DATA = REAL_DATA_FILE_PATH + "categories" + File.separator + "categories.yml";

    public FilePath() {

    }
}
