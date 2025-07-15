package tests;

import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageElementsAreVisible() {
        HomePage homePage = new HomePage(driver);

        assertTrue(homePage.isLoginLinkVisible(), "Linkul de Login nu este vizibil pe pagina principală");
        assertTrue(homePage.isHomeLogoVisible(), "Logo-ul site-ului nu este vizibil");
        assertTrue(homePage.isWelcomeMessageVisible(), "Mesajul de bun venit nu este vizibil pe pagina principală");
    }

    @Test
    public void testNavigationToLoginPage() {
        HomePage homePage = new HomePage(driver);

        homePage.clickLoginLink();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/login") || currentUrl.contains("signup"), "Navigarea către pagina de login nu a avut loc");
    }
}