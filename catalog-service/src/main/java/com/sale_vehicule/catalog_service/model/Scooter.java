package com.sale_vehicule.catalog_service.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Scooter")
public class Scooter extends Vehicle {
    public Scooter(String name, double price) {
        super(name, price);
    }

    public Scooter() {
    }

    @Override
    public String toString() {
        return "Scooter{" + "id=" + getId() + ", name='" + getName() + "', price=" + getPrice() + "}";
    }
}
