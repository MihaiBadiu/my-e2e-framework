package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // === Locatori ===
    private By nameInput = By.xpath("//input[@name='name']");
    private By emailInput = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[text()='Signup']");
    private By errorMsg = By.xpath("//p[contains(text(),'already registered')]");
    private By duplicateEmailError = By.xpath("//p[contains(text(), 'Email Address already exist')]");
    private By accountInfoSection = By.xpath("//b[contains(text(),'Enter Account Information')]");

    // === Constructor ===
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // === Acțiuni ===
    public void enterName(String name) {
        WebElement nameField = driver.findElement(nameInput);
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(emailInput);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickSignupButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();
    }

    // === Validări ===
    public boolean isAccountInformationVisible() {
        return driver.findElements(accountInfoSection).size() > 0;
    }

    public boolean isAlreadyRegisteredErrorDisplayed() {
        return driver.findElements(errorMsg).size() > 0;
    }

    public boolean isDuplicateEmailErrorDisplayed() {
        try {
            return driver.findElement(duplicateEmailError).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isInvalidEmailTooltipDisplayed() {
        try {
            WebElement emailField = driver.findElement(emailInput);
            emailField.sendKeys(Keys.TAB);
            wait.until(ExpectedConditions.attributeContains(emailField, "validationMessage", "@"));
            String tooltipText = emailField.getAttribute("validationMessage");
            return tooltipText.contains("Please include an '@' in the email address");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmptyFieldsValidationTriggered() {
        try {
            WebElement nameField = driver.findElement(nameInput);
            WebElement emailField = driver.findElement(emailInput);
            String nameValidation = nameField.getAttribute("validationMessage");
            String emailValidation = emailField.getAttribute("validationMessage");

            return nameValidation.contains("Please fill out this field") ||
                    emailValidation.contains("Please fill out this field");
        } catch (Exception e) {
            return false;
        }
    }
}
