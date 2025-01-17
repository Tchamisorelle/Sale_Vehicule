package com.Sale_Vehicule.customer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.Sale_Vehicule.customer_service.modele.Customer;
import com.Sale_Vehicule.customer_service.modele.Filiale;
import com.Sale_Vehicule.customer_service.modele.Particulier;
import com.Sale_Vehicule.customer_service.modele.Societe;
import com.Sale_Vehicule.customer_service.repository.CustomerRepository;
import com.Sale_Vehicule.customer_service.repository.ParticulierRepository;
import com.Sale_Vehicule.customer_service.repository.SocieteRepository;


import com.Sale_Vehicule.customer_service.dto.LoginRequest;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    @Autowired
    private SocieteRepository societeRepository;

    @Autowired
    private RestTemplate restTemplate;

     @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ParticulierRepository particulierRepository;

   
    public void saveSociete(Societe societe) {
        if (societe.getFiliales() == null) {
        societe.setFiliales(new ArrayList<>());
    }
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
    
    public String login(String email, String password) {
        String url = "http://localhost:8079/SERVICE-AUTHENTIFICATION/auth/login";
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        
        try {
            return restTemplate.postForObject(url, loginRequest, String.class);
        } catch (Exception e) {
            return "Echec : " + e.getMessage();
        }
    }

    public Particulier findParticulier(String numero){
        return particulierRepository.findByTel(numero).orElseThrow();
    }

    public Societe findSociete(String numero){
        return societeRepository.findByTel(numero).orElseThrow();
         
    }

    public List<Particulier> getAllParticuliers() {
        return particulierRepository.findAll();
    }

    public List<Societe> getAllSocietesWithFiliales() {
        return societeRepository.findAll();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll(); // Récupère tous les clients, y compris Particulier, Societe et Filiale
    }
}


