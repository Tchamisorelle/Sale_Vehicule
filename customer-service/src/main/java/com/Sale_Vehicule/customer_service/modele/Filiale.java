package com.Sale_Vehicule.customer_service.modele;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("FILIALE")
public class Filiale extends Customer {

    private String name;
    private String adresse;
    private String email;
    private String tel;

    @ManyToOne
    @JoinColumn(name = "societe_id", nullable = false)
    private Societe societe;

    
    public void showDetails() {
        System.out.println("Filiale: " + name);
        System.out.println("Adresse: " + adresse);
        System.out.println("Email: " + email);
        System.out.println("Telephone: " + tel);
        System.out.println("\n");
    }    

    
}
