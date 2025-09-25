package edu.ccrm.service;

import java.util.Properties;

// Used the Singleton pattern to hold app-wide settings.
// This ensures there's only one configuration object.
public class AppConfig {
    private static AppConfig instance;
    private final Properties properties = new Properties();

    private AppConfig() {
        // Default properties
        properties.setProperty("data.folder", "data");
        properties.setProperty("backup.folder", "backups");
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}