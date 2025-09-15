package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerificationPage_PL1 extends StartupPage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public VerificationPage_PL1(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
    }

    // ===== Locators =====
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By loginBtn = By.id("loginBtn");
    private By verificationMenu = By.xpath("//a[contains(text(),'Verification')]");
    private By subModuleButtons = By.xpath("//div[@class='sub-module']//button");
    private By requisitionTab = By.xpath("//a[contains(text(),'Requisition')]");
    private By purchaseRequestTab = By.xpath("//a[contains(text(),'Purchase Request')]");
    private By okBtn = By.xpath("//button[text()='OK']");
    private By tooltipStar = By.xpath("//span[@class='tooltip-star']");
    private By tooltipText = By.xpath("//div[@class='tooltip-text']");
    private By dateFrom = By.id("fromDate");
    private By dateTo = By.id("toDate");
    private By dateRangeDropdown = By.id("dateRange");

    // ===== Methods =====

    public boolean login() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys("testuser@yaksha.com");
            driver.findElement(passwordInput).sendKeys("Test@123");
            driver.findElement(loginBtn).click();
            wait.until(ExpectedConditions.urlContains("dashboard"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean openVerificationMenu() {
        try {
            WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(verificationMenu));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menu);
            menu.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean successfullNavigation(String module) {
        try {
            return driver.getCurrentUrl().contains(module.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyButtonPresentWithText(String text) {
        try {
            WebElement button = driver.findElement(By.xpath("//button[contains(text(),'" + text + "')]"));
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickOnButtonByText(String text) {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'" + text + "')] | //a[contains(text(),'" + text + "')]")));
            button.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyVerificationComponentsAreVisible() {
        try {
            return driver.findElement(requisitionTab).isDisplayed() &&
                   driver.findElement(purchaseRequestTab).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean chooseDate(String from, String to) {
        try {
            WebElement fromElement = driver.findElement(dateFrom);
            WebElement toElement = driver.findElement(dateTo);
            fromElement.clear();
            fromElement.sendKeys(from);
            toElement.clear();
            toElement.sendKeys(to);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean selectRadioButton(String text) {
        try {
            WebElement radio = driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]/preceding-sibling::input[@type='radio']"));
            if(!radio.isSelected()) radio.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickOkButton() {
        try {
            driver.findElement(okBtn).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyDateRange(String from, String to) {
        try {
            List<WebElement> dates = driver.findElements(By.xpath("//table//td[@class='date']"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fromDate = LocalDate.parse(from, formatter);
            LocalDate toDate = LocalDate.parse(to, formatter);

            for (WebElement d : dates) {
                LocalDate date = LocalDate.parse(d.getText(), formatter);
                if (date.isBefore(fromDate) || date.isAfter(toDate)) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String verifyToolTipText() {
        try {
            actions.moveToElement(driver.findElement(tooltipStar)).perform();
            return driver.findElement(tooltipText).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean clickTooltip() {
        try {
            driver.findElement(tooltipStar).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyDateRangeforReq(String from, String to) {
        return verifyDateRange(from, to);
    }

    public boolean chooseDateRange(String range) {
        try {
            WebElement dropdown = driver.findElement(dateRangeDropdown);
            dropdown.click();
            dropdown.sendKeys(range);
            dropdown.sendKeys(Keys.ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
  }
