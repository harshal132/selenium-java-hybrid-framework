package pages;

import static org.openqa.selenium.support.locators.RelativeLocator.with;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import common.constants.FilePath;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;
import utils.DataLoader;

public class BasePage {
    protected WebDriver driver;
    protected DataLoader yaml;

    public BasePage(WebDriver driver) {
        System.out.println("Driver object: "+driver);
        this.driver = driver;
        this.yaml = new DataLoader();
    }

    public void loadUrl(boolean deleteCookiesFlag, String url) {
        try {
            if (deleteCookiesFlag) {
                driver.manage().deleteAllCookies();
                System.out.println("Loaded url by deleting cookies: " + url);
            }
            driver.get(url);
            waitForPageToLoad();
        } catch (Exception e) {
            System.out.println("Exception reached: Could not load Url");
            throw e;
        }
    }

    public void loadBaseUrl(){
        try {
            if(BaseTest.isQaTest()){
                driver.get(DataLoader.getAppData(FilePath.REAL_APP_DATA_FILE_PATH,"baseUrl.qa"));
                waitForPageToLoad();
            }else {
                driver.get(DataLoader.getAppData(FilePath.REAL_APP_DATA_FILE_PATH,"baseUrl.prod"));
                waitForPageToLoad();
            }

        } catch (Exception e) {
            System.out.println("Exception reached: Could not load Base Url");
            throw e;
        }
    }

    public void refreshPage() {
        try {
            driver.navigate().refresh();
            waitForPageToLoad();
            System.out.println("Refreshed page ");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not refresh current page");
            throw e;
        }
    }

    public void navigateBack() {
        try {
            driver.navigate().back();
            waitForPageToLoad();
            System.out.println("Naviagted to back page ");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not navigate to back page");
            throw e;
        }
    }

    public void navigateForward() {
        try {
            driver.navigate().forward();;
            waitForPageToLoad();
            System.out.println("Naviagted to forward page ");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not navigate to forward page");
            throw e;
        }
    }

    public String getCurrentPageUrl() {
        String url;
        try {
            waitForPageToLoad();
            url = driver.getCurrentUrl().trim();
            System.out.println("Got current page url: " + url);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get current page url");
            throw e;
        }
        return url;
    }

    protected WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Element");
            throw e;
        }
        return element;
    }

    protected List<WebElement> getElements(By locator) {
        List<WebElement> elements = null;
        try {
            elements = driver.findElements(locator);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get elements"+ e);
            throw e;
        }
        return elements;
    }

    protected void clearText(By locator) {
        clearText(getElement(locator));
    }

    protected void clearText(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            System.out.println("Exception reached: Could not clear text"+ e);
            throw e;
        }
    }

    protected void enterText(String text, By locator) {
        enterText(text,getElement(locator));
    }

    protected void enterKeybordKeys(By locator, CharSequence... keysToSend) {
        enterKeybordKeys(getElement(locator), keysToSend);
    }

    protected void enterKeybordKeys(WebElement element, CharSequence... keysToSend) {
        try {
            element.sendKeys(keysToSend);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not enter keys"+ e);
            throw e;
        }
    }

    protected void enterTextWithoutClear(String text, By locator) {
        enterTextWithoutClear(text, getElement(locator));
    }

    protected void enterTextWithoutClear(String text, WebElement element) {
        try {
            element.sendKeys(text);
            System.out.println("Text entered on element: " + text);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not enter text"+ e);
            throw e;
        }
    }

    protected void enterText(String text, WebElement element) {
        try {
            clearText(element);
            element.sendKeys(text);
            System.out.println("Text entered on element: " + text);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not enter text"+ e);
            throw e;
        }
    }

    protected void enterTextCharByChar(String text, By locator) {
        try {
            WebElement element = getElement(locator);
            clearText(element);
            text.chars().forEach(ch -> {
                element.sendKeys(Character.toString((char) ch));
                hardWait(500);
            });
            // // logger.debug("Text entered on element char by char: " + text);
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not enter text char by char", e);
            throw e;
        }
    }

    protected String getText(By locator) {
        return getText(getElement(locator));
    }

    protected String getText(WebElement element) {
        String text = "";
        try {
            text = element.getText();
            // // logger.debug("Element text returned: " + text);
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get text by element", e);
        }
        return text;
    }

    protected void click(By locator) {
        click(getElement(locator));
    }

    protected void click(WebElement element) {
        try {
            element.click();
            // // logger.debug("Clicked on element");
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not click by element", e);
            throw e;
        }
    }

    protected String getTagName(By locator) {
        return getTagName(getElement(locator));
    }

    protected String getTagName(WebElement element) {
        String tagName="";
        try {
            element.getTagName();
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get tagName", e);
            throw e;
        }

        return tagName;
    }

    protected boolean isAttributePresent(String attributeName, By locator) {
        return isAttributePresent(attributeName, getElement(locator));
    }

    protected boolean isAttributePresent(String attributeName, WebElement element) {
        boolean result = false;
        try {
            String value = getAttributeValue(attributeName, element);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not verify attribute's presence ", e);
            throw e;
        }
        return result;
    }

    protected String getAttributeValue(String attributeName, By locator) {
        return getAttributeValue(attributeName, getElement(locator));
    }

    protected String getAttributeValue(String attributeName, WebElement element) {
        String text = "";
        try {
            text = element.getAttribute(attributeName);
            // // logger.debug("Got attribute: " + attributeName + " value: " + text);
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get attribute", e);
            throw e;
        }
        return text;
    }

    protected int getElementsCount(By locator) {
        int count = -1;
        try {
            count = getElements(locator).size();
            // // logger.debug("Got number of elments count: " + count);
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get elements count", e);
            throw e;
        }
        return count;
    }

    protected boolean isDisplayed(boolean shouldBeDisplayed, By locator) {
        try {
            if (getElementsCount(locator) > 0) {
                boolean isDisplayed = getElement(locator).isDisplayed();
                // // logger.debug("Got isDisplayed value as: " + isDisplayed);
                return !(shouldBeDisplayed ^ isDisplayed);
            } else {
                // // logger.debug("Got element count value as equal to 0");
                if (!shouldBeDisplayed)
                    return true;
                else
                    return false;
            }
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get element is displayed status", e);
            throw e;

        }
    }

    protected boolean isDisplayedIfElementOnDom(boolean shouldBeDisplayed, WebElement element) {
        try {
            boolean isDisplayed = element.isDisplayed();
            // // logger.debug("Got isDisplayed value as: " + isDisplayed);
            return !(shouldBeDisplayed ^ isDisplayed);
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get element is displayed status", e);
            throw e;

        }
    }

    protected boolean isSelected(boolean shouldBeSelected, By locator) {
        try {
            if (getElementsCount(locator) > 0) {
                boolean isSelected = getElement(locator).isSelected();
                // // logger.debug("Got isSelected value as: " + isSelected);
                return !(shouldBeSelected ^ isSelected);
            } else {
                // // logger.debug("Got element count value as equal to 0");
                if (!shouldBeSelected)
                    return true;
                else
                    return false;
            }
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get element is selected status", e);
            throw e;

        }
    }

    public void hardWait(long timeInMilliSeconds) {
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not add hardwait", e);
        }
    }

    protected WebElement waitForElementToBeClickable(By locator, int timeInMilliSeconds) {
        WebElement element = null;
        try {
            element = new WebDriverWait(driver, Duration.ofMillis(timeInMilliSeconds))
                    .until(ExpectedConditions.elementToBeClickable(locator));
            // // logger.debug("Waited for element to be clickable");
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not wait for element to be clickable", e);
            throw e;
        }
        return element;
    }

    protected WebElement waitForElementToBeClickable(WebElement element, int timeInMilliSeconds) {
        try {
            element = new WebDriverWait(driver, Duration.ofMillis(timeInMilliSeconds))
                    .until(ExpectedConditions.elementToBeClickable(element));
            // // logger.debug("Waited for element to be clickable");
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not wait for element to be clickable", e);
            throw e;
        }
        return element;
    }

    protected WebElement waitForElementToBeVisible(By locator, int timeInMilliSeconds) {
        WebElement element = null;
        try {
            element = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(timeInMilliSeconds))
                    .pollingEvery(Duration.ofMillis(500)).ignoring(TimeoutException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not wait for element to be visible", e);
            throw e;
        }
        return element;
    }

    protected List<WebElement> waitForElementsToBeVisible(By locator, int timeInMilliSeconds) {
        List<WebElement> elements = null;
        try {
            elements = new WebDriverWait(driver, Duration.ofMillis(timeInMilliSeconds))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            // // logger.debug("Waited for element to be clickable");
        } catch (TimeoutException e) {
            if(getElements(locator).size() == 0)
                System.out.println("Timeout Exception reached: Could not wait for element to be visible");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not wait for element to be visible");
            throw e;
        }
        return elements;
    }

    protected boolean waitForElementToBeInVisible(By locator, int timeInMilliSeconds) {
        boolean elementInvisible = false;
        try {
            elementInvisible = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(timeInMilliSeconds))
                    .pollingEvery(Duration.ofMillis(200)).ignoring(TimeoutException.class)
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not wait for element to be invisible", e);
            throw e;
        }
        return elementInvisible;
    }

    protected WebElement waitForElementToBecomeUnstale(By locator) {
        WebElement element = null;
        try {
            element = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(10000))
                    .pollingEvery(Duration.ofMillis(500)).ignoring(StaleElementReferenceException.class)
                    .ignoring(TimeoutException.class)
                    .until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not wait for element to be unstale", e);
            throw e;
        }
        return element;
    }

    public void waitForPageToLoad() {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMillis(10000));
            wait.until(driver -> {
                return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            });
            // // logger.debug("Waited for page to load");
        } catch (Exception e) {
            // logger.warn("Exception reached: Could not wait for page to be loaded", e);
        }
    }

    protected void hoverOnElement(WebElement element) {
        try {
            new Actions(driver).moveToElement(element).build().perform();
            // // logger.debug("hovered on element");
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not hover on element", e);
            throw e;
        }
    }

    protected void hoverOnElement(By locator) {
        hoverOnElement(getElement(locator));
    }

    protected void actionsScrollToElement(By locator) {
        try {
            WebElement element = getElement(locator);
            new Actions(driver).moveToElement(element).perform();
            // // logger.debug("Scrolled to element");
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not scroll to element by actions class", e);
            throw e;
        }
    }

    protected void actionsDragAndDropElements(By sourceLocator, By destinationLocator) {
        actionsDragAndDropElements(getElement(sourceLocator), getElement(destinationLocator));
    }

    protected void actionsDragAndDropElements(WebElement sourceElement, WebElement destinationElement) {
        try {
            new Actions(driver)
                    .dragAndDrop(sourceElement, destinationElement)
                    .perform();

            // // logger.debug("Dragged source element to destination element");
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not drag source element to destination element by actions class", e);
            throw e;
        }
    }

    protected Object jsExecuteScript(String script, WebElement element) {
        Object scriptExceute = null;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            scriptExceute = js.executeScript(script, element);
            // // logger.debug("Executed javascript");
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not execute script: " + script, e);
            throw e;
        }
        return scriptExceute;
    }

    protected Object jsExecuteScript(String script) {
        Object scriptExceute = null;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            scriptExceute = js.executeScript(script);
            // // logger.debug("Executed javascript");
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not execute script: " + script, e);
            throw e;
        }
        return scriptExceute;
    }

    protected String jsGetText(By locator) {
        return jsGetText(getElement(locator));
    }


    protected String jsGetText(WebElement element) {
        String value = "";
        try {
            value = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;",
                    element);
            // // logger.debug("Got text from elment by javascript: " + value);
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get text through javascript: ", e);
            throw e;
        }
        return value;
    }

    protected String jsGetValue(By locator) {
        return jsGetValue(getElement(locator));
    }

    protected String jsGetValue(WebElement element) {
        String value = "";
        try {
            value = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",
                    element);
            // // logger.debug("Got value from elment by javascript: " + value);
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get value through javascript: ", e);
            throw e;
        }
        return value;
    }

    protected void jsSetValue(By locator, String value) {
        jsSetValue(getElement(locator), value);
    }


    protected void jsSetValue(WebElement element, String value) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + value + "'",
                    element);
            // // logger.debug("Set value for elment by javascript: " + value);
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not set value through javascript: ", e);
            throw e;
        }
    }

    protected String jsGetInnerText(By locator) {
        return jsGetInnerText(getElement(locator));
    }

    protected String jsGetInnerText(WebElement element) {
        String value = "";
        try {
            value = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;",
                    element);
            // // logger.debug("Got text from elment by javascript: " + value);
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get text through javascript: ", e);
            throw e;
        }
        return value;
    }

    protected WebElement jsGetElementByXpath(String xPathExp) {
        WebElement element = null;
        try {
            String script = "document.evaluate(" + xPathExp
                    + ",document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;)";
            element = (WebElement) jsExecuteScript(script);
            // // logger.debug("Got element from xpath by javascript");
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not get xpath element by javascript: " + xPathExp, e);
            throw e;
        }
        return element;
    }

    protected void jsClick(WebElement element) {
        try {
            jsExecuteScript("arguments[0].click();", element);
            // // logger.debug("Clicked on element");
        } catch (Exception e) {
            // // logger.error("Exception reached: Could not click by javascript", e);
            throw e;
        }
    }

    /**
     * click on elemnt by javascript
     *
     * @param locator
     */
    protected void jsClick(By locator) {
        jsClick(getElement(locator));
    }

    /**
     * scroll by pixel on page
     *
     * @param x
     * @param y
     */
    protected void jsScrollByPixel(int x, int y) {
        try {
            jsExecuteScript("window.scrollBy(" + x + "," + y + ")");
            // // logger.debug("Scrolled by pixel: (" + x + "," + y + ")");
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not scroll by pixel by javascript: ", e);
        }
    }

    protected void jsScrollToElement(WebElement element) {
        try {
            jsExecuteScript("arguments[0].scrollIntoView();", element);
            // // logger.debug("Scrolled to elemnt by javascript");
        } catch (Exception e) {
            // // logger.warn("Exception reached: Could not scroll to element by javascript: ", e);
        }
    }

    /**
     * scroll to element by locator
     *
     * @param locator
     */
    protected void jsScrollToElement(By locator) {
        jsScrollToElement(getElement(locator));
    }

    /**
     * scroll to top of page
     */
    public void jsScrollToTop() {
        try {
            jsExecuteScript("window.scrollTo(0, 0)");
            // logger.debug("Scrolled to top by javascript");
        } catch (Exception e) {
            // logger.warn("Exception reached: Could not scroll to top", e);
        }
    }

    /**
     * scroll to bottom of page
     */
    public void jsScrollToBottom() {
        try {
            jsExecuteScript("window.scrollTo(0, document.body.scrollHeight)");
            // logger.debug("Scrolled to bottom by javascript");
        } catch (Exception e) {
            // logger.warn("Exception reached: Could not scroll to bottom", e);
        }
    }

    protected void jsRemoveElement(By locator) {
        try {
            WebElement element = getElement(locator);
            jsExecuteScript("arguments[0].remove();", element);
            // logger.debug("Removed elemnt by javascript");
        } catch (Exception e) {
            // logger.error("Exception reached: Could not remove iframe/element by javascript: ", e);
            throw e;
        }
    }

    protected void jsHighlightElement(By locator) {
        try {
            WebElement element = getElement(locator);
            jsExecuteScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
            // logger.debug("Highlighted elemnt by javascript");
        } catch (Exception e) {
            // logger.error("Exception reached: Could not highlight element by javascript: ", e);
            throw e;
        }
    }

    protected void acceptAlertIfPresent() {
        try {
            new WebDriverWait(driver, Duration.ofMillis(8000)).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (TimeoutException e) {
            // logger.warn("Timed out, alert not present", e);
        } catch (NoAlertPresentException e) {
            // logger.warn("No Alert present", e);
        } catch (Exception e) {
            // logger.error("Exception reached: Could not confirm on alert", e);
            throw e;
        }
    }

    public void dismissAlertIfPresent() {
        try {
            new WebDriverWait(driver, Duration.ofMillis(8000)).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().dismiss();;
        } catch (TimeoutException e) {
            // logger.warn("Timed out, alert not present", e);
        } catch (NoAlertPresentException e) {
            // logger.warn("No Alert present", e);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not confirm on alert");
            throw e;
        }
    }

    protected void jsSetBrowserGeoLocation(double longitude, double latitude) {
        try {
            jsExecuteScript(
                    "window.navigator.geolocation.getCurrentPosition=function(success){; var position = {'coords' : {'latitude': '"
                            + latitude + "','longitude': '" + longitude + "'}}; success(position);}");
        } catch (Exception e) {
            // logger.error("Exception reached: Could not set browser location", e);
            throw e;
        }
    }

    protected String jsGetBrowserGeoLocationLatitude() {
        String latitude = null;
        try {
            latitude = (String) jsExecuteScript(
                    "var x='';navigator.geolocation.getCurrentPosition(function(position) {x= position.coords.latitude});return x;");
        } catch (Exception e) {
            // logger.error("Exception reached: Could not get browser location latitude", e);
            throw e;
        }
        return latitude;
    }

    protected String jsGetBrowserGeoLocationLongitude() {
        String latitude = null;
        try {
            latitude = (String) jsExecuteScript(
                    "var y='';navigator.geolocation.getCurrentPosition(function(position) {y= position.coords.longitude});return y;");
        } catch (Exception e) {
            // logger.error("Exception reached: Could not get browser location longitude", e);
            throw e;
        }
        return latitude;
    }

    public String getWindowHandle() {
        try {
            return driver.getWindowHandle();
        } catch(Exception e) {
            // logger.error("Exception reached: Could not get window handle", e);
            throw e;
        }
    }

    public Set<String> getWindowHandles() {
        try {
            return driver.getWindowHandles();
        } catch(Exception e) {
            // logger.error("Exception reached: Could not get window handles", e);
            throw e;
        }
    }

    public void switchToChildWindow() {
        try {
            if(getWindowHandles().size()>1) {
                String originalWindow = getWindowHandle();
                for (String windowHandle : getWindowHandles()) {
                    if (!originalWindow.contentEquals(windowHandle)) {
                        driver.switchTo().window(windowHandle);
                        waitForPageToLoad();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            // logger.error("Exception reached: Could not switch to child window", e);
            throw e;
        }
    }

    protected void switchToParentWindow() {
        try {
            String childWindow = getWindowHandle();
            String parentWindow = null;
            parentWindow = getWindowHandles().stream().filter(window -> !window.contentEquals(childWindow)).findFirst()
                    .orElse("");
            driver.switchTo().window(parentWindow);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not switch to parent window"+ e);
            throw e;
        }
    }

    public void switchToParentWindowClosingChild() {
        try {
            String childWindow = getWindowHandle();
            String parentWindow = null;
            parentWindow = getWindowHandles().stream().filter(window -> !window.contentEquals(childWindow)).findFirst()
                    .orElse("");
            driver.close();
            driver.switchTo().window(parentWindow);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not switch to parent window after closing child"+ e);
            throw e;
        }
    }

    protected void switchToIframe(By locator) {
        try {
            driver.switchTo().frame(getElement(locator));
        } catch (Exception e) {
            System.out.println("Exception reached: Could not switch to iframe"+ e);
            throw e;
        }
    }

    protected void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Exception reached: Could not switch to default content"+ e);
            throw e;
        }
    }

    public String getPageUrlFromNextTab() {
        String url;
        try {
            hardWait(3000);
            switchToChildWindow();
            waitForPageToLoad();
            url = getCurrentPageUrl();
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get url from next tab"+ e);
            throw e;
        }
        return url;
    }

    public int getHttpResponseCodeWithoutParams(String urlString, String method) {
        URL url;
        HttpURLConnection request;
        int responseCode = 0;
        try {
            url = new URL(urlString);
            request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod(method);
            request.connect();
            responseCode = request.getResponseCode();
        } catch (IOException e) {
            System.out.println("Exception reached: Could not get url response"+ e);
        }
        return responseCode;
    }

    protected void hitEnterKey(By locator) {
        try {
            getElement(locator).sendKeys(Keys.RETURN);
        } catch (Exception e) {
            System.out.println("Exception reached: Could not hit enter key"+ e);
            throw e;
        }
    }

    public List<String> getStringIntoList(String stringToChange, String delimeter) {
        List<String> list;
        try {
            list = Stream.of(stringToChange.split(delimeter, -1)).collect(Collectors.toList());
        } catch(Exception e) {
            System.out.println("Exception reached: Could not split string to list " + stringToChange + " with delimeter " + delimeter+ e);
            throw e;
        }
        return list;
    }

    public Map<String, String> getListIntoMap(List<String> listToChange, String delimeter) {
        Map<String, String> map = new LinkedHashMap<>();
        try {
            for (String string : listToChange)
                map.put(string.split(delimeter)[0], string.split(delimeter)[1]);
        } catch(Exception e) {
            System.out.println("Exception reached: Could not split list to map " + listToChange + " with delimeter " + delimeter+ e);
            throw e;
        }
        return map;
    }

    protected boolean isElementWithSize(By locator) {
        WebElement element = getElement(locator);
        return isElementWithSize(element);
    }

    protected boolean isElementWithSize(WebElement element) {
        try {
            if(element.getSize().height < 1 || element.getSize().width < 1)
                return false;
            return true;
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get element size"+ e);
            throw e;
        }
    }

    protected String getElementCssPropertyValue(By locator, String cssPropertyame) {
        try {
            WebElement element = getElement(locator);
            return element.getCssValue(cssPropertyame);
        } catch(Exception e) {
            System.out.println("Exception reached: Could not get element css property"+ e);
            throw e;
        }
    }

    protected void selectOptionByText(By selectLocator, String optionText) {
        try {
            Select selectElement = new Select(getElement(selectLocator));
            selectElement.selectByVisibleText(optionText);
        } catch(Exception e) {
            System.out.println("Exception reached: Could not select option by index"+ e);
            throw e;
        }
    }

    protected void selectOptionByIndex(By selectLocator, int index) {
        try {
            Select selectElement = new Select(getElement(selectLocator));
            selectElement.selectByIndex(index);
        } catch(Exception e) {
            System.out.println("Exception reached: Could not select option by index"+ e);
            throw e;
        }
    }

    // Math operations

    public double truncateUptoParticularDecimalPoint(double value, int decimalpoint)
    {
        double updatedValue=0.0;
        try {
            updatedValue = value * Math.pow(10, decimalpoint);
            updatedValue = Math.floor(updatedValue);
            updatedValue = updatedValue / Math.pow(10, decimalpoint);

        } catch (Exception e) {
            System.out.println("Exception reached: Could not truncate value upto decimal point as expected due to - "+ e);
        }
        return updatedValue;
    }

    // Regular expression operations
    public String getTextMatchingWithPatternUsingRegex(String patternToFindMatch, String textToBeMatched) {
        try {
            Pattern pattern = Pattern.compile(patternToFindMatch);
            Matcher matcher = pattern.matcher(textToBeMatched);
            boolean matchFound = matcher.find();
            if(matchFound)
                return matcher.group(1);

        } catch (Exception e) {
            System.out.println("Failed to get text matching the given pattern due to exception " + e.getMessage());
        }
        return null;
    }

    public boolean validateTextIsMatchingWithPatternUsingRegex(String patternToFindMatch, String textToBeMatched) {
        try {
            Pattern pattern = Pattern.compile(patternToFindMatch);
            Matcher matcher = pattern.matcher(textToBeMatched);
            boolean matchFound = matcher.find();
            if(matchFound)
                return true;

        } catch (Exception e) {
            System.out.println("Failed to validate whether the text is matching the given pattern or not, due to exception " + e.getMessage());
        }
        return false;
    }

    // ROBOT framework operations

    public boolean uploadMediaByRobot(String fileName) {
        final String imagesData = FilePath.IMAGES_DATA_FILE_PATH;

        try {
            //File Need to be imported

            File file = new File(imagesData+fileName);
            StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
            //Copy to clipboard
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            Robot robot = new Robot();

            robot.delay(3000);
            // Cmd + Tab is needed since it launches a Java app and the browser looses focus
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(500);

            //Open Goto window
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);
            robot.delay(5000);
            //Paste the clipboard value
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_V);

            //Press Enter key to close the Goto window and Upload window
            robot.delay(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(3000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            System.out.println("Uploaded media with path : " + imagesData+fileName);
            return true;

        } catch (Exception e) {
            System.out.println("Failed to upload media with path : " + imagesData+fileName + " due to exception - " + e.getMessage());
            return false;
        }
    }

    // Relative Locators functions
    protected WebElement getElementAboveOf(By baseLocator, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).above(baseLocator));
            System.out.println("WebElement found above");
        } catch (Exception e) {
            // logger.error("Exception reached: Could not get Element above using relative locator ", e);
        }
        return element;
    }

    protected WebElement getElementBelowOf(By baseLocator, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).below(baseLocator));
            System.out.println("WebElement found below");
        } catch (Exception e) {
            // logger.error("Exception reached: Could not get Element below using relative locator ", e);
        }
        return element;
    }

    protected WebElement getElementToLeftOf(By baseLocator, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).toLeftOf(baseLocator));
            System.out.println("WebElement found to the left");
        } catch (Exception e) {
            // logger.error("Exception reached: Could not get Element to the left using relative locator ", e);
        }
        return element;
    }

    protected WebElement getElementToRightOf(By baseLocator, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).toRightOf(baseLocator));
            System.out.println("WebElement found to the right");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Element to the right using relative locator "+ e);
        }
        return element;
    }

    protected WebElement getElementNearTo(By baseLocator, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).below(baseLocator));
            System.out.println("WebElement found near to it");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Element near to it using relative locator "+ e);
        }
        return element;
    }

    protected WebElement getElementAboveOf(WebElement baseElement, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).above(baseElement));
            System.out.println("WebElement found above");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Element above using relative locator "+ e);
        }
        return element;
    }

    protected WebElement getElementBelowOf(WebElement baseElement, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).below(baseElement));
            System.out.println("WebElement found below");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Element below using relative locator "+ e);
        }
        return element;
    }


    protected WebElement getElementToLeftOf(WebElement baseElement, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).toLeftOf(baseElement));
            System.out.println("WebElement found to the left");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Element to the left using relative locator "+e.getMessage());
        }
        return element;
    }

    protected WebElement getElementToRightOf(WebElement baseElement, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).toLeftOf(baseElement));
            System.out.println("WebElement found to the right");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Element to the right using relative locator "+ e);
        }
        return element;
    }

    protected WebElement getElementNearTo(WebElement baseElement, By relativeLocator) {
        WebElement element = null;
        try {
            element = driver.findElement(with(relativeLocator).below(baseElement));
            System.out.println("WebElement found near to it");
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get Element near to it using relative locator "+ e);
        }
        return element;
    }

    // Location related
    public void setBrowserGeoLocation(Double latitude, Double longitude) {
        try {
            ((LocationContext)driver).setLocation(new Location(latitude, longitude, 0));
        } catch(Exception e) {
            System.out.println("Exception reached: Could not set browser location to specific lat and long"+ e);
            throw e;
        }
    }

    public String getTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            System.out.println("Exception reached: Could not get title by element");
            return null;
        }
    }
}
