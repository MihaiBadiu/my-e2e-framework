package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Locatori pentru Login
    private By emailField = By.xpath("//input[@data-qa='login-email']");
    private By passwordField = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By successMessage = By.xpath("//a[contains(text(),'Logged in as')]");
    private By errorMessage = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    // Locator pentru "Forgot your password?" link
    private By forgotPasswordLink = By.xpath("//a[contains(text(),'Forgot your password')]");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Acțiuni pe pagina de Login
    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccessful() {
        return driver.findElements(successMessage).size() > 0;
    }

    public boolean isLoginErrorDisplayed() {
        return driver.findElements(errorMessage).size() > 0;
    }

    // ✅ Metoda necesară pentru testul Forgot Password
    public void clickForgotPassword() {
        driver.findElement(forgotPasswordLink).click();
    }
}
