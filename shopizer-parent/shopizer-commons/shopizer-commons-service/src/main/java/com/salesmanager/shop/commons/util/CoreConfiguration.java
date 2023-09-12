package com.salesmanager.shop.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class CoreConfiguration {


    private static final Logger LOGGER = LoggerFactory.getLogger(CoreConfiguration.class);
    public Properties properties = new Properties();

    public CoreConfiguration() {
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String propertyKey) {

        return properties.getProperty(propertyKey);


    }

    public String getProperty(String propertyKey, String defaultValue) {

        String prop = defaultValue;
        try {
            prop = properties.getProperty(propertyKey);
        } catch (Exception e) {
            LOGGER.warn("Cannot find property " + propertyKey);
        }
        return prop;


    }

}
