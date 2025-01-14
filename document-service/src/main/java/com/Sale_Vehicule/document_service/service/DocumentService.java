package com.Sale_Vehicule.document_service.service;

import org.springframework.stereotype.Service;

import com.Sale_Vehicule.document_service.modele.Directeur;
import com.Sale_Vehicule.document_service.modele.DocumentHtml;
import com.Sale_Vehicule.document_service.modele.DocumentPdf;
import com.Sale_Vehicule.document_service.modele.MonteurDocument;
import com.Sale_Vehicule.document_service.modele.MonteurDocumentHtml;
import com.Sale_Vehicule.document_service.modele.MonteurDocumentPdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DocumentService {
    MonteurDocument monteurDocumenthtml;
    MonteurDocument monteurDocumentpdf;
    Directeur directeurhtml;
    Directeur directeurpdf;
    
    // Methode qui gere la creation de la liasse de document HTML
    public DocumentHtml createDocumentHtml(String name, String address, String brand, String model, String serialNumber, String purchaseDate, String seller, String price) throws IOException {

        monteurDocumenthtml = new MonteurDocumentHtml();
        directeurhtml = new Directeur(monteurDocumenthtml);
        return (DocumentHtml) directeurhtml.construireDocument(name, address, brand, model, serialNumber, purchaseDate, seller, price);
    }

    // Methode qui gere la creation de la liasse de document Pdf
    public DocumentPdf createDocumentPdf(String name, String address, String brand, String model, String serialNumber, String purchaseDate, String seller, String price) throws IOException {

        monteurDocumentpdf = new MonteurDocumentPdf();
        directeurpdf = new Directeur(monteurDocumentpdf);
        return (DocumentPdf) directeurpdf.construireDocument(name, address, brand, model, serialNumber, purchaseDate, seller, price);
    }

    // Methode permettant de zippé le dossier contenant la liasse de document en vue d'un telechargement
    public void zipDirectory(String sourceDirPath, String zipFilePath) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            File sourceDir = new File(sourceDirPath);
            for (File file : sourceDir.listFiles()) {
                if (file.isFile()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zos.putNextEntry(zipEntry);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) >= 0) {
                            zos.write(buffer, 0, length);
                        }
                        zos.closeEntry();
                    }
                }
            }
        }
    }
}
