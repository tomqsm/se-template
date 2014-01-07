package com.tomasz.design.framuga.config;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Tomasz
 */
public class ConfigurationTest {

    public ConfigurationTest() {
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
    public void printsAdequateInfoWhenNoConfigFile() {
        final Configuration config = Configuration.COLOR;
        assertNotNull(config);
        assertEquals("blue", Configuration.COLOR.toString());
    }
    
    @Test
    public void savesPropertyWhenChanged() throws ConfigurationException{
        long currentTime = System.nanoTime();
        Configuration.COLOR.setValue(String.valueOf(currentTime));
        assertEquals(String.valueOf(currentTime), Configuration.COLOR.toString());
    }
}
