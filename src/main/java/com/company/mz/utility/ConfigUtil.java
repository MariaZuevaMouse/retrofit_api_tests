package com.company.mz.utility;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class ConfigUtil {
    Properties properties = new Properties();
    private static InputStream configFile;

    static {
        try{
            configFile = new FileInputStream("src/main/resources/application.properties");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public String getBaseUrl(){
        properties.load(configFile);
        return properties.getProperty("base.url");
    }
}
