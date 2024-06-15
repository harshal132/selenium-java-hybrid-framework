package tests.customerportal;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.BaseTest;

public class TestOne extends BaseTest {
    @Test(groups = {"regression"})
    public void testOne() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://www.google.com");
        Thread.sleep(8000);
    }
}
