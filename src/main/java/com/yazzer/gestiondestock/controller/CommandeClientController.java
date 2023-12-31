package com.yazzer.gestiondestock.controller;

import com.yazzer.gestiondestock.controller.api.CommandeClientApi;
import com.yazzer.gestiondestock.dto.CommandeClientDto;
import com.yazzer.gestiondestock.model.EtatCommande;
import com.yazzer.gestiondestock.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        return ResponseEntity.ok(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer idCommandeClient) {
        return ResponseEntity.ok(commandeClientService.findById(idCommandeClient));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findALL());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
            BigDecimal quantite) {
        return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateClient(Integer idCommande, Integer idClient) {
        return ResponseEntity.ok(commandeClientService.updateClient(idCommande, idClient));
    }
}
