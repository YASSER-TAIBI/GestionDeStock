package com.yazzer.gestiondestock.dto;

import com.yazzer.gestiondestock.model.LigneCommandeClient;
import com.yazzer.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;

    private ArticleDto article;

    private CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;


    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
        if (ligneCommandeClient == null) {
            return null;
            // TODO throw an exception
        }

        return LigneCommandeClientDto.builder()
                .id (ligneCommandeClient.getId())
                .article (ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .commandeClient (CommandeClientDto.fromEntity(ligneCommandeClient.getCommandeClient()))
                .quantite (ligneCommandeClient.getQuantite())
                .prixUnitaire (ligneCommandeClient.getPrixUnitaire())
                .build();
    }
    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
        if (ligneCommandeClientDto == null) {
            return null;
            // TODO throw an exception
        }

        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(ligneCommandeClientDto.getId());
        ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
        ligneCommandeClient.setCommandeClient(CommandeClientDto.toEntity(ligneCommandeClientDto.getCommandeClient()));
        ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
        return ligneCommandeClient;
    }
}
