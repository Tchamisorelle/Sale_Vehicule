package com.sale_vehicule.catalog_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.sale_vehicule.catalog_service.iterator.VehicleIterator;
import com.sale_vehicule.catalog_service.model.Vehicle;
import com.sale_vehicule.catalog_service.service.CatalogService;

@RestController
@RequestMapping("/api/vehicles")
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return catalogService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return catalogService.getVehicleById(id);
    }

    @PostMapping("/create")
    public Vehicle createVehicle(
        @RequestParam String type,
        @RequestParam String name,
        @RequestParam double price
    ) {
        return catalogService.createVehicle(type, name, price);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        catalogService.deleteVehicle(id);
    }

    @PostMapping("/{id}/add-animation")
    public Vehicle addAnimation(@PathVariable Long id) {
        Vehicle vehicle = catalogService.getVehicleById(id);
        if (vehicle == null) {
            throw new IllegalArgumentException("Véhicule introuvable avec l'ID : " + id);
        }
        return catalogService.addAnimation(vehicle);
    }

    @PostMapping("/{id}/add-accessory")
    public Vehicle addAccessory(@PathVariable Long id) {
        Vehicle vehicle = catalogService.getVehicleById(id);
        return catalogService.addAccessory(vehicle);
    }

    @GetMapping("/iterator")
    public List<String> getVehiclesIterator() {
        VehicleIterator iterator = catalogService.getVehicleIterator();  
        List<String> vehiclesNames = new ArrayList<>();
        while (iterator.hasNext()) {
            vehiclesNames.add(iterator.next().getName());
        }   
        return vehiclesNames;
    }
}
