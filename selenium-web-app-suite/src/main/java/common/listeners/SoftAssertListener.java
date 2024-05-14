package common.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import utils.ExtentTestManager;

public class SoftAssertListener extends SoftAssert {
    public synchronized void onAssertSuccess(IAssert<?> assertCommand) {
        try {
            ExtentTestManager.getTest().log(Status.PASS, "Validation as expected: " + assertCommand.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(TestListener.takeScreenshot()).build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        try {
            ExtentTestManager.getTest().log(Status.FAIL, "Soft Validation not as expected: " + ex.getLocalizedMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(TestListener.takeScreenshot()).build());
        } catch (Exception e) {
            System.out.println("Could not add report statement");
        }
    }
}
