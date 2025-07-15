package pages;
// Pachetul în care se află clasa LoginPage (parte din arhitectura Page Object Model).

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// Importă clasele necesare pentru localizarea elementelor și interacțiunea cu WebDriver.

public class LoginPage {
    private WebDriver driver;
    // Instanța WebDriver folosită pentru a accesa și controla elementele paginii de login.

    // Definirea locatorilor pentru elementele paginii de login
    private By emailField = By.xpath("//input[@data-qa='login-email']");
    // Locator pentru câmpul de email

    private By passwordField = By.xpath("//input[@data-qa='login-password']");
    // Locator pentru câmpul de parolă

    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    // Locator pentru butonul de login

    private By successMessage = By.xpath("//a[contains(text(),'Logged in as')]");
    // Locator pentru mesajul care apare după autentificare reușită („Logged in as...”)

    private By errorMessage = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");
    // Locator pentru mesajul de eroare afișat la autentificare eșuată

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    // Constructor care primește obiectul WebDriver și îl asociază acestei pagini.

    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        // Golește câmpul de email înainte de a introduce o nouă valoare.

        driver.findElement(emailField).sendKeys(email);
        // Introduce adresa de email în câmpul corespunzător.
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        // Golește câmpul de parolă înainte de a introduce o nouă valoare.

        driver.findElement(passwordField).sendKeys(password);
        // Introduce parola în câmpul corespunzător.
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        // Apasă pe butonul de login pentru a trimite formularul.
    }

    public boolean isLoginSuccessful() {
        return driver.findElements(successMessage).size() > 0;
        // Verifică dacă elementul „Logged in as...” este prezent, ceea ce indică un login reușit.
    }

    public boolean isLoginErrorDisplayed() {
        return driver.findElements(errorMessage).size() > 0;
        // Verifică dacă mesajul de eroare este prezent, indicând un login eșuat.
    }
}
