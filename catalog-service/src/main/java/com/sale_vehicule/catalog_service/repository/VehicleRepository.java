package com.sale_vehicule.catalog_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sale_vehicule.catalog_service.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
