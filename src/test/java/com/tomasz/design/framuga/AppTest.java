package com.tomasz.design.framuga;

import com.tomasz.design.framuga.util.PurchaseOrder;
import java.math.BigDecimal;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.BindyType;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test
     */
    public void testApp() throws Exception {
        CamelContext cc = new DefaultCamelContext();
        cc.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:toCsv").marshal().bindy(BindyType.Csv, "com.tomasz.design.framuga.util").to("mock:result");
            }
        });
        cc.start();
        MockEndpoint mock = cc.getEndpoint("mock:result", MockEndpoint.class);
        mock.expectedBodiesReceived("1|49,95|Camel in Action\n");
        PurchaseOrder order = new PurchaseOrder();
        order.setAmount(1);
        order.setPrice(new BigDecimal("49.95"));
        order.setName("Camel in Action");

        ProducerTemplate template = cc.createProducerTemplate();
        template.sendBody("direct:toCsv", order);
        mock.assertIsSatisfied();

    }


}
