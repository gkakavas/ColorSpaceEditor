package duth.dip.cse.engine.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private static final Properties PROPERTIES = new Properties();

    public static void addProperties(String propertiesFilePath){
        try (InputStream inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(propertiesFilePath)) {
            var properties = new Properties();
            properties.load(inputStream);
            PROPERTIES.putAll(properties);;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}

