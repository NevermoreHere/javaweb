package com.example.Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveMail {
    private static final String EXCHANGE_NAME = "email";
    private static final String QUEUE_NAME = "email_queue";
    private static final String ROUTING_KEY1 = "email.#";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.102.192.55");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //定义一个匿名额队列
        String queueName = channel.queueDeclare(QUEUE_NAME, true, false, false, null).getQueue();
        //将队列和交换机绑定
        channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY1);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
