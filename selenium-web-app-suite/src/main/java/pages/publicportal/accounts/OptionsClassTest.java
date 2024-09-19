package pages.publicportal.accounts;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OptionsClassTest {
    @Test
    public void optionsTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--incognito");

        ChromeDriver driver = new ChromeDriver(chromeOptions);
        WebdriverEventListener listener = new WebdriverEventListener();

        EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<WebDriver>(listener); //Pass listener to constructor

        WebDriver decorated = decorator.decorate(driver);

        decorated.get("https://demo.guru99.com/test/");
        WebElement element = decorated.findElement(By.name("bdaytime"));
        element.sendKeys("04092024" + Keys.TAB + "1246PM");

        System.out.println("Value: "+element.getAttribute("value"));

//        String currentWindow = driver.getWindowHandle();
//        Set<String> windows = driver.getWindowHandles();
//
//        for(String win: windows){
//            if(!currentWindow.equals(win)){
//                driver.switchTo().window(win);
//                // action to be performed
//            }
//            driver.switchTo().defaultContent();
//        }


        Thread.sleep(5000);

//        actions.click().pause(3000).release().build().perform();
//        actions.keyDown(Keys.)
//        actions.clickAndHold().pause()




    }
}
