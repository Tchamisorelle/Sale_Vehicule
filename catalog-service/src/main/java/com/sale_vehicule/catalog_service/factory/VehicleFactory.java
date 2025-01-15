package com.sale_vehicule.catalog_service.factory;

import com.sale_vehicule.catalog_service.model.Vehicle;

public interface VehicleFactory {
    Vehicle createVehicle(String name, double price);
}
