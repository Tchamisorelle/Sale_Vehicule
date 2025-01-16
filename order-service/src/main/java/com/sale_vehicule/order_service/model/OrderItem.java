package com.sale_vehicule.order_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String vehicleName;

    private double price;

    public OrderItem() {
    }

    public OrderItem(String vehicleName, double price) {
        this.vehicleName = vehicleName;
        this.price = price;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
