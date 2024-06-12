package org.testproject.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigHelper.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            } else
            {
                // Load a properties file from class path, inside static block
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
