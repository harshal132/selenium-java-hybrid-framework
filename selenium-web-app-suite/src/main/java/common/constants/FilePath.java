package common.constants;

import java.io.File;

public class FilePath {
    // Resources
    private static final String RESOURCES_PATH = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator;
    // Directory
    public static final String DATA_DIR_PATH = RESOURCES_PATH + "data" + File.separator;
    public static final String REPORTS_DIR_PATH = RESOURCES_PATH + "reports" + File.separator;
    public static final String TEST_SCREENSHOT_DIR = REPORTS_DIR_PATH + "screenshots" + File.separator;
    public static final String IMAGES_DATA_FILE_PATH = DATA_DIR_PATH + "images";

    // Locators Files & Directories Added Here
    public static final String LOCATORS_DIR = RESOURCES_PATH + "locators" + File.separator;
    public static final String LOCATORS_ADMIN_PORTAL_DIR = LOCATORS_DIR + "adminportal" + File.separator;
    public static final String LOCATORS_CUSTOMER_PORTAL_DIR = LOCATORS_DIR + "customerportal" + File.separator;
    public static final String LOCATORS_PUBLIC_PORTAL_DIR = LOCATORS_DIR + "publicportal" + File.separator;

    // Data Files & Directories Added Here
    public static final String APP_DATA_FILE_PATH = DATA_DIR_PATH + "application" + File.separator + "app-data.yml";
    public static final String TEST_DATA_MODULE_ONE = DATA_DIR_PATH + "moduleone" + File.separator + "moduleone.yml";
    public static final String TEST_DATA_MODULE_TWO = DATA_DIR_PATH + "moduletwo" + File.separator + "moduletwo.yml";

    public FilePath() {

    }
}
