package com.sale_vehicule.order_service.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final AmqpTemplate amqpTemplate;

    @Value("${order.exchange}")
    private String exchange;

    public OrderEventPublisher(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishEvent(String routingKey, Object message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println("Événement publié : " + routingKey + " -> " + message);
    }
}
