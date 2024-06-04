package tests.publicportal;

import common.constants.WaitTime;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.BasePage;
import tests.BaseTest;

import java.beans.beancontext.BeanContextServiceAvailableEvent;

public class KeyCloakAutomationCheck extends BaseTest {
    @Test
    public void simpleLoginTest() throws InterruptedException {
        new BasePage(getDriver()).loadUrl(true, "https://poc-dshpp.hidubai.com/home/");

        getDriver().findElement(By.id("username")).sendKeys("harshal.hidubai@gmail.com");
        getDriver().findElement(By.id("password")).sendKeys("Harshal@123");
        getDriver().findElement(By.id("kc-login")).click();
    }

    @Test
    public void simpleRegisterTest() throws InterruptedException {
        new BasePage(getDriver()).loadUrl(true, "https://poc-dshpp.hidubai.com/home/");

        getDriver().findElement(By.linkText("Register")).click();

        getDriver().findElement(By.id("email")).sendKeys("tt.hidubai@gmail.com");
        getDriver().findElement(By.id("password")).sendKeys("Test@123");
        getDriver().findElement(By.id("password-confirm")).sendKeys("Test@123");
        getDriver().findElement(By.id("firstName")).sendKeys("Harshal");
        getDriver().findElement(By.id("lastName")).sendKeys("Chavan");
        getDriver().findElement(By.id("mobile")).sendKeys("+971556939352");

        getDriver().findElement(By.xpath("//input[@type='submit']")).click();

    }
}
