package com.Sale_Vehicule.document_service.modele;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MonteurDocumentPdf extends MonteurDocument{

    public void createNewDocument(String name){

        // Générer un identifiant unique pour le dossier qui stockera la liasse de documents
        String uniqueId = UUID.randomUUID().toString();
        String folderName = name + "_infoCommandePdf_" + uniqueId;
        String folderPath = "src/main/resources/uploads/" + folderName;

        // Créer le dossier qui stockera la liasse de documents
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Crée le dossier
        }

        //initialisation du document
        document = new DocumentPdf();

        //sauvegarde du chemin d'accès du dossier crée
        document.setDocumentPath(folderPath);
    }
    
    public void monterRegistrationRequest(String name, String address, String brand, String model, String serialNumber, String purchaseDate) throws IOException{
        document.createRegistrationRequest(name, address, brand, model, serialNumber, purchaseDate);
    }

    public void monterTransferCertificate(String seller, String buyer, String brand, String model, String serialNumber, String date) throws IOException{
        document.createTransferCertificate(seller, buyer, brand, model, serialNumber, date);
    }

    public void monterOrderForm(String clientName, String address, String brand, String model, String price, String orderDate) throws IOException{
        document.createOrderForm(clientName, address, brand, model, price, orderDate);
    }

    
}
