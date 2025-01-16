package com.Sale_Vehicule.customer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sale_Vehicule.customer_service.modele.Filiale;
import com.Sale_Vehicule.customer_service.modele.Particulier;
import com.Sale_Vehicule.customer_service.modele.Societe;
import com.Sale_Vehicule.customer_service.repository.ParticulierRepository;
import com.Sale_Vehicule.customer_service.repository.SocieteRepository;

@Service
public class CustomerService {

    @Autowired
    private SocieteRepository societeRepository;

    @Autowired
    private ParticulierRepository particulierRepository;

   
    public void saveSociete(Societe societe) {
        societeRepository.save(societe);
    }

    public void removeSociete(String idSociete){
        societeRepository.deleteById(idSociete);
    }

    public void saveParticulier(Particulier particulier){
        particulierRepository.save(particulier);
    }

    public void removeParticulier(String idParticulier){
        particulierRepository.deleteById(idParticulier);
    }
    
    public void addFiliale(Filiale filiale, String idSociete){
        Societe societe = societeRepository.findById(idSociete).orElseThrow();
        societe.addFiliale(filiale);
        societeRepository.save(societe);
    }

    public void removeFiliale(Filiale filiale, String idSociete){
        Societe societe = societeRepository.findById(idSociete).orElseThrow();
        societe.removeFiliale(filiale);
        societeRepository.save(societe);   
    }
}
