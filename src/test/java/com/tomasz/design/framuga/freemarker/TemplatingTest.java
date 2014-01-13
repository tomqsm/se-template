package com.tomasz.design.framuga.freemarker;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kusmierc
 */
public class TemplatingTest {

    private Templating templating;
    private ConfigStruct configStruct;

    @Before
    public void setUp() throws IOException {
        configStruct = new ConfigStruct();
        configStruct.setDataXmlFile(new File("src/main/resources/freemarker/withNspace/report.xml"));
        configStruct.setTemplateFile(new File("template.ftl"));
        configStruct.setDirectoryForTemplateLoading(new File("src/main/resources/freemarker/withNspace/"));
        configStruct.setOutput(new File("src/main/resources/freemarker/withNspace/output.txt"));
        templating = new Templating(configStruct);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void runsTemplateWithNamespaces() throws Exception {
        templating.parseTemplate();
    }
    
    @Test
    public void runsTemplateWithoutNamespaces() throws Exception {
        ConfigStruct configStructLocal = (ConfigStruct) this.configStruct.clone();
        configStructLocal.setOutput(new File("src/main/resources/freemarker/output.txt"));
        configStructLocal.setDirectoryForTemplateLoading(new File("src/main/resources/freemarker/"));
        configStructLocal.setDataXmlFile(new File("src/main/resources/freemarker/shiporder.xml"));
        Templating templatingLocal = new Templating(configStructLocal);
        templatingLocal.parseTemplate();
    }
}
