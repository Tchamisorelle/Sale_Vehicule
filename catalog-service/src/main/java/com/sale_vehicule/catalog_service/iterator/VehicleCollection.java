package com.sale_vehicule.catalog_service.iterator;

import com.sale_vehicule.catalog_service.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleCollection {

    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    public VehicleIterator iterator() {
        return new VehicleIteratorImpl(vehicles);
    }

    // Classe interne pour l'implémentation de l'iterator
    private static class VehicleIteratorImpl implements VehicleIterator {
        private List<Vehicle> vehicles;
        private int position = 0;

        public VehicleIteratorImpl(List<Vehicle> vehicles) {
            this.vehicles = vehicles;
        }

        @Override
        public boolean hasNext() {
            return position < vehicles.size();
        }

        @Override
        public Vehicle next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more vehicles.");
            }
            return vehicles.get(position++);
        }
    }
}
