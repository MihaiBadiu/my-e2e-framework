package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By loginLink = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By homeLogo = By.xpath("//div[@class='logo pull-left']");
    private By welcomeMessage = By.xpath("//h2[contains(text(),'Full-Fledged')]");
    private By consentButton = By.cssSelector("button.fc-cta-consent");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void closeCookiePopupIfPresent() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(consentButton));
            btn.click();
            System.out.println("Consent button clicked");
        } catch (Exception e) {
            System.out.println("Consent popup not present or already closed");
        }
    }

    public void clickLoginLink() {
        closeCookiePopupIfPresent();
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    // Metode pentru verificarea vizibilității elementelor, cu așteptare explicită
    public boolean isLoginLinkVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHomeLogoVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(homeLogo)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWelcomeMessageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
