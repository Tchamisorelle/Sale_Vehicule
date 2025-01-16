package com.sale_vehicule.catalog_service.factory;

import com.sale_vehicule.catalog_service.model.Vehicle;
import com.sale_vehicule.catalog_service.model.Automobile;

public class AutomobileFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(String name, double price) {
        return new Automobile(name, price);
    }
}
