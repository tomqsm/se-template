package com.tomasz.design.framuga;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class OperatorTest {
    
    public OperatorTest() {
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

    /**
     * Test of devide method, of class Operator.
     */
    @Test
    public void doesntAllowDevision0() {
        thrown.expect(AppException.class);
        thrown.expectMessage("Not allowed.");
        Operator operator = new Operator();
        operator.devide(2, 0);
    }
}