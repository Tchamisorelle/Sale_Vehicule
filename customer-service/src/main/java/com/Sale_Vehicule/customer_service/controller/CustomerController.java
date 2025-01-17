package com.Sale_Vehicule.customer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sale_Vehicule.customer_service.modele.Customer;
import com.Sale_Vehicule.customer_service.modele.Filiale;
import com.Sale_Vehicule.customer_service.modele.Particulier;
import com.Sale_Vehicule.customer_service.modele.Societe;
import com.Sale_Vehicule.customer_service.repository.SocieteRepository;
import com.Sale_Vehicule.customer_service.service.CustomerService;
import java.util.List;
import org.springframework.stereotype.Service;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;  // Utilisation de la classe de service
    

    @PostMapping("/societes")
    public void createSociete(@RequestBody Societe societe) {
        
        customerService.saveSociete(societe);

    }

    @DeleteMapping("/societes/{id}")
    public void deleteSociete(@PathVariable String id) {
        customerService.removeSociete(id);
    }

    @PostMapping("/particuliers")
    public void createParticulier(@RequestBody Particulier particulier) {
        customerService.saveParticulier(particulier);
    }

    @DeleteMapping("/particuliers/{id}")
    public void deleteParticulier(@PathVariable String id) {
        customerService.removeParticulier(id);
    }

    @PostMapping("/societes/{idSociete}/filiales")
    public void addFiliale(@PathVariable String idSociete, @RequestBody Filiale filiale) {
        customerService.addFiliale(filiale, idSociete);
    }

    @DeleteMapping("/societes/{idSociete}/filiales")
    public void removeFiliale(@PathVariable String idSociete, @RequestBody Filiale filiale) {
        customerService.removeFiliale(filiale, idSociete);  
    }
    @GetMapping("/particuliers")
    public List<Particulier> getAllParticuliers() {
        return customerService.getAllParticuliers();
    }

    // Endpoint pour récupérer toutes les sociétés avec leurs filiales
    @GetMapping("/get-societes")
    public List<Societe> getAllSocietesWithFiliales() {
        return customerService.getAllSocietesWithFiliales();
    }

    @GetMapping("/societe/{tel}")
    public Societe getSocieteByTel(@PathVariable String tel) {
        return customerService.findSociete(tel);
    }

    @GetMapping("/particulier/{tel}")
    public Particulier getParticulierByTel(@PathVariable String tel) {
        return customerService.findParticulier(tel);
    }


    @GetMapping("/get")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers(); // Récupère tous les clients
    }

}