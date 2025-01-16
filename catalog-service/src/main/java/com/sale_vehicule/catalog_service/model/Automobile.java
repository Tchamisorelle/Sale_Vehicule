package com.sale_vehicule.catalog_service.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Automobile")
public class Automobile extends Vehicle {
    public Automobile(String name, double price) {
        super(name, price);
    }

    public Automobile() {
    }

    @Override
    public String toString() {
        return "Automobile{" + "id=" + getId() + ", name='" + getName() + "', price=" + getPrice() + "}";
    }
}
