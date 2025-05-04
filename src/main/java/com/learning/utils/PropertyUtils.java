package com.learning.utils;

import com.learning.constants.FrameworkConstants;
import com.learning.enums.PropertiesType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertyUtils {

    // Utility class for managing properties loaded from a configuration file.
    // This class follows the Singleton pattern to ensure that properties are loaded only once during class initialization.
    private PropertyUtils() {
    }

    // A Properties object to hold key-value pairs read from the properties file.
    private static Properties properties = new Properties();

    // A HashMap to store the properties in a more accessible format,
    // allowing for quick retrieval by key.
    private static Map<String, String> MAP = new HashMap<>();

    // Static block that executes when the class is loaded.
    // This block is responsible for reading the properties from the specified file and populating the MAP.
    static {
        try (FileInputStream inputStream = new FileInputStream(FrameworkConstants.getConfigFilePath())) { // Automatically closes the stream
            // Load properties from the input stream into the Properties object.
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            // Exit the application if the properties file cannot be read.
            System.exit(0);
        }

        // Populate the MAP with entries from the Properties object,
        // converting keys and values to String and storing them in lowercase.
        properties.entrySet().forEach(e -> MAP.put(String.valueOf(e.getKey()), String.valueOf(e.getValue())));
//        properties.forEach((key, value) -> MAP.put(key.toString(), value.toString()));
    }

    // Public method to retrieve property values based on the provided PropertiesType key.
    // Converts the enum name to lowercase to match the keys in the MAP.
    public static String getValues(PropertiesType key) {
        return MAP.get(key.name().toLowerCase());
    }

    public static synchronized String getPropertyValue(String key) {
        return MAP.get(key);
    }

}
