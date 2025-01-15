package com.sale_vehicule.catalog_service.decorator;

import com.sale_vehicule.catalog_service.model.Vehicle;

public class AnimationDecorator extends VehicleDecorator {

    public AnimationDecorator(Vehicle decoratedVehicle) {
        super(decoratedVehicle);
    }

    @Override
    public String getName() {
        return super.getName() + " (avec animation 3D)";
    }

    @Override
    public Double getPrice() {
        return super.getPrice() + 500; // Ajout d'un coût pour l'animation
    }
}
