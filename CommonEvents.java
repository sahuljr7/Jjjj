import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CommonEvents {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Constructor
    public CommonEvents(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    
    /**
     * Scrolls down and clicks on the substore tab
     * @return true if successful, false otherwise
     * @throws Exception if any error occurs during the operation
     */
    public boolean scrollDownAndClickSubstoreTab(By dropDownMenuLocator) throws Exception {
        boolean success = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement substoreTab = findElement(dropDownMenuLocator);
            
            // Scroll the element into view
            js.executeScript("arguments[0].scrollIntoView(true);", substoreTab);
            js.executeScript("window.scrollBy(0, -50)"); // Adjust scroll position
            
            // Highlight and click the element
            highlight(substoreTab);
            click(substoreTab);
            
            // Wait for the URL to contain "WardSupply"
            waitForUrlContains("WardSupply", 30);
            
            success = true;
        } catch (Exception e) {
            throw new Exception("Failed to scroll and click substore tab: " + e.getMessage(), e);
        }
        return success;
    }
    
    /**
     * Finds a web element with explicit wait
     * @param locator the By locator strategy
     * @return the found WebElement
     */
    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Clicks on a web element with explicit wait for clickability
     * @param element the WebElement to click
     */
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    
    /**
     * Highlights a web element by changing its border style
     * @param element the WebElement to highlight
     */
    public void highlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; border-style: dashed;');", element);
        
        try {
            Thread.sleep(200); // Brief pause to see the highlight
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
    }
    
    /**
     * Waits for the URL to contain specific text
     * @param urlText the text to wait for in the URL
     * @param timeoutInSeconds the maximum time to wait
     */
    public void waitForUrlContains(String urlText, int timeoutInSeconds) {
        WebDriverWait urlWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        urlWait.until(ExpectedConditions.urlContains(urlText));
    }
    
    /**
     * Waits for the URL to match exactly
     * @param expectedUrl the expected URL
     * @param timeoutInSeconds the maximum time to wait
     */
    public void waitForUrlToBe(String expectedUrl, int timeoutInSeconds) {
        WebDriverWait urlWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        urlWait.until(ExpectedConditions.urlToBe(expectedUrl));
    }
    
    /**
     * Scrolls to a specific element
     * @param element the WebElement to scroll to
     */
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Scrolls to a specific element with offset
     * @param element the WebElement to scroll to
     * @param offsetY the vertical offset (positive or negative)
     */
    public void scrollToElementWithOffset(WebElement element, int offsetY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0, " + offsetY + ")");
    }
    
    /**
     * Types text into an input field after clearing it
     * @param element the input WebElement
     * @param text the text to type
     */
    public void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Checks if an element is displayed
     * @param locator the By locator strategy
     * @return true if element is displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Waits for an element to be visible
     * @param locator the By locator strategy
     * @param timeoutInSeconds the maximum time to wait
     */
    public void waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait elementWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        elementWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Waits for an element to be clickable
     * @param locator the By locator strategy
     * @param timeoutInSeconds the maximum time to wait
     */
    public void waitForElementClickable(By locator, int timeoutInSeconds) {
        WebDriverWait elementWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        elementWait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}