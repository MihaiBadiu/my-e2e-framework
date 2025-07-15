package pages;
// Pachetul în care se află clasa HomePage (parte din structura Page Object Model).

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
// Importă toate clasele necesare pentru interacțiunea cu WebDriver, așteptări și gestionarea excepțiilor.

public class HomePage {
    private WebDriver driver;
    // Instanța de WebDriver folosită pentru a interacționa cu elementele din pagină.

    private By loginLink = By.xpath("//a[contains(text(),'Signup / Login')]");
    // Locator pentru linkul de „Signup / Login” din meniul principal al site-ului.

    private By cookieIframe = By.cssSelector("iframe[src*='consent']");
    // Locator pentru iframe-ul care conține bannerul de consimțământ pentru cookie-uri.

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    // Constructor care primește WebDriver-ul și îl stochează în variabila locală pentru utilizare ulterioară.

    public void closeCookiePopupIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By consentButton = By.cssSelector("button.fc-cta-consent");

            // Așteaptă să fie clicabil butonul Consent (dacă există)
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(consentButton));
            btn.click();
            System.out.println("Consent button clicked");

        } catch (Exception e) {
            // Dacă butonul nu există sau nu poate fi găsit, se ignoră
            System.out.println("Consent popup not present or already closed");
        }
    }




    public void clickLoginLink() {
        closeCookiePopupIfPresent();
        // Închide pop-up-ul de cookies dacă este prezent, înainte de orice acțiune.

        driver.findElement(loginLink).click();
        // Găsește și apasă pe linkul „Signup / Login” din meniu.
    }
}
