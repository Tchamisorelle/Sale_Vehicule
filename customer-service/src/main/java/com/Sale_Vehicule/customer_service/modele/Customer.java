package com.Sale_Vehicule.customer_service.modele;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type")
public abstract class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Cette ligne permet à JPA de générer l'ID automatiquement
    protected Long id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String adresse;

    @Column(nullable = false)
    protected String email;

    @Column(nullable = false)
    protected String tel;

    @Column(nullable = false)
    protected String password;

    protected Role role = Role.client;

    public Customer(String name, String adresse, String email, String tel, String password) {
        this.name = name;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
        this.password = password;
    }

    protected abstract void showDetails();
    protected abstract void setSociete(Societe societe);
}
