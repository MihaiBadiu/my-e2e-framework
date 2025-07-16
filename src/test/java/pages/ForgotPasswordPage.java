package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    // Locatori
    private By emailInput = By.xpath("//input[@data-qa='forgot-password-email']");
    private By retrieveButton = By.xpath("//button[text()='Retrieve Password']");
    private By errorMsg = By.xpath("//p[contains(text(),'Email address not found')]");
    private By successMsg = By.xpath("//p[contains(text(),'email has been sent')]");

    // Constructor
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    // Acțiuni
    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickRetrieveButton() {
        driver.findElement(retrieveButton).click();
    }

    // Validări compatibile cu testele

    // Verifică dacă mesajul de succes pentru resetarea parolei este afișat
    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMsg).size() > 0;
    }

    // Verifică dacă mesajul de eroare pentru email neînregistrat este afișat
    public boolean isErrorMessageDisplayed() {
        return driver.findElements(errorMsg).size() > 0;
    }
}
