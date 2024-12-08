package duth.dip.cse.util.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private static final String USER_PROPERTIES_FILE = "application.properties";
    private static final String DEFAULT_PROPERTIES_FILE = "default-application.properties";

    private static final Properties userDefinedProperties = new Properties();
    private static final Properties defaultProperties = new Properties();


    static {
        try (InputStream userInput = PropertyLoader.class.getClassLoader().getResourceAsStream(USER_PROPERTIES_FILE);
            InputStream defaultInput = PropertyLoader.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE)) {

            if (defaultInput == null)
                throw new RuntimeException("Property file not found!");

            defaultProperties.load(defaultInput);

            if(userInput != null)
                userDefinedProperties.load(userInput);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static String getProperty(String key) {
        return userDefinedProperties.getProperty(key)!=null
                ? userDefinedProperties.getProperty(key)
                : defaultProperties.getProperty(key);
    }
}

