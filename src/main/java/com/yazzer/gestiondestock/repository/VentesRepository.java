package com.yazzer.gestiondestock.repository;

import com.yazzer.gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {
}
