package common.constants;

import java.io.File;

public class FilePath {
    private static final String RESOURCES_PATH = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "test" + File.separator + "resources" + File.separator;

    // Locator and Data files Base path
    private static final String PP_OBJECT_REPO_FILE_PATH = RESOURCES_PATH + "locators" + File.separator + "publicPortal" + File.separator;
    private static final String PP_DATA_FILE_PATH = RESOURCES_PATH + "data" + File.separator + "publicPortal" + File.separator;
    private static final String AP_OBJECT_REPO_FILE_PATH = RESOURCES_PATH + "locators" + File.separator + "adminPortal" + File.separator;
    private static final String AP_DATA_FILE_PATH = RESOURCES_PATH + "data" + File.separator + "adminPortal" + File.separator;
    private static final String HP_OBJECT_REPO_FILE_PATH = RESOURCES_PATH + "locators" + File.separator + "hlcmsPortal" + File.separator;
    private static final String HP_DATA_FILE_PATH = RESOURCES_PATH + "data" + File.separator + "hlcmsPortal" + File.separator;
    private static final String DATA_FILE_PATH = RESOURCES_PATH + "data" + File.separator;
    public static final String IMAGES_DATA_FILE_PATH = DATA_FILE_PATH + "images" + File.separator;
    public static final String API_DATA_FILE_PATH = DATA_FILE_PATH + "api" + File.separator;

    // Log4j properties file
    public static final String LOG4J_PROPERTIES = System.getProperty("user.dir") + File.separator + "log4j.properties";

    // Failed screenshots main file directory
    public static final String FAILED_TEST_SCREENSHOT_DIR = RESOURCES_PATH + "failTestScreenshots";

    // Reports main file directory
    public static final String REPORTS_DIR = RESOURCES_PATH + File.separator + "reports";
    public static final String REPORT_NAME = "Pindubai-Test-Execution-Report" + ".html";

    //Api yml file path
    public static final String HIDUBAI_APIS = API_DATA_FILE_PATH + "pindubai-apis.yml";

    // **********Public portal**************
    //Locator files
    public static final String HOME_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "HomePage.yml";
    public static final String BUSINESS_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "BusinessPage.yml";
    public static final String BUSINESS_SETTINGS_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "BusinessSettingsPage.yml";
    public static final String HEADER_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "HeaderPage.yml";
    public static final String HIDUBAI_FIRST_AUTH_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "FirstAuthPage.yml";
    public static final String CHECK_AUTH_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "CheckAuthPage.yml";
    public static final String USER_PROFILE_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "UserProfilePage.yml";
    public static final String USER_PROFILE_SETTINGS_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH
            + "UserProfileSettingsPage.yml";
    public static final String GOOGLE_LOGIN_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "GoogleLoginPage.yml";
    public static final String SEARCH_RESULTS_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "SearchResultsPage.yml";
    public static final String FOCUS_SITE_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "FocusSitePage.yml";
    public static final String DEALS_SITE_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "DealsSitePage.yml";
    public static final String LIST_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "ListPage.yml";
    public static final String REVIEW_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "ReviewPage.yml";
    public static final String FOOTER_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "FooterPage.yml";
    public static final String CATEGORY_LIST_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "CategoryListPage.yml";
    public static final String CATEGORY_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "CategoryPage.yml";
    public static final String DET_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "DetPage.yml";
    public static final String SME_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "SmePage.yml";
    public static final String ABOUT_SITE_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "AboutSitePage.yml";
    public static final String ERROR_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "ErrorPage.yml";
    public static final String APP_OR_PLAY_STORE_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "AppOrPlayStorePage.yml";
    public static final String HOW_IT_WORKS_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "HowItWorksPage.yml";
    public static final String HELP_AND_SUPPORT_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "HelpAndSupportPage.yml";
    public static final String ABOUT_HIDUBAI_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "AboutHidubaiPage.yml";
    public static final String CLAIM_YOUR_BUSINESS_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "ClaimYourBusinessPage.yml";
    public static final String ADD_BUSINESS_PAGE_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "AddBusinessPage.yml";
    public static final String HTML_SITEMAP_LOCATORS = PP_OBJECT_REPO_FILE_PATH + "HtmlSitemapPage.yml";

    //Test data files
    public static final String APPLICATION_DATA = DATA_FILE_PATH + "Application.properties";
    public static final String HOME_PAGE_DATA = PP_DATA_FILE_PATH + "HomePage.properties";
    public static final String BUSINESS_PAGE_DATA = PP_DATA_FILE_PATH + "BusinessPage.properties";
    public static final String BUSINESS_SETTINGS_PAGE_DATA = PP_DATA_FILE_PATH + "BusinessSettingsPage.properties";
    public static final String HEADER_PAGE_DATA = PP_DATA_FILE_PATH + "HeaderPage.properties";
    public static final String CATEGORY_LIST_PAGE_DATA = PP_DATA_FILE_PATH + "CategoryListPage.properties";
    public static final String CATEGORY_PAGE_DATA = PP_DATA_FILE_PATH + "CategoryPage.properties";
    public static final String USER_PROFILE_PAGE_DATA = PP_DATA_FILE_PATH + "UserProfilePage.properties";
    public static final String USER_PROFILE_SETTINGS_PAGE_DATA = PP_DATA_FILE_PATH + "UserProfileSettingsPage.properties";
    public static final String ERROR_PAGE_DATA = PP_DATA_FILE_PATH + "ErrorPage.properties";
    public static final String SEARCH_RESULTS_PAGE_DATA = PP_DATA_FILE_PATH + "SearchResultsPage.properties";
    public static final String FOOTER_PAGE_DATA = PP_DATA_FILE_PATH + "FooterPage.properties";
    public static final String ABOUT_SITE_PAGE_DATA = PP_DATA_FILE_PATH + "AboutSitePage.properties";
    public static final String LIST_PAGE_DATA = PP_DATA_FILE_PATH + "ListPage.properties";
    public static final String REVIEW_PAGE_DATA = PP_DATA_FILE_PATH + "ReviewPage.properties";
    public static final String URLS_EXCEL_DATA_FILE = PP_DATA_FILE_PATH + "ExcelData.xlsx";
    public static final String HOW_IT_WORKS_PAGE_DATA = PP_DATA_FILE_PATH + "HowItWorksPage.properties";
    public static final String HELP_AND_SUPPORT_PAGE_DATA = PP_DATA_FILE_PATH + "HelpAndSupportPage.properties";
    public static final String ABOUT_HIDUBAI_PAGE_DATA = PP_DATA_FILE_PATH + "AboutHidubaiPage.properties";
    public static final String CLAIM_YOUR_BUSINESS_PAGE_DATA = PP_DATA_FILE_PATH + "ClaimYourBusinessPage.properties";
    public static final String FOCUS_SITE_PAGE_DATA = PP_DATA_FILE_PATH + "FocusSitePage.properties";
    public static final String HTML_SITEMAP_PAGE_DATA = PP_DATA_FILE_PATH + "HtmlSitemapPage.properties";

    // **********Admin portal**************
    //Locator files
    public static final String HOME_HEADER_PAGE_LOCATORS = AP_OBJECT_REPO_FILE_PATH + "HomeHeaderPage.yml";
    public static final String ADMIN_LOGIN_PAGE_LOCATORS = AP_OBJECT_REPO_FILE_PATH + "AdminLoginPage.yml";
    public static final String MANAGE_MACRO_PAGE_LOCATORS = AP_OBJECT_REPO_FILE_PATH + "ManageMacroPage.yml";
    public static final String MANAGE_MASTER_PAGE_LOCATORS = AP_OBJECT_REPO_FILE_PATH + "ManageMasterPage.yml";
    public static final String MANAGE_NEIGHBORHOODS_PAGE_LOCATORS = AP_OBJECT_REPO_FILE_PATH + "ManageNeighborhoodsPage.yml";
    public static final String MANAGE_BUSINESS_PAGE_LOCATORS = AP_OBJECT_REPO_FILE_PATH + "ManageBusinessPage.yml";
    public static final String MANAGE_BUSINESS_CANDIDATES_PAGE_LOCATORS = AP_OBJECT_REPO_FILE_PATH + "ManageBusinessCandidatesPage.yml";

    //Test data files
    public static final String HOME_HEADER_PAGE_DATA = AP_DATA_FILE_PATH + "HomeHeaderPage.properties";
    public static final String MANAGE_MACRO_PAGE_DATA = AP_DATA_FILE_PATH + "ManageMacroPage.properties";
    public static final String MANAGE_MASTER_PAGE_DATA = AP_DATA_FILE_PATH + "ManageMasterPage.properties";
    public static final String MANAGE_NEIGHBORHOODS_PAGE_DATA = AP_DATA_FILE_PATH + "ManageNeighborhoodsPage.properties";
    public static final String MANAGE_BUSINESS_PAGE_DATA = AP_DATA_FILE_PATH + "ManageBusinessPage.properties";
    public static final String MANAGE_BUSINESS_CANDIDATES_PAGE_DATA = AP_DATA_FILE_PATH + "ManageBusinessCandidatesPage.properties";


    // **********HLCMS portal**************
    //Locator files
    public static final String HLCMS_LOGIN_PAGE_LOCATORS = HP_OBJECT_REPO_FILE_PATH + "HlcmsLoginPage.yml";
    public static final String HLCMS_HOME_PAGE_LOCATORS = HP_OBJECT_REPO_FILE_PATH + "HlcmsHomePage.yml";
    public static final String HLCMS_CATEGORY_LOCATORS = HP_OBJECT_REPO_FILE_PATH + "HlcmsCategoryPage.yml";
    public static final String HLCMS_CATEGORY_FAQS_LOCATORS = HP_OBJECT_REPO_FILE_PATH + "HlcmsCategoryFAQsPage.yml";
    public static final String HLCMS_CATEGORY_POPULAR_KEYWORDS_LOCATORS = HP_OBJECT_REPO_FILE_PATH + "HlcmsCategoryPopularKeywords.yml";

    //Test data files
    public static final String HLCMS_HOME_PAGE_DATA = HP_DATA_FILE_PATH + "HlcmsHomePage.properties";
    public static final String HLCMS_CATEGORY_PAGE_DATA = HP_DATA_FILE_PATH + "HlcmsCategoryPage.properties";
    public static final String HLCMS_CATEGORY_FAQS_PAGE_DATA = HP_DATA_FILE_PATH + "HlcmsCategoryFAQsPage.properties";
    public static final String HLCMS_CATEGORY_POPULAR_KEYWORDS_DATA = HP_DATA_FILE_PATH + "HlcmsCategoryPopularKeywords.properties";



    public FilePath() {

    }
}
