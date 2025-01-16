package com.sale_vehicule.order_service.controller;

import com.sale_vehicule.order_service.model.Order;
import com.sale_vehicule.order_service.model.OrderItem;
import com.sale_vehicule.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Endpoint pour créer une commande
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestParam String customerName, @RequestBody List<OrderItem> items) {
        Order order = orderService.createOrder(customerName, items);
        return ResponseEntity.ok(order);
    }
    

    // Endpoint pour récupérer toutes les commandes
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Endpoint pour récupérer une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // Endpoint pour ajouter un item à une commande
    @PostMapping("/{orderId}/add-item")
    public ResponseEntity<Order> addItemToOrder(@PathVariable Long orderId, @RequestBody OrderItem item) {
        Order updatedOrder = orderService.addItemToOrder(orderId, item);
        return ResponseEntity.ok(updatedOrder);
    }

    // Endpoint pour supprimer une commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
