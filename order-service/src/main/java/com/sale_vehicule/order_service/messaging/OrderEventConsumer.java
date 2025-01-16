package com.sale_vehicule.order_service.messaging;

import com.sale_vehicule.order_service.model.OrderItem;
import com.sale_vehicule.order_service.model.Vehicle;
import com.sale_vehicule.order_service.repository.OrderItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderEventConsumer.class);
    private final OrderItemRepository orderItemRepository;

    public OrderEventConsumer(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // ✅ Consommer les événements vehicle.created
    @RabbitListener(queues = "catalog.queue")
    public void handleVehicleCreatedEvent(OrderItem orderItem) {
        logger.info("Événement consommé : vehicle.created -> " + orderItem);
        orderItemRepository.save(orderItem);
    }

    @RabbitListener(queues = "catalog.queue")
    public void handleVehicleDeletedEvent(Vehicle vehicle) {
        logger.info("Événement consommé : vehicle.deleted -> " + vehicle);
        orderItemRepository.deleteById(vehicle.getId());
    }
    
    @RabbitListener(queues = "catalog.queue")
    public void handleVehicleUpdatedEvent(Vehicle vehicle) {
        logger.info("Événement consommé : vehicle.updated -> " + vehicle);

        // Rechercher l'OrderItem correspondant par ID
        OrderItem orderItem = orderItemRepository.findById(vehicle.getId())
            .orElseThrow(() -> new IllegalArgumentException("OrderItem introuvable avec l'ID : " + vehicle.getId()));

        // Mettre à jour les propriétés
        orderItem.setVehicleName(vehicle.getName());
        orderItem.setPrice(vehicle.getPrice());

        // Sauvegarder les modifications
        orderItemRepository.save(orderItem);

        logger.info("OrderItem mis à jour : " + orderItem);
}


}
