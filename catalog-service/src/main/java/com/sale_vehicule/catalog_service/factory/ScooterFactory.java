package com.sale_vehicule.catalog_service.factory;

import com.sale_vehicule.catalog_service.model.Vehicle;
import com.sale_vehicule.catalog_service.model.Scooter;

public class ScooterFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(String name, double price) {
        return new Scooter(name, price);
    }
}
