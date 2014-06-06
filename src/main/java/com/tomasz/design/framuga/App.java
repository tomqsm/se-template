package com.tomasz.design.framuga;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("Starting application.");
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        Operatorable operatorable = ac.getBean("myOperator", DivisionOperator.class);
        System.out.println(operatorable.devide(12, 6));
        CamelContext camelContext = new DefaultCamelContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        camelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("file:data/inbox?noop=true")
                        .choice().when(header("CamelFileName").endsWith(".xml"))
                        .process(new Processor() {

                            @Override
                            public void process(Exchange exchange) throws Exception {
                                Message message = exchange.getIn();
                                String body = message.getBody(String.class);
                                body = body + " enriched " + message.getHeader("CamelFileName", String.class);
                                message.setBody(body, String.class);
                                exchange.setIn(message);
                            }
                        }).convertBodyTo(String.class)
                        .to("jms:orders");
                from("jms:orders").process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Message message = exchange.getIn();
                        String body = message.getBody(String.class);
                        System.out.println(body);
                    }
                }).to("file:data/outbox");
                from("netty-http:http://localhost:9999").process(new Processor() {

                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getOut().getHeaders());
                    }
                }).to("http://localhost:8084/servlet_tomcat/?bridgeEndpoint=true");
            }
        });
        camelContext.start();
//        Thread.sleep(50000);
//        camelContext.stop();
    }


}
