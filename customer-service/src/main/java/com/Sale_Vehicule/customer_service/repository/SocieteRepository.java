package com.Sale_Vehicule.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Optional;
import java.util.List;
import com.Sale_Vehicule.customer_service.modele.Societe;

@RepositoryRestResource
public interface SocieteRepository extends JpaRepository<Societe, String>{
        public Optional<Societe> findByTel(String tel);
        List<Societe> findAll();
}
