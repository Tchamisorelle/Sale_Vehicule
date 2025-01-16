package com.sale_vehicule.order_service.config;

import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sale_vehicule.order_service.model.Vehicle;

@Configuration
public class RabbitConfig {

    // ✅ Définir l'exchange pour Order-Service
    @Bean
    public FanoutExchange orderExchange() {
        return ExchangeBuilder.fanoutExchange("order.exchange").durable(true).build();
    }

    // ✅ Définir la queue pour consommer les événements du Catalog-Service
    @Bean
    public Queue catalogQueue() {
        return new Queue("catalog.queue", true);
    }

    // ✅ Lier la queue au Catalog-Service Exchange
    @Bean
    public Binding catalogBinding(@Qualifier("catalogQueue") Queue catalogQueue) {
        return BindingBuilder.bind(catalogQueue).to(new FanoutExchange("catalog.exchange"));
    }

    // ✅ Configurer le convertisseur JSON
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(new DefaultJackson2JavaTypeMapper() {{
            setIdClassMapping(Map.of(
            "com.sale_vehicule.catalog_service.model.Automobile", Vehicle.class,
            "com.sale_vehicule.catalog_service.model.Scooter", Vehicle.class
        ));
    }});
    return converter;
}


    // ✅ Configurer le RabbitTemplate avec le convertisseur JSON
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
