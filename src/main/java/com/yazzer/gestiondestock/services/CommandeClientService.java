package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.CommandeClientDto;
import com.yazzer.gestiondestock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {


    CommandeClientDto save (CommandeClientDto dto);

    CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeClientDto updateClient(Integer idCommande, Integer idClient);

    CommandeClientDto findById(Integer id);
    
    CommandeClientDto findByCode (String code);
    
    List<CommandeClientDto> findALL();
    
    void delete(Integer id);
}
