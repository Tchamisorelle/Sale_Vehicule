package com.Sale_Vehicule.customer_service.modele;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("SOCIETE")
public class Societe extends Customer {

    private String name;
    private String adresse;
    private String email;
    private String tel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "societe_id")
    private List<Customer> filiales = new ArrayList<>();

    
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