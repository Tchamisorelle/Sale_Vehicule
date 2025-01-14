package com.Sale_Vehicule.document_service.modele;

import java.io.IOException;

public class Directeur {
    private MonteurDocument monteurAbstrait;

    public Directeur(MonteurDocument monteurDocument){
        monteurAbstrait = monteurDocument;
    }

    //Methode du Directeur pour la  construction des liasses de documents 
    public Document construireDocument(String name, String address, String brand, String model, String serialNumber, String purchaseDate, String seller, String price) throws IOException{

    
        monteurAbstrait.createNewDocument(name);
        monteurAbstrait.monterRegistrationRequest(name, address, brand, model, serialNumber, purchaseDate);
        monteurAbstrait.monterTransferCertificate(seller, name, brand, model, serialNumber, purchaseDate);
        monteurAbstrait.monterOrderForm(name, address, brand, model, price, purchaseDate);
        return monteurAbstrait.getDocument();

    }


    
}

