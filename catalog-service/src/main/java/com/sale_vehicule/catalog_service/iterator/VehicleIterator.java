package com.sale_vehicule.catalog_service.iterator;

import com.sale_vehicule.catalog_service.model.Vehicle;

public interface VehicleIterator {
    boolean hasNext();
    Vehicle next();
}
