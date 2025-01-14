package com.Sale_Vehicule.document_service.modele;

import java.io.IOException;

// interface connu par le client  et qui est hérité par DocumentHtml et DocumentPdf(l'adaptateur)
public interface Document {
    //methode pour la creation de la demande d'immatriculation(document)
    public abstract void createRegistrationRequest(String name, String address, String brand, String model, String serialNumber, String purchaseDate) throws IOException;

    //methode pour la creation du certificat de cession(document)
    public abstract void createTransferCertificate(String seller, String buyer, String brand, String model, String serialNumber, String date) throws IOException;

    //methade pour la cration du bon de commande(document)
    public abstract void createOrderForm(String clientName, String address, String brand, String model, String price, String orderDate) throws IOException;
    public abstract void setDocumentPath(String documentPath);
} 