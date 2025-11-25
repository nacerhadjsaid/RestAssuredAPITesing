package utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {
    public static String readJsonFile(String path) {
        try {
            return new String(Files.readAllBytes(
                    Paths.get(ClassLoader.getSystemResource(path).toURI())
            ));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + path, e);
        }
    }
}
