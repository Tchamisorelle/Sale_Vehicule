package com.Sale_Vehicule.customer_service.modele;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.PersistenceCreator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@DiscriminatorValue("SOCIETE")
public class Societe extends Customer {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "societe_id")
    private List<Customer> filiales = new ArrayList<>();

    @Id
    protected String id;
    public Societe(String name, String adresse, String email, String tel, String password) {
        super(name, adresse, email, tel, password);  // Appel au constructeur parent
        this.id = "SOCIETE-" + System.currentTimeMillis();
        
    }
    
    public void addFiliale(Customer filiale) {
        filiales.add(filiale);
        filiale.setSociete(this);
    }

    public void removeFiliale(Customer filiale) {
        filiales.remove(filiale);
        filiale.setSociete(null);
    }

    
    public void showDetails() {
        System.out.println("Société: " + name);
        for (Customer filiale : filiales) {
            filiale.showDetails();
        }
    }

    @Override
    protected void setSociete(Societe societe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSociete'");
    }

    

   
}