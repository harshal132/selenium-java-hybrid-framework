package common.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.asserts.IAssert;
import utils.ExtentTestManager;

public class AssertListener extends Assert {
    public synchronized void onAssertSuccess(IAssert<?> assertCommand) {
        try{
            ExtentTestManager.getTest().log(Status.PASS, "Validation as expected: " + assertCommand.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(TestListener.takeScreenshot()).build());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public synchronized void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        try {
            ExtentTestManager.getTest().log(Status.FAIL, "Validation not as expected: " + ex.getLocalizedMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(TestListener.takeScreenshot()).build());
        } catch (Exception e) {
            System.out.println("Could not add report statement");
        }
    }
}
