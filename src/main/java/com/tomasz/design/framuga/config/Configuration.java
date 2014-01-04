package com.tomasz.design.framuga.config;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

/**
 *
 * @author Tomasz
 */
public enum Configuration {

    COLOR {
                @Override
                public String toString() {
                    HierarchicalConfiguration hc = XML_CONFIG.configurationsAt("properties").get(FIRST);
                    return hc.getString("color");
                }
            };

    static {
        ConfigProvider configProvider = new ConfigProvider();
        configProvider.initialiseXMLConfiguration("config/configuration.xml");
        XML_CONFIG = configProvider.getXMLConfiguration();
    }
    private static XMLConfiguration XML_CONFIG;
    private static final int FIRST = 0;
}
