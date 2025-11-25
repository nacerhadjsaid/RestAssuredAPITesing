package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Utils {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("../APITesting/config/config.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
