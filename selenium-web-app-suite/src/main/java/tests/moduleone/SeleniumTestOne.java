package tests.moduleone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.annotations.Test;

public class SeleniumTestOne {
    @Test
    public void testOne(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        System.out.println(SeleniumManager.getInstance().getDriverPath(options,true));
        WebDriver driver = new ChromeDriver(options);


        driver.get("https://www.google.com");
        System.out.print(driver.getTitle());
        driver.quit();

    }
}
