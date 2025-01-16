package com.sale_vehicule.order_service.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // Constructeur vide
    public Order() {
    }

    // Constructeur privé pour le Builder
    private Order(Builder builder) {
        this.customerName = builder.customerName;
        this.items = builder.items;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    // ✅ Ajouter la méthode addItem()
    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    // ✅ Ajouter la méthode removeItem() (facultatif)
    public void removeItem(OrderItem item) {
        this.items.remove(item);
    }

    // Classe Builder interne
    public static class Builder {
        private String customerName;
        private List<OrderItem> items = new ArrayList<>();

        public Builder withCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder addItem(OrderItem item) {
            this.items.add(item);
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
