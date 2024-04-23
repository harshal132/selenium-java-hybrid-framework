package pages.moduleone;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class ModuleOnePageOne extends BasePage {
    WebDriver driver;
    public ModuleOnePageOne(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
