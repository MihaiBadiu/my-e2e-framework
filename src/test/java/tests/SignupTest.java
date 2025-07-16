package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SignupPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupTest extends BaseTest {

    private SignupPage signupPage;

    @BeforeEach
    public void setUpTest() {
        super.setUp();
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginLink();
        signupPage = new SignupPage(driver);
    }

    @Test
    public void testValidSignup() {
        signupPage.enterName("TestUser");
        signupPage.enterEmail("validuser" + System.currentTimeMillis() + "@example.com"); // email unic
        signupPage.clickSignupButton();
        assertTrue(signupPage.isAccountInformationVisible(), "❌ Pagina de creare cont nu este afișată.");
    }

    @Test
    public void testSignupWithEmptyFields() {
        signupPage.enterName("");
        signupPage.enterEmail("");
        signupPage.clickSignupButton();

        assertTrue(signupPage.isEmptyFieldsValidationTriggered(),
                "❌ Nu a fost afișat mesajul de validare pentru câmpuri goale (browser native).");
    }

    @Test
    public void testSignupWithInvalidEmail() {
        signupPage.enterName("TestUser");
        signupPage.enterEmail("invalidemail"); // lipsă @ și domeniu
        signupPage.clickSignupButton();

        assertTrue(signupPage.isInvalidEmailTooltipDisplayed(),
                "❌ Nu a fost afișat mesajul de eroare pentru email invalid.");
    }

    @Test
    public void testSignupWithShortName() {
        signupPage.enterName("T"); // presupunem că numele trebuie să fie mai lung
        signupPage.enterEmail("shortname" + System.currentTimeMillis() + "@example.com");
        signupPage.clickSignupButton();

        assertTrue(signupPage.isAccountInformationVisible() || signupPage.isAlreadyRegisteredErrorDisplayed(),
                "❌ Nu a fost afișat mesajul de eroare sau pagina de creare cont.");
    }

    @Test
    public void testSignupWithDuplicateEmail() {
        signupPage.enterName("ExistingUser");
        signupPage.enterEmail("existing@example.com"); // trebuie să fie deja înregistrat
        signupPage.clickSignupButton();

        assertTrue(signupPage.isDuplicateEmailErrorDisplayed(),
                "❌ Nu a fost afișat mesajul de eroare pentru email existent.");
    }
}
