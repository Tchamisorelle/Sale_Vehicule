package com.sale_vehicule.catalog_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sale_vehicule.catalog_service.decorator.AccessoryDecorator;
import com.sale_vehicule.catalog_service.decorator.AnimationDecorator;
import com.sale_vehicule.catalog_service.factory.AutomobileFactory;
import com.sale_vehicule.catalog_service.factory.ScooterFactory;
import com.sale_vehicule.catalog_service.factory.VehicleFactory;
import com.sale_vehicule.catalog_service.iterator.VehicleCollection;
import com.sale_vehicule.catalog_service.iterator.VehicleIterator;
import com.sale_vehicule.catalog_service.messaging.CatalogEventPublisher;
import com.sale_vehicule.catalog_service.model.Vehicle;
import com.sale_vehicule.catalog_service.observer.EmailNotificationObserver;
import com.sale_vehicule.catalog_service.observer.Subject;
import com.sale_vehicule.catalog_service.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CatalogService {

    private static final Logger logger = LoggerFactory.getLogger(CatalogService.class);

    private final VehicleRepository vehicleRepository;
    private final Subject subject;
    private final CatalogEventPublisher eventPublisher;

    public CatalogService(VehicleRepository vehicleRepository, CatalogEventPublisher eventPublisher) {
        this.vehicleRepository = vehicleRepository;
        this.eventPublisher = eventPublisher;
        this.subject = new Subject();

        // Ajout d'un observateur d'exemple
        this.subject.addObserver(new EmailNotificationObserver("msteach1547@gmail.com"));
        logger.info("Observateur ajoute : msteach1547@gmail.com");
    }

    // Récupérer tous les véhicules
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Pattern Iterator
    public VehicleIterator getVehicleIterator() {
        VehicleCollection collection = new VehicleCollection();
        vehicleRepository.findAll().forEach(collection::addVehicle);
        return collection.iterator();
    }

    // Récupérer un véhicule par ID
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    // Créer un véhicule dynamiquement selon son type et publier l'événement
    public Vehicle createVehicle(String type, String name, double price) {
        VehicleFactory factory;
        if (type.equalsIgnoreCase("automobile")) {
            factory = new AutomobileFactory();
        } else if (type.equalsIgnoreCase("scooter")) {
            factory = new ScooterFactory();
        } else {
            throw new IllegalArgumentException("Type de véhicule inconnu : " + type);
        }
        Vehicle vehicle = factory.createVehicle(name, price);
        vehicleRepository.save(vehicle);

        // Publier l'événement vehicle.created
        eventPublisher.publishEvent("vehicle.created", vehicle);
        logger.info("Événement publié : vehicle.created -> " + vehicle);

        return vehicle;
    }

    // Supprimer un véhicule et publier l'événement
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);

        // Publier l'événement vehicle.deleted
        eventPublisher.publishEvent("vehicle.deleted", "Véhicule supprimé avec l'ID : " + id);
        logger.info("Événement publié : vehicle.deleted -> ID : " + id);
    }

    // Méthode pour appliquer un décorateur et publier l'événement
    public Vehicle addAnimation(Vehicle vehicle) {
        Vehicle decoratedVehicle = new AnimationDecorator(vehicle);
        vehicle.setName(decoratedVehicle.getName());
        vehicle.setPrice(decoratedVehicle.getPrice());
        vehicleRepository.save(vehicle);

        // Publier l'événement vehicle.updated
        eventPublisher.publishEvent("vehicle.updated", vehicle);
        logger.info("Événement publié : vehicle.updated -> " + vehicle);

        return vehicle;
    }

    public Vehicle addAccessory(Vehicle vehicle) {
        Vehicle decoratedVehicle = new AccessoryDecorator(vehicle);
        vehicle.setName(decoratedVehicle.getName());
        vehicle.setPrice(decoratedVehicle.getPrice());
        vehicleRepository.save(vehicle);

        // Publier l'événement vehicle.updated
        eventPublisher.publishEvent("vehicle.updated", vehicle);
        logger.info("Événement publié : vehicle.updated -> " + vehicle);

        return vehicle;
    }
}
