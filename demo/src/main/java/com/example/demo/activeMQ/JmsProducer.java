package com.example.demo.activeMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

public class JmsProducer {
    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;
        try {
            String brokerURL = "tcp://localhost:61616";
            ConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory(brokerURL);

            connection = mqConnectionFactory.createConnection();
            connection.start();

            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
