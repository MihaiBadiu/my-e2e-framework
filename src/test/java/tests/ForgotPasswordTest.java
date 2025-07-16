package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForgotPasswordTest extends BaseTest {

    private ForgotPasswordPage forgotPage;

    @BeforeEach
    public void setUpTest() {
        super.setUp();
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();
        forgotPage = new ForgotPasswordPage(driver);
    }

    @Test
    public void testValidPasswordReset() {
        forgotPage.enterEmail("validuser@example.com");
        forgotPage.clickRetrieveButton();
        assertTrue(forgotPage.isSuccessMessageDisplayed(),
                "Mesajul de succes pentru resetare parolă nu este afișat.");
    }

    @Test
    public void testResetWithInvalidEmailFormat() {
        forgotPage.enterEmail("invalidemail");
        forgotPage.clickRetrieveButton();
        assertTrue(forgotPage.isErrorMessageDisplayed(),
                "Mesajul de eroare pentru email invalid nu este afișat.");
    }

    @Test
    public void testResetWithEmptyEmail() {
        forgotPage.enterEmail("");
        forgotPage.clickRetrieveButton();
        assertTrue(forgotPage.isErrorMessageDisplayed(),
                "Mesajul de eroare pentru email gol nu este afișat.");
    }

    @Test
    public void testResetWithUnregisteredEmail() {
        forgotPage.enterEmail("nonexistent@example.com");
        forgotPage.clickRetrieveButton();
        // Folosim metoda existentă isErrorMessageDisplayed() pentru email neînregistrat
        assertTrue(forgotPage.isErrorMessageDisplayed(),
                "Nu este afișat mesajul pentru email inexistent.");
    }
}
