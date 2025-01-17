package com.Sale_Vehicule.customer_service.modele;

import org.springframework.data.annotation.PersistenceCreator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("PARTICULIER")
public class Particulier extends Customer {
    
    
    @Id
    protected String id;

    public Particulier(String name, String adresse, String email, String tel, String password) {
        super(name, adresse, email, tel, password);  // Appel au constructeur parent
        this.id = "PARTICULIER-" + System.currentTimeMillis();
    }

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
