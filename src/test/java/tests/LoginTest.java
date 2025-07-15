package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginTest {

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox"})
    public void loginWithValidCredentials_MultiBrowser(String browser) {
        BaseTest baseTest = new BaseTest(browser);
        baseTest.setUp();

        HomePage home = new HomePage(baseTest.driver);
        home.clickLoginLink();

        LoginPage login = new LoginPage(baseTest.driver);
        login.enterEmail("blana1234@mail.com");
        login.enterPassword("blana1234!");
        login.clickLoginButton();

        assertTrue(login.isLoginSuccessful(), "Login-ul cu credențiale valide a eșuat pe browserul: " + browser);

        baseTest.tearDown();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox"})
    public void loginWithInvalidCredentials_MultiBrowser(String browser) {
        BaseTest baseTest = new BaseTest(browser);
        baseTest.setUp();

        HomePage home = new HomePage(baseTest.driver);
        home.clickLoginLink();

        LoginPage login = new LoginPage(baseTest.driver);
        login.enterEmail("invalid@email.com");
        login.enterPassword("wrongpassword");
        login.clickLoginButton();

        assertTrue(login.isLoginErrorDisplayed(), "Mesajul de eroare nu a apărut pe browserul: " + browser);
        assertFalse(login.isLoginSuccessful(), "Login-ul a reușit când nu trebuia pe browserul: " + browser);

        baseTest.tearDown();
    }
}
