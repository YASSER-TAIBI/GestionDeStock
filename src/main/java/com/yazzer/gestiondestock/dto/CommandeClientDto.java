package com.yazzer.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yazzer.gestiondestock.model.CommandeClient;
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

    private ClientDto client;

    @JsonIgnore
    private List<LigneCommandeClientDto> ligneCommandeClients;


    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
            // TODO throw an exception
        }

        return CommandeClientDto.builder()
                .id (commandeClient.getId())
                .code (commandeClient.getCode())
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
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));

        return commandeClient;
    }
}
