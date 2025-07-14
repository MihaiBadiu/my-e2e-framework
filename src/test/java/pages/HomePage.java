package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By loginLink = By.xpath("//a[contains(text(),'Signup / Login')]");

    private By cookieIframe = By.cssSelector("iframe[src*='consent']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookiePopupIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(cookieIframe));

            By consentButton = By.xpath("//button[contains(text(),'Consent')]");

            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(consentButton));
            btn.click();

            driver.switchTo().defaultContent();

        } catch (NoSuchElementException | TimeoutException e) {
            driver.switchTo().defaultContent();
        }
    }

    public void clickLoginLink() {
        closeCookiePopupIfPresent();
        driver.findElement(loginLink).click();
    }
}
