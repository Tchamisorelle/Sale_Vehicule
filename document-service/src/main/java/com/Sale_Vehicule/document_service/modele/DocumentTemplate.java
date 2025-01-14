package com.Sale_Vehicule.document_service.modele;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


//Classe Singleton pour la creation de la liasse vierge de documents (leurs templates)
public final class DocumentTemplate {

    private static DocumentTemplate instance = null;

    private DocumentTemplate(){
        super();
    }

    public static DocumentTemplate getInstance(){
        if(instance == null){
            instance = new DocumentTemplate();
        }
        return instance;
    }

    // chargement du document(fichier) vierge pour la demande d'immatriculation
    public String loadRegistrationRequest() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/templates/registrationRequest.html")));
        } catch (IOException e) {
            e.printStackTrace();
            return ""; // Retourne une chaîne vide en cas d'erreur
        }
    }
    
    //chargement de document(fichier) vierge pour le transfer de cession
    public String loadTransferCertificate() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/templates/transferCertificate.html")));
        } catch (IOException e) {
            e.printStackTrace();
            return ""; // Retourne une chaîne vide en cas d'erreur
        }
    }

    //chargement de document(fichier) vierge pour le bon de commande
    public String loadOrderForm() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/templates/orderForm.html")));
        } catch (IOException e) {
            e.printStackTrace();
            return ""; // Retourne une chaîne vide en cas d'erreur
        }
    }
}
