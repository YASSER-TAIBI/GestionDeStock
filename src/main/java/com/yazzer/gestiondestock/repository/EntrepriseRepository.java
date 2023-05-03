package com.yazzer.gestiondestock.repository;

import com.yazzer.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
}
