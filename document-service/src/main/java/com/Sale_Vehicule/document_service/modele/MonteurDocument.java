package com.Sale_Vehicule.document_service.modele;

import java.io.IOException;

public abstract class MonteurDocument {
   protected Document document;

    public abstract void createNewDocument(String name);
    public abstract void monterRegistrationRequest(String name, String address, String brand, String model, String serialNumber, String purchaseDate)throws IOException;
    public abstract void monterTransferCertificate(String seller, String buyer, String brand, String model, String serialNumber, String date)throws IOException;
    public abstract void monterOrderForm(String clientName, String address, String brand, String model, String price, String orderDate)throws IOException;
    public Document getDocument(){
        return document;
    }
    
    
}