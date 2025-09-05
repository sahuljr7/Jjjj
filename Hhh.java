package pages;

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class FormManagement_L1_Pages extends StartupPage {

    public SoftAssert softAssert;
    private WebDriver driver;

    /*
     * WebElement Declaration
     */ 
    // TC-1&2 Locators
    By switchToNavigationMenu = By.xpath("//a[contains(text() , 'SwitchTo')]"); 
    By alertPopup = By.xpath("//a[contains(text() , 'Alerts')]"); 

    // TC-3 Locators
    By buttonToDisplayAnAlertBox = By.xpath("//button[contains(text() , 'click the button to display an  alert box:')]"); 

    // TC-4 Locators
    By registerNavigationMenu = By.xpath("//a[contains(text() , 'Register')]"); 
    By firstNameTextbox = By.xpath("//input[@placeholder='First Name']"); 
    By lastNameTextbox = By.xpath("//input[@placeholder='Last Name']"); 
    By addressInputAreabox = By.xpath("//textarea[@ng-model='Adress']"); 
    By emailAddressTextbox = By.xpath("//input[@type='email']"); 
    By phoneNumberTextbox = By.xpath("//input[@type='tel']"); 
    By maleRadioButton = By.xpath("//input[@value='Male']"); 
    By feMaleRadioButton = By.xpath("//input[@value='FeMale']"); 
    By cricketCheckBox = By.xpath("//input[@value='Cricket']"); 
    By moviesCheckBox = By.xpath("//input[@value='Movies']"); 
    By hockeyCheckBox = By.xpath("//input[@value='Hockey']"); 

    // TC-5,6
    By clickOnCountryDropdown = By.xpath("//span[@role='combobox']");
    By selectCountryAustralia = By.xpath("//li[contains(text(), 'Australia')]"); 
    By selectCountryBangladesh = By.xpath("//li[contains(text(), 'Bangladesh')]"); 
    By selectCountryDenmark = By.xpath("//li[contains(text(), 'Denmark')]"); 
    By selectCountryHongKong = By.xpath("//li[contains(text(), 'Hong Kong')]"); 
    By selectCountryIndia = By.xpath("//li[contains(text(), 'India')]"); 
    By selectCountryJapan = By.xpath("//li[contains(text(), 'Japan')]"); 

    // TC-9 Locators
    By selectYear = By.xpath("//select[@placeholder='Year']");
    By selectMonth = By.xpath("//select[@placeholder='Month']"); 
    By selectDate = By.xpath("//select[@placeholder='Day']"); 

    String pageName = this.getClass().getSimpleName();

    /*
     * constructor Initialization
     */ 
    public FormManagement_L1_Pages(WebDriver driver) {
        super(driver);
        this.driver = driver;
    } 

    // 🔹 Helper Methods (No more CommonEvents needed!)
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void sendKeys(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getAttribute(By locator, String attribute) {
        return driver.findElement(locator).getAttribute(attribute);
    }

    public boolean isDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public boolean isSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    /*
     * ====================== TEST METHODS ======================
     */

    /** TC-1 */
    public String validateTitleOfHomePage() throws Exception {
        return getTitle();
    }

    /** TC-2 */
    public String clickOnSwitchToAlertandValidateTitleOfPage() throws Exception {
        click(switchToNavigationMenu);
        click(alertPopup);
        return getTitle();
    } 

    /** TC-3 */
    public String handleAlertsPopupAndValidateTheTextInsideAnAlertsPopup() throws Exception {
        click(buttonToDisplayAnAlertBox);
        Alert alert = driver.switchTo().alert();
        Thread.sleep(1000);
        String alertPopUpMessage = alert.getText();
        alert.accept();
        return alertPopUpMessage;
    }

    /** TC-4 */
    public boolean clickOnRegisterLinkandFillTheForms(Map<String, String> expectedData) throws Exception {
        click(registerNavigationMenu);
        sendKeys(firstNameTextbox, expectedData.get("firstName"));
        sendKeys(lastNameTextbox, expectedData.get("lastName"));
        sendKeys(addressInputAreabox, expectedData.get("adds"));
        sendKeys(emailAddressTextbox, expectedData.get("emaiI"));
        sendKeys(phoneNumberTextbox, expectedData.get("phoneNo"));
        Thread.sleep(1000);
        click(maleRadioButton);
        click(cricketCheckBox);
        click(moviesCheckBox);
        click(hockeyCheckBox);

        return getAttribute(firstNameTextbox, "value").equals(expectedData.get("firstName")) &&
               getAttribute(lastNameTextbox, "value").equals(expectedData.get("lastName")) &&
               getAttribute(addressInputAreabox, "value").equals(expectedData.get("adds")) &&
               getAttribute(emailAddressTextbox, "value").equals(expectedData.get("emaiI")) &&
               getAttribute(phoneNumberTextbox, "value").equals(expectedData.get("phoneNo")) &&
               isSelected(maleRadioButton) &&
               isSelected(cricketCheckBox) &&
               isSelected(moviesCheckBox) &&
               isSelected(hockeyCheckBox);
    }

    /** TC-5 */
    public boolean clickOnSelectCountryAndSelectEachCountry() throws Exception {
        boolean isDropdownDisplayed = isDisplayed(clickOnCountryDropdown);

        // Select Bangladesh
        click(clickOnCountryDropdown);
        click(selectCountryBangladesh); 
        boolean isBangladeshSelected = getText(clickOnCountryDropdown).equals("Bangladesh");

        // Select Denmark
        click(clickOnCountryDropdown);
        click(selectCountryDenmark);
        boolean isDenmarkSelected = getText(clickOnCountryDropdown).equals("Denmark");

        // Select Hong Kong
        click(clickOnCountryDropdown);
        click(selectCountryHongKong);
        boolean isHongKongSelected = getText(clickOnCountryDropdown).equals("Hong Kong");

        // Select Australia
        click(clickOnCountryDropdown);
        click(selectCountryAustralia); 
        boolean isAustraliaSelected = getText(clickOnCountryDropdown).equals("Australia");

        return isDropdownDisplayed && isBangladeshSelected && isDenmarkSelected && isHongKongSelected && isAustraliaSelected;
    }

    /** TC-6 */
    public String selectAustraliaInCountryDrpdownAndValidate() throws Exception {
        click(clickOnCountryDropdown);
        click(selectCountryAustralia);	 
        return getText(clickOnCountryDropdown);
    }

    /** TC-7 */
    public boolean checkUncheckCheckBoxAndValidateThatCheckBox() throws Exception {
        WebElement cricket = findElement(cricketCheckBox);
        WebElement movies = findElement(moviesCheckBox);
        WebElement hockey = findElement(hockeyCheckBox);

        if (!cricket.isSelected()) click(cricketCheckBox);
        if (!movies.isSelected()) click(moviesCheckBox);
        if (!hockey.isSelected()) click(hockeyCheckBox);

        return cricket.isSelected() && movies.isSelected() && hockey.isSelected();
    }

    /** TC-8 */
    public boolean selectRadioButtonvalidateRadioButtonOptionIsSelectable() throws Exception {
        click(maleRadioButton);
        WebElement maleRadioButtonWebElement = findElement(maleRadioButton);
        WebElement femaleRadioButtonWebElement = findElement(feMaleRadioButton);

        boolean maleSelected = maleRadioButtonWebElement.isSelected() && !femaleRadioButtonWebElement.isSelected();

        Thread.sleep(1000);

        click(feMaleRadioButton);
        boolean femaleSelected = femaleRadioButtonWebElement.isSelected() && !maleRadioButtonWebElement.isSelected();

        return maleSelected && femaleSelected;
    }

    /** TC-9 */
    public boolean selectYearMonthDate() throws Exception {
        WebElement selectYeardropdown = findElement(selectYear);
        Select yearDropdown = new Select(selectYeardropdown);
        yearDropdown.selectByValue("1996");

        WebElement selectMonthdropdown = findElement(selectMonth);
        Select monthDropdown = new Select(selectMonthdropdown);
        monthDropdown.selectByVisibleText("June");

        WebElement selectDatedropdown = findElement(selectDate);
        Select dateDropdown = new Select(selectDatedropdown);
        dateDropdown.selectByValue("25"); 

        return selectDatedropdown.isDisplayed() && selectMonthdropdown.isDisplayed() && selectYeardropdown.isDisplayed();
    }
                                            }
