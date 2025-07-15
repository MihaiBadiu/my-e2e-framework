package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    private String browser;

    // Constructor implicit: ia browserul din config.properties
    public BaseTest() {
        this.browser = utils.ConfigReader.getBrowser();
    }

    // Constructor cu browser explicit (pentru parametrizare)
    public BaseTest(String browser) {
        this.browser = browser;
    }

    public void setUp() {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browserul specificat nu este suportat: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(utils.ConfigReader.getBaseUrl());
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
