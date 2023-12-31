package com.yazzer.gestiondestock.dto;

import com.yazzer.gestiondestock.model.CommandeClient;
import com.yazzer.gestiondestock.model.EtatCommande;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private ClientDto client;

    private Integer idEntreprise;

    private List<LigneCommandeClientDto> ligneCommandeClients;


    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
            // TODO throw an exception
        }

        return CommandeClientDto.builder()
                .id (commandeClient.getId())
                .code (commandeClient.getCode())
                .idEntreprise(commandeClient.getIdEntreprise())
                .etatCommande(commandeClient.getEtatCommande())
                .dateCommande (commandeClient.getDateCommande())
                .client (ClientDto.fromEntity(commandeClient.getClient()))
                .build();
    }
    public static CommandeClient toEntity (CommandeClientDto commandeClientDto) {
        if (commandeClientDto == null) {
            return null;
            // TODO throw an exception
        }

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setEtatCommande(commandeClientDto.getEtatCommande());
        commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());

        return commandeClient;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
      }
}
