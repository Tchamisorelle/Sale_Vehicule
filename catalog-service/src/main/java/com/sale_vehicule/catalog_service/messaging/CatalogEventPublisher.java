package com.sale_vehicule.catalog_service.messaging;

import com.sale_vehicule.catalog_service.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CatalogEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(CatalogEventPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${catalog.exchange}")
    private String catalogExchange;

    public CatalogEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishEvent(String eventType, Vehicle vehicle) {
        logger.info("📢 Publishing event: {} -> {}", eventType, vehicle);
        rabbitTemplate.convertAndSend(catalogExchange, eventType, vehicle, message -> {
            message.getMessageProperties().setHeader("__TypeId__", vehicle.getClass().getName());
            return message;
        });
    }
}
