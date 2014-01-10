package com.tomasz.design.framuga.guava.executor;

import com.tomasz.design.framuga.guava.executor.GuavaExpriment;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author kusmierc
 */
public class GuavaExprimentTest {

    public GuavaExprimentTest() {
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

    @Test
    public void testSomeMethod() throws IOException {
        ImmutableList<String> lines = Files.asCharSource(new File("config/configuration.xml"), Charsets.UTF_8).readLines();
        System.err.println("lines = " + lines.size());
        GuavaExpriment ge = new GuavaExpriment();
    }

}
