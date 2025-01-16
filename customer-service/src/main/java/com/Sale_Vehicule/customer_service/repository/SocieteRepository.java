package com.Sale_Vehicule.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sale_Vehicule.customer_service.modele.Societe;

public interface SocieteRepository extends JpaRepository<Societe, String>{
    
}
