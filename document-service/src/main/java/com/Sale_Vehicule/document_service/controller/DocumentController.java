package com.Sale_Vehicule.document_service.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Sale_Vehicule.document_service.modele.DocumentHtml;
import com.Sale_Vehicule.document_service.modele.DocumentPdf;
import com.Sale_Vehicule.document_service.service.DocumentService;



@RestController
@RequestMapping("api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // controller pour la cration et le telechargement de la liasse de document html
    @PostMapping("/createAndDownloadHtml")
    public ResponseEntity<Resource> createAndDownloadDocumentHtml(@RequestParam String name, 
                                                               @RequestParam String address, 
                                                               @RequestParam String brand, 
                                                               @RequestParam String model, 
                                                               @RequestParam String serialNumber, 
                                                               @RequestParam String purchaseDate, 
                                                               @RequestParam String seller, 
                                                               @RequestParam String price) {
        try {
            // Construire le document
            DocumentHtml documenthtml = documentService.createDocumentHtml(name, address, brand, model, serialNumber, purchaseDate, seller, price);
            String documentPath = documenthtml.getDocumentPath(); // Récupérer le chemin du dossier
            
            // Créer le fichier zip à partir du dossier
            String zipFilePath = documentPath + ".zip"; // Chemin du fichier zip
            documentService.zipDirectory(documentPath, zipFilePath); // Zipper le dossier

            // Préparer le fichier zip pour le téléchargement
            Resource resource = new FileSystemResource(zipFilePath);
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Retourner le fichier zip pour le téléchargement
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/zip")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // controller pour la cration et le telechargement de la liasse de document html
    @PostMapping("/createAndDownloadPdf")
    public ResponseEntity<Resource> createAndDownloadDocumentPdf(@RequestParam String name, 
                                                               @RequestParam String address, 
                                                               @RequestParam String brand, 
                                                               @RequestParam String model, 
                                                               @RequestParam String serialNumber, 
                                                               @RequestParam String purchaseDate, 
                                                               @RequestParam String seller, 
                                                               @RequestParam String price) {
        try {
            // Construire le document
            DocumentPdf documentpdf = documentService.createDocumentPdf(name, address, brand, model, serialNumber, purchaseDate, seller, price);
            String documentPath = documentpdf.getDocumentPath(); // Récupérer le chemin du dossier
            
            // Créer le fichier zip à partir du dossier
            String zipFilePath = documentPath + ".zip"; // Chemin du fichier zip
            documentService.zipDirectory(documentPath, zipFilePath); // Zipper le dossier

            // Préparer le fichier zip pour le téléchargement
            Resource resource = new FileSystemResource(zipFilePath);
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Retourner le fichier zip pour le téléchargement
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/zip")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
}
