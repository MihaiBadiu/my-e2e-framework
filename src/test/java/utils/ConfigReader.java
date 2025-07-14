package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Fișierul config.properties nu a fost găsit în resources!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Eroare la încărcarea fișierului config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl", "https://automationexercise.com");
    }
}
