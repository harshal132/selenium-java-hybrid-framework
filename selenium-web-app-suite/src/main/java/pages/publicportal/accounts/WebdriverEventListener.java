package pages.publicportal.accounts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class WebdriverEventListener implements WebDriverListener {
    public WebdriverEventListener() {

    }

    public void beforeAnyCall(Object target, Method method, Object[] args) {
        System.out.println("Before calling method: " + method.getName());
    }

    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        System.out.println("After calling method: " + method.getName());
    }

    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        System.out.println("Error while calling method: " + method.getName() + " - " + e.getMessage());
    }

    public void beforeAnyWebDriverCall(WebDriver driver, Method method, Object[] args) {
        System.out.println("Before calling WebDriver method: " + method.getName());
    }

    public void afterAnyWebDriverCall(WebDriver driver, Method method, Object[] args, Object result) {
        System.out.println("After calling WebDriver method: " + method.getName());
    }

    public void beforeGet(WebDriver driver, String url) {
        System.out.println("Before navigating to URL: " + url);
    }

    public void afterGet(WebDriver driver, String url) {
        System.out.println("After navigating to URL: " + url);
    }

    public void beforeGetCurrentUrl(WebDriver driver) {
        System.out.println("Before getting current URL.");
    }

    public void afterGetCurrentUrl(String result, WebDriver driver) {
        System.out.println("After getting current URL: " + result);
    }

    public void beforeGetTitle(WebDriver driver) {
        System.out.println("Before getting page title.");
    }

    public void afterGetTitle(WebDriver driver, String result) {
        System.out.println("After getting page title: " + result);
    }

    public void beforeFindElement(WebDriver driver, By locator) {
        System.out.println("Before finding element by: " + locator);
    }

    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println("After finding element by: " + locator);
    }

    public void beforeFindElements(WebDriver driver, By locator) {
        System.out.println("Before finding elements by: " + locator);
    }

    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        System.out.println("After finding elements by: " + locator);
    }

    public void beforeGetPageSource(WebDriver driver) {
        System.out.println("Before getting page source.");
    }

    public void afterGetPageSource(WebDriver driver, String result) {
        System.out.println("After getting page source.");
    }

    public void beforeClose(WebDriver driver) {
        System.out.println("Before closing the WebDriver.");
    }

    public void afterClose(WebDriver driver) {
        System.out.println("After closing the WebDriver.");
    }

    public void beforeQuit(WebDriver driver) {
        System.out.println("Before quitting the WebDriver.");
    }

    public void afterQuit(WebDriver driver) {
        System.out.println("After quitting the WebDriver.");
    }

    public void beforeGetWindowHandles(WebDriver driver) {
        System.out.println("Before getting window handles.");
    }

    public void afterGetWindowHandles(WebDriver driver, Set<String> result) {
        System.out.println("After getting window handles.");
    }

    public void beforeGetWindowHandle(WebDriver driver) {
        System.out.println("Before getting window handle.");
    }

    public void afterGetWindowHandle(WebDriver driver, String result) {
        System.out.println("After getting window handle.");
    }

    public void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
        System.out.println("Before executing script: " + script);
    }

    public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
        System.out.println("After executing script: " + script);
    }

    public void beforeExecuteAsyncScript(WebDriver driver, String script, Object[] args) {
        System.out.println("Before executing async script: " + script);
    }

    public void afterExecuteAsyncScript(WebDriver driver, String script, Object[] args, Object result) {
        System.out.println("After executing async script: " + script);
    }

    public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
        System.out.println("Before performing actions.");
    }

    public void afterPerform(WebDriver driver, Collection<Sequence> actions) {
        System.out.println("After performing actions.");
    }

    public void beforeResetInputState(WebDriver driver) {
        System.out.println("Before resetting input state.");
    }

    public void afterResetInputState(WebDriver driver) {
        System.out.println("After resetting input state.");
    }

    public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
        System.out.println("Before calling WebElement method: " + method.getName());
    }

    public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
        System.out.println("After calling WebElement method: " + method.getName());
    }

    public void beforeClick(WebElement element) {
        System.out.println("Before clicking on element.");
    }

    public void afterClick(WebElement element) {
        System.out.println("After clicking on element.");
    }

    public void beforeSubmit(WebElement element) {
        System.out.println("Before submitting a form element.");
    }

    public void afterSubmit(WebElement element) {
        System.out.println("After submitting a form element.");
    }

    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println("Before sending keys to element.");
    }

    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println("After sending keys to element.");
    }

    public void beforeClear(WebElement element) {
        System.out.println("Before clearing the text of an element.");
    }

    public void afterClear(WebElement element) {
        System.out.println("After clearing the text of an element.");
    }

    public void beforeGetTagName(WebElement element) {
        System.out.println("Before getting the tag name of an element.");
    }

    public void afterGetTagName(WebElement element, String result) {
        System.out.println("After getting the tag name of an element: " + result);
    }

    public void beforeGetAttribute(WebElement element, String name) {
        System.out.println("Before getting an attribute of an element: " + name);
    }

    public void afterGetAttribute(WebElement element, String name, String result) {
        System.out.println("After getting an attribute of an element: " + name);
    }

    public void beforeIsSelected(WebElement element) {
        System.out.println("Before checking if element is selected.");
    }

    public void afterIsSelected(WebElement element, boolean result) {
        System.out.println("After checking if element is selected: " + result);
    }

    public void beforeIsEnabled(WebElement element) {
        System.out.println("Before checking if element is enabled.");
    }

    public void afterIsEnabled(WebElement element, boolean result) {
        System.out.println("After checking if element is enabled: " + result);
    }

    public void beforeGetText(WebElement element) {
        System.out.println("Before getting text from element.");
    }

    public void afterGetText(WebElement element, String result) {
        System.out.println("After getting text from element: " + result);
    }

    public void beforeFindElement(WebElement element, By locator) {
        System.out.println("Before finding element within element: " + locator);
    }

    public void afterFindElement(WebElement element, By locator, WebElement result) {
        System.out.println("After finding element within element: " + locator);
    }

    public void beforeFindElements(WebElement element, By locator) {
        System.out.println("Before finding elements within element: " + locator);
    }

    public void afterFindElements(WebElement element, By locator, List<WebElement> result) {
        System.out.println("After finding elements within element: " + locator);
    }
}