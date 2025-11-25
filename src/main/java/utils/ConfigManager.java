package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("config/config.properties"));
        } catch (Exception e) {
            throw new RuntimeException("❌ Could not load config file!", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
