package com.yazzer.gestiondestock.repository;

import com.yazzer.gestiondestock.model.LigneCommandeClient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {

    List<LigneCommandeClient> findAllByCommandeClientId(Integer id);
}
