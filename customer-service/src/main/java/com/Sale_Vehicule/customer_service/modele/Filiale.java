package com.Sale_Vehicule.customer_service.modele;

import org.springframework.data.annotation.PersistenceCreator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("FILIALE")
public class Filiale extends Customer {


    @Id
    protected String id;
   
    public Filiale(String name, String adresse, String email, String tel, String password) {
        super(name, adresse, email, tel, password);  // Appel au constructeur parent
        this.id = "FILIALE-" + System.currentTimeMillis();
    }

    @ManyToOne
    @JoinColumn(name = "societe_id", nullable = true)
    private Societe societe;

    
    public void showDetails() {
        System.out.println("Nom de Filiale: " + name);
        System.out.println("Adresse: " + adresse);
        System.out.println("Email: " + email);
        System.out.println("Telephone: " + tel);
        System.out.println("\n");
    }    

    
}
