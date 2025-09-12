package pages;

import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class substore_page extends StartupPage {

    // Locators
    public By usernameField = By.id("username_id");
    public By passwordField = By.xpath("//input[@id='password']");
    public By signInButton = By.xpath("//button[@id='login']");
    public By dropDownMenu = By.xpath("//a[@href='#/WardSupply']");

    public substore_page(WebDriver driver) {
        super(driver);
    }

    public boolean loginToHealthAppByGivenValidCredetial(Map<String, String> expectedData) throws Exception {
        Boolean success = false;
        try {
            WebElement usernameElement = commonEvents.findElement(usernameField);
            commonEvents.highlightElement(usernameElement);
            commonEvents.sendKeys(usernameField, expectedData.get("username"));

            WebElement passwordElement = commonEvents.findElement(passwordField);
            commonEvents.highlightElement(passwordElement);
            commonEvents.sendKeys(passwordField, expectedData.get("password"));

            WebElement signInElement = commonEvents.findElement(signInButton);
            commonEvents.highlightElement(signInElement);
            commonEvents.click(signInButton);
            
            success = true;
        } catch (Exception e) {
            throw e;
        }
        return success;
    }
}