package com.Sale_Vehicule.customer_service.modele;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type")
public abstract class Customer {
    @Id
    private String id;

    protected abstract void showDetails();

    protected abstract void setSociete(Societe societe);
  
    
}