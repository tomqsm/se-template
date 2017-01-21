package com.tomasz.design.framuga.jaxb;

import com.tomasz.design.framuga.jaxb.generated.Shiporder;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

//import static org.fest.assertions.api.Assertions.assertThat; // main one
//import static org.fest.assertions.api.Assertions.atIndex; // for List assertion
//import static org.fest.assertions.api.Assertions.entry;  // for Map assertion
//import static org.fest.assertions.api.Assertions.extractProperty; // for Iterable/Array assertion
//import static org.fest.assertions.api.Assertions.fail; // use when making exception tests
//import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown; // idem
//import static org.fest.assertions.api.Assertions.filter; // for Iterable/Array assertion
//import static org.fest.assertions.api.Assertions.offset; // for floating number assertion
//import static org.fest.assertions.api.Assertions.anyOf; // use with Condition
/**
 *
 * @author kusmierc
 */
public class XmlUtilsTest {

    private static final File SHIPORDER_XML = new File("src/main/resources/jaxb/xmls/shiporder.xml");
    private static final File SHIPORDER_MARSHALLED_XML = new File("src/main/resources/jaxb/xmls/shiporderMarshalled.xml");
    private static final File SHIPORDER_SCHEMA = new File("src/main/resources/jaxb/schemas/shiporder.xsd");

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void canValidateAgainstSchema() throws Exception {
        boolean valid = false;
        try{
            valid = XmlUtils.validateAgainstXSD(
                    FileUtils.openInputStream(SHIPORDER_XML),
                    FileUtils.openInputStream(SHIPORDER_SCHEMA));
        } catch (Exception e){
            fail();
        }

        assertEquals(true, valid);
    }

    @Test
    public void unmarshalsConfig() throws Exception {
        Shiporder order = XmlUtils.unmarshal(Shiporder.class, FileUtils.openInputStream(SHIPORDER_XML));
        assertNotNull(order);
        assertThat(order.getItem()).hasSize(2);
    }

    @Test
    public void marshaledCorrectly() throws IOException, JAXBException {
        Shiporder order = XmlUtils.unmarshal(Shiporder.class, FileUtils.openInputStream(SHIPORDER_XML));
        assertNotNull(order);
        assertThat(order.getItem()).hasSize(2);
        XmlUtils.marshal(order, SHIPORDER_MARSHALLED_XML);
        order = XmlUtils.unmarshal(Shiporder.class, FileUtils.openInputStream(SHIPORDER_MARSHALLED_XML));
        assertNotNull(order);
        assertThat(order.getItem()).hasSize(2);
    }

}
