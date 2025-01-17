package com.Sale_Vehicule.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.Sale_Vehicule.customer_service.modele.Particulier;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ParticulierRepository extends JpaRepository<Particulier, String> {
    public Optional<Particulier> findByTel(String tel);
}
