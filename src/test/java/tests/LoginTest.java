package tests;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginTest extends BaseTest {

    @Test
    public void loginWithValidCredentials() {
        HomePage home = new HomePage(driver);
        home.clickLoginLink();

        LoginPage login = new LoginPage(driver);
        login.enterEmail("blana1234@mail.com");      // Înlocuiește cu date reale valide
        login.enterPassword("blana1234!");     // Înlocuiește cu parola validă
        login.clickLoginButton();

        assertTrue(login.isLoginSuccessful(), "Login-ul cu credențiale valide a eșuat.");
    }

    @Test
    public void loginWithInvalidCredentials() {
        HomePage home = new HomePage(driver);
        home.clickLoginLink();

        LoginPage login = new LoginPage(driver);
        login.enterEmail("invalid@email.com");
        login.enterPassword("wrongpassword");
        login.clickLoginButton();

        assertTrue(login.isLoginErrorDisplayed(), "Mesajul de eroare nu a apărut pentru credențiale invalide.");
        assertFalse(login.isLoginSuccessful(), "Login-ul a reușit când nu trebuia.");
    }
}