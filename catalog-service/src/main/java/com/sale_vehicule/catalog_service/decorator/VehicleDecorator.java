package com.sale_vehicule.catalog_service.decorator;

import com.sale_vehicule.catalog_service.model.Vehicle;

public abstract class VehicleDecorator extends Vehicle {
    protected Vehicle decoratedVehicle;

    public VehicleDecorator(Vehicle decoratedVehicle) {
        super(decoratedVehicle.getName(), decoratedVehicle.getPrice());
        this.decoratedVehicle = decoratedVehicle;
    }

    @Override
    public String getName() {
        return decoratedVehicle.getName();
    }

    @Override
    public Double getPrice() {
        return decoratedVehicle.getPrice();
    }
}
