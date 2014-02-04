package com.tomasz.design.framuga.jaxb;

import com.tomasz.design.framuga.jaxb.generated.Shiporder;
import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.FileUtils;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

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
        final boolean valid = XmlUtils.validateAgainstXSD(
                FileUtils.openInputStream(SHIPORDER_XML),
                FileUtils.openInputStream(SHIPORDER_SCHEMA));
        assertEquals(true, valid);
    }

    @Test
    public void unmarshalsConfig() throws Exception {
        Shiporder order = XmlUtils.unmarshall(Shiporder.class, FileUtils.openInputStream(SHIPORDER_XML));
        assertNotNull(order);
        assertThat(order.getItem()).hasSize(2);
    }

    @Test
    public void marshaledCorrectly() throws IOException, JAXBException {
        Shiporder order = XmlUtils.unmarshall(Shiporder.class, FileUtils.openInputStream(SHIPORDER_XML));
        assertNotNull(order);
        assertThat(order.getItem()).hasSize(2);
        XmlUtils.marshall(order, SHIPORDER_MARSHALLED_XML);
        order = XmlUtils.unmarshall(Shiporder.class, FileUtils.openInputStream(SHIPORDER_MARSHALLED_XML));
        assertNotNull(order);
        assertThat(order.getItem()).hasSize(2);
    }

}
