package com.Sale_Vehicule.customer_service.modele;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("PARTICULIER")
public class Particulier extends Customer {


    private String name;
    private String adresse;
    private String email;
    private String tel;

   
    public void showDetails() {
        System.out.println("Nom du Particulier: " + name);
        System.out.println("Adresse: " + adresse);
        System.out.println("Email: " + email);
        System.out.println("Telephone: " + tel);
        System.out.println("\n");
    }


    @Override
    protected void setSociete(Societe societe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSociete'");
    }
    
}
