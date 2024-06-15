package utils;
import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import common.constants.FilePath;
import static utils.DataLoader.getCommonData;

public class ExtentManager {
    private static ExtentReports extentReport;

    public static ExtentReports getInstance() {
        if (extentReport == null){
            extentReport = createInstance();
        }
        return extentReport;
    }

    public static ExtentReports createInstance() {
        String fileName = getReportPath(FilePath.REPORTS_DIR_PATH);
        String reportName = getCommonData(FilePath.APP_DATA_FILE_PATH,"testSuiteReportName");
        ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle(reportName);
        reporter.config().setEncoding("utf-8");
        reporter.config().setReportName(reportName);
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        reporter.config().setProtocol(Protocol.HTTPS);

        extentReport = new ExtentReports();
        extentReport.attachReporter(reporter);

        return extentReport;
    }

    /**
     * Generates new reports in separate directory without deleting old reports
     */
//    private static String getReportPath(String path) {
//        String reportFileLocation = null;
//        Date now = new Date();
//        String format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH).format(now);
//
//        reportFileLocation = path + File.separator + format1 + File.separator + getCommonData(FilePath.REAL_APP_DATA_FILE_PATH,"testSuiteReportName");
//        File reportsDirectory = new File(reportFileLocation);
//        if (!reportsDirectory.exists()) {
//            if (reportsDirectory.mkdir()) {
//                return reportFileLocation;
//            }
//        }
//        return reportFileLocation;
//    }

    /**
     * Overwrite data in reports directory with updated test report
     * @param path
     * @return String
     */
    private static String getReportPath(String path) {
        String reportName = DataLoader.getCommonData(FilePath.APP_DATA_FILE_PATH, "testSuiteReportName");
        String reportFileLocation = FilePath.REPORTS_DIR_PATH + File.separator + reportName;
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                return reportFileLocation;
            }
        }
        return reportFileLocation;
    }
}
