package com.cognizantTechno.test.helpers;

import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {

    private ConfigReader(){
        throw new IllegalStateException("ConfigReader class");
    }

    public static String getProperty(String key) {
        Properties p = new Properties();
        try {
            FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            p.load(reader);
            return p.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p.getProperty(key);
    }
}
