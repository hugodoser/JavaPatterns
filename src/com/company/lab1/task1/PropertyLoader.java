package com.company.lab1.task1;

import java.io.*;
import java.util.Properties;

public final class PropertyLoader {
    //volatile чтобы двойная проверка блокировки сработала правильно
    private static volatile PropertyLoader singletonPropertyLoader;
    private Properties properties;

    private PropertyLoader() {
        readProperty();
    }

    public static PropertyLoader getLoader() {
        PropertyLoader result = singletonPropertyLoader;
        if (result != null) {
            return result;
        }
        synchronized (PropertyLoader.class) {
            if (singletonPropertyLoader == null) {
                singletonPropertyLoader = new PropertyLoader();
            }
            return singletonPropertyLoader;
        }
    }

    public Properties getProperties() {
        return properties;
    }

    private void readProperty() {
        properties = new Properties();
        try {
            InputStream input = new FileInputStream("resources/config.properties");
            properties.load(input);
            System.out.println("Считан файл свойств единственный раз");
        } catch (IOException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }
    }
}