package com.sale_vehicule.catalog_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitConfig {

    // Définir l'exchange
    @Bean
    public Exchange catalogExchange() {
        return ExchangeBuilder.fanoutExchange("catalog.exchange").durable(true).build();
    }

    // Définir la queue
    @Bean
    public Queue catalogQueue() {
        return new Queue("catalog.queue", true);
    }

    // Lier la queue à l'exchange
    @Bean
    public Binding binding(Queue catalogQueue, Exchange catalogExchange) {
        return BindingBuilder.bind(catalogQueue).to(catalogExchange).with("").noargs();
    }

    // Définir le convertisseur de message en JSON
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Configurer le RabbitTemplate avec le convertisseur JSON
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
