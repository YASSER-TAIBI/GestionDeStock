package com.yazzer.gestiondestock.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yazzer.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private Integer idEntreprise;

    @JsonIgnore
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;


    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (commandeFournisseur == null) {
            return null;
            // TODO throw an exception
        }

        return CommandeFournisseurDto.builder()
                .id (commandeFournisseur.getId())
                .code (commandeFournisseur.getCode())
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .dateCommande (commandeFournisseur.getDateCommande())
                .fournisseur (FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .build();
    }
    public static CommandeFournisseur toEntity (CommandeFournisseurDto commandeFournisseurDto) {
        if (commandeFournisseurDto == null) {
            return null;
            // TODO throw an exception
        }

        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurDto.getId());
        commandeFournisseur.setCode(commandeFournisseurDto.getCode());
        commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
        commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());
        commandeFournisseur.setFournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseur()));

        return commandeFournisseur;
    }
}
