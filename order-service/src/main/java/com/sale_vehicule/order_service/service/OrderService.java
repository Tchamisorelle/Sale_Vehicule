package com.sale_vehicule.order_service.service;

import com.sale_vehicule.order_service.messaging.OrderEventPublisher;
import com.sale_vehicule.order_service.model.Order;
import com.sale_vehicule.order_service.model.OrderItem;
import com.sale_vehicule.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher eventPublisher;

    public OrderService(OrderRepository orderRepository, OrderEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    // Créer une commande et publier un événement
    public Order createOrder(String customerName, List<OrderItem> items) {
        Order order = new Order.Builder()
                .withCustomerName(customerName)
                .build();
        items.forEach(order::addItem);
        Order savedOrder = orderRepository.save(order);
        eventPublisher.publishEvent("order.created", savedOrder);
        return savedOrder;
    }

    // Récupérer toutes les commandes
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Récupérer une commande par ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Commande introuvable avec l'ID : " + id));
    }

    // Ajouter un item à une commande existante et publier un événement
    public Order addItemToOrder(Long orderId, OrderItem item) {
        Order order = getOrderById(orderId);
        order.addItem(item);
        Order updatedOrder = orderRepository.save(order);
        eventPublisher.publishEvent("order.updated", updatedOrder);
        return updatedOrder;
    }

    // Supprimer une commande et publier un événement
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
        eventPublisher.publishEvent("order.deleted", "Commande supprimée avec l'ID : " + id);
    }
}
