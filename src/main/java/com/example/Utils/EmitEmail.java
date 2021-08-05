package com.example.Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitEmail {
    private static final String EXCHANGE_NAME = "email";
    private static final String ROUTING_KEY1 = "email.activation";

    public static boolean emitEmail(String msg, String email){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.102.192.55");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY1, null, msg.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + msg + "'");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        emitEmail("test", "123@123.com");
    }

}
