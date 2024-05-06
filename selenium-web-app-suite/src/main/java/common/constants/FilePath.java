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
    public static final String REAL_LOCATORS_MODULE_ONE = REAL_LOCATORS + "moduleone" + File.separator;
    public static final String REAL_LOCATORS_MODULE_ONE_PAGE_ONE = REAL_LOCATORS_MODULE_ONE + "moduleonepageone.yml";

    // Data
    public static final String REAL_APP_DATA_FILE_PATH = REAL_DATA_FILE_PATH + "application" + File.separator + "app-data.yml";
    public static final String TEST_DATA_MODULE_ONE = REAL_DATA_FILE_PATH + "moduleone" + File.separator + "moduleone.yml";
    public static final String TEST_DATA_MODULE_TWO = REAL_DATA_FILE_PATH + "moduletwo" + File.separator + "moduletwo.yml";

    public FilePath() {

    }
}
