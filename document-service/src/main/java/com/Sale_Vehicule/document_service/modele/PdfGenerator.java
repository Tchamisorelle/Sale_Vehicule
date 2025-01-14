package com.Sale_Vehicule.document_service.modele;

import com.itextpdf.html2pdf.HtmlConverter;
import java.io.FileOutputStream;
import java.io.IOException;

// Classe Adapte qu'on doit faire correspondre à 'Document'
public class PdfGenerator {

    //methode qui gere les fichiers pdf(leur generation)
    public void generatePDF(String htmlContent, String pdfPath) {
        try (FileOutputStream outputStream = new FileOutputStream(pdfPath)) {
            HtmlConverter.convertToPdf(htmlContent, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
