package com.tomasz.design.framuga;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class DivisionOperatorTest {

    public DivisionOperatorTest() {
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
     * Test of devide method, of class DivisionOperator.
     */
    @Test
    public void doesntAllowDevision0() {
        thrown.expect(AppException.class);
        thrown.expectMessage("Not allowed.");
        DivisionOperator operator = new DivisionOperator();
        operator.devide(2, 0);
    }
}