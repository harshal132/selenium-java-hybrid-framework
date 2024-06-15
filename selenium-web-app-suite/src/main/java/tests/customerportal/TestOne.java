package tests.customerportal;

import config.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import tests.BaseTest;

public class TestOne extends BaseTest {
    @Ignore
    @Test(groups = {"regression"})
    public void testOne() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://www.google.com");
        Thread.sleep(8000);
    }

    @Test
    public void manageTwoActivities() throws Exception {
        WebDriver driver = getDriver();
        driver.get("https://www.google.com");

        WebDriver fbDriver = WebDriverFactory.getWebDriver(driverType,browserName);
        fbDriver.get("https://www.facebook.com");
        String fbTitle = fbDriver.getTitle();

        System.out.println(fbDriver.manage().getCookies());
        //login
        fbDriver.findElement(By.name("login")).click();
        Thread.sleep(4500);
        System.out.println(fbDriver.manage().getCookies());
        Thread.sleep(500);
    }
}
