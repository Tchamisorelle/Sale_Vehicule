package com.Sale_Vehicule.document_service.modele;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;



public class DocumentHtml implements Document {
    private String documentPath = " "; //chemin d'acces du dossier contenant la liasse de documents

    public void setDocumentPath(String documentPath){
        this.documentPath = documentPath;
    }

    public String getDocumentPath(){
        return this.documentPath;
    }

    // Utilisation du singleton(DocumentTemplate) pour la creation de la liasse de document vierge
    DocumentTemplate documentTemplate = DocumentTemplate.getInstance();

    public void createRegistrationRequest(String name, String address, String brand, String model, String serialNumber, String purchaseDate) throws IOException {
    String template = documentTemplate.loadRegistrationRequest();
    String htmlContent = template.replace("{name}", name)
                                  .replace("{address}", address)
                                  .replace("{brand}", brand)
                                  .replace("{model}", model)
                                  .replace("{serialNumber}", serialNumber)
                                  .replace("{purchaseDate}", purchaseDate);

    /// Générer un identifiant unique pour le nom du fichier
        String uniqueId = UUID.randomUUID().toString();
        String registrationRequestPath = this.documentPath+"/registration_request_" + uniqueId + ".html";

    // Écrire le contenu HTML dans le fichier
    try (FileWriter fileWriter = new FileWriter(registrationRequestPath)) {
        fileWriter.write(htmlContent);
    }

    
}

    public void createTransferCertificate(String seller, String buyer, String brand, String model, String serialNumber, String date) throws IOException {
        String template = documentTemplate.loadTransferCertificate();
        String htmlContent =template.replace("{seller}", seller)
                                    .replace("{buyer}", buyer)
                                    .replace("{brand}", brand)
                                    .replace("{model}", model)
                                    .replace("{serialNumber}", serialNumber)
                                    .replace("{date}", date);

        /// Générer un identifiant unique pour le nom du fichier
        String uniqueId = UUID.randomUUID().toString();
        String transferCertificatePath = this.documentPath+"/transfer_certificate_" + uniqueId + ".html";

        // Écrire le contenu HTML dans le fichier
        try (FileWriter fileWriter = new FileWriter(transferCertificatePath)) {
            fileWriter.write(htmlContent);
        }

        
    }

    public void createOrderForm(String clientName, String address, String brand, String model, String price, String orderDate) throws IOException {
        String template = documentTemplate.loadOrderForm();
        String htmlContent =template.replace("{clientName}", clientName)
                                    .replace("{address}", address)
                                    .replace("{brand}", brand)
                                    .replace("{model}", model)
                                    .replace("{price}", price)
                                    .replace("{orderDate}", orderDate);
        
        /// Générer un identifiant unique pour le nom du fichier
        String uniqueId = UUID.randomUUID().toString();
        String orderFormPath = this.documentPath+"/order_form_" + uniqueId + ".html";

        // Écrire le contenu HTML dans le fichier
        try (FileWriter fileWriter = new FileWriter(orderFormPath)) {
            fileWriter.write(htmlContent);
        }
        
    }
}
