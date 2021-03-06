package com.tomasz.design.framuga.config;

import java.util.List;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Tomasz
 */
public class ConfigProviderTest {

    public ConfigProviderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testInitialiseXMLConfiguration() {
        final int first = 0;
        ConfigProvider configProvider = new ConfigProvider();
        assertNotNull(configProvider);
        configProvider.initialiseXMLConfiguration("config/configuration.xml");
        final List<HierarchicalConfiguration> props = configProvider
                .getXMLConfiguration().configurationsAt("properties");
        HierarchicalConfiguration hc = props.get(first);
        final String color = hc.getString("color");
        assertEquals("blue", color);
    }
    
    @Test
    public void throwsWhenFileNotFound() {
        final int first = 0;
        final String expectedMessage = "Cannot locate configuration source configurationWrong.xml";
        thrown.expectMessage(expectedMessage);
        ConfigProvider configProvider = new ConfigProvider();
        assertNotNull(configProvider);
        configProvider.initialiseXMLConfiguration("configurationWrong.xml");
        final List<HierarchicalConfiguration> props = configProvider
                .getXMLConfiguration().configurationsAt("properties");
        HierarchicalConfiguration hc = props.get(first);
        final String color = hc.getString("color");
        assertEquals("blue", color);
    }

}
