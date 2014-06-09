package com.tomasz.design.framuga;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.jms.ConnectionFactory;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RouteDefinition;
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
        ModelCamelContext modelCamelContext = new DefaultCamelContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        modelCamelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        modelCamelContext.addRoutes(new RouteBuilder() {

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
                from("jetty:http://localhost:9999").process(new Processor() {

                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn());
                        HttpServletRequest request = exchange.getIn(HttpServletRequest.class);
                        System.out.println(request.getLocalAddr());
                    }
                }).to("http://localhost:8084/lukasfloorspring/?bridgeEndpoint=true");
//                from("jetty:http://localhost:9999/lukasfloorspring/resources/styles/standard.css").process(new Processor() {
//
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        System.out.println(exchange.getOut().getHeaders());
//                    }
//                }).to("http://localhost:8084/lukasfloorspring/resources/styles/standard.css?bridgeEndpoint=true");

//                from("stream:url?url=http://localhost:8084/lukasfloorspring&scanStream=true&encoding=UTF-8").process(new Processor() {
//
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                    }
//                }).to("stream:out");
                
//                from("servlet:///localhost:8084/lukasfloorspring/").process(new Processor() {
//
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        System.out.println(exchange.getOut().getHeaders());
//                    }
//                }).to("stream:out");
            }
        }
        );

//        modelCamelContext.addRouteDefinition(rd);
        modelCamelContext.start();
        Thread.sleep(50000);
        modelCamelContext.stop();
    }
}
