package com.Sale_Vehicule.document_service.modele;

import java.util.UUID;

public class DocumentPdf implements Document {
    private String documentPath = " "; //chemin d'acces du dossier contenant la liasse de documents

    public void setDocumentPath(String documentPath){
        this.documentPath = documentPath;
    }

    public String getDocumentPath(){
        return this.documentPath;
    }


     // Utilisation du singleton(DocumentTemplate) pour la creation de la liasse de document vierge
    DocumentTemplate documentTemplate = DocumentTemplate.getInstance();

    //Appelle de l'objet de la classe adapte (PdfGenerator)
    PdfGenerator pdfAdapte = new PdfGenerator();

    public void createRegistrationRequest(String name, String address, String brand, String model, String serialNumber, String purchaseDate) {
        String htmlContent = documentTemplate.loadRegistrationRequest()
                .replace("{name}", name)
                .replace("{address}", address)
                .replace("{brand}", brand)
                .replace("{model}", model)
                .replace("{serialNumber}", serialNumber)
                .replace("{purchaseDate}", purchaseDate);

        String uniqueId = UUID.randomUUID().toString();
        String registrationRequestPath = this.documentPath+"/registration_request_" + uniqueId + ".pdf";

        //generation du fichier pdf par utilisation d'un objet de la classe adapte(PdfGenerator)
        pdfAdapte.generatePDF(htmlContent, registrationRequestPath);
       
    }

    public void createTransferCertificate(String seller, String buyer, String brand, String model, String serialNumber, String date) {
        String htmlContent = documentTemplate.loadTransferCertificate()
                .replace("{seller}", seller)
                .replace("{buyer}", buyer)
                .replace("{brand}", brand)
                .replace("{model}", model)
                .replace("{serialNumber}", serialNumber)
                .replace("{date}", date);

        String uniqueId = UUID.randomUUID().toString();
        String transferCertificatePath = this.documentPath+"/transfer_certificate_" + uniqueId + ".pdf";
        
        //generation du fichier pdf par utilisation d'un objet de la classe adapte(PdfGenerator)
        pdfAdapte.generatePDF(htmlContent, transferCertificatePath);

        
    }

    public void createOrderForm(String clientName, String address, String brand, String model, String price, String orderDate) {
        String htmlContent = documentTemplate.loadOrderForm()
                .replace("{clientName}", clientName)
                .replace("{address}", address)
                .replace("{brand}", brand)
                .replace("{model}", model)
                .replace("{price}", price)
                .replace("{orderDate}", orderDate);
                
        String uniqueId = UUID.randomUUID().toString();
        String orderFormPath = this.documentPath+"/order_form_" + uniqueId + ".pdf";
        
        //generation du fichier pdf par utilisation d'un objet de la classe adapte(PdfGenerator)
        pdfAdapte.generatePDF(htmlContent, orderFormPath);

        
    }
}
