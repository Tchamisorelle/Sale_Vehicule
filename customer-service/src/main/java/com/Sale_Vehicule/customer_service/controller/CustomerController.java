package com.Sale_Vehicule.customer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sale_Vehicule.customer_service.modele.Filiale;
import com.Sale_Vehicule.customer_service.modele.Particulier;
import com.Sale_Vehicule.customer_service.modele.Societe;
import com.Sale_Vehicule.customer_service.service.CustomerService;

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
}