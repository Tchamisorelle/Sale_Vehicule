package com.sale_vehicule.catalog_service.decorator;

import com.sale_vehicule.catalog_service.model.Vehicle;

public class AccessoryDecorator extends VehicleDecorator {

    public AccessoryDecorator(Vehicle decoratedVehicle) {
        super(decoratedVehicle);
    }

    @Override
    public String getName() {
        return super.getName() + " (avec accessoires)";
    }

    @Override
    public Double getPrice() {
        return super.getPrice() + 1000; // Ajout d'un coût pour les accessoires
    }
}
