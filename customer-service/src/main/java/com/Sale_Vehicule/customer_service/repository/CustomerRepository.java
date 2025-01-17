package com.Sale_Vehicule.customer_service.repository;

import com.Sale_Vehicule.customer_service.modele.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    // Cette méthode récupère tous les clients (incluant Particulier, Societe et Filiale)
    List<Customer> findAll();
}
