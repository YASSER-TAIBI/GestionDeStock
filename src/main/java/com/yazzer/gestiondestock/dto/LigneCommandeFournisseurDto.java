package com.yazzer.gestiondestock.dto;

import com.yazzer.gestiondestock.model.LigneCommandeClient;
import com.yazzer.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto {

    private Integer id;

    private ArticleDto article;

    private CommandeFournisseurDto commandeFournisseur ;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;


    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (ligneCommandeFournisseur == null) {
            return null;
            // TODO throw an exception
        }

        return LigneCommandeFournisseurDto.builder()
                .id (ligneCommandeFournisseur.getId())
                .article (ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .commandeFournisseur (CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
                .quantite (ligneCommandeFournisseur.getQuantite())
                .prixUnitaire (ligneCommandeFournisseur.getPrixUnitaire())
                .build();
    }
    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        if (ligneCommandeFournisseurDto == null) {
            return null;
            // TODO throw an exception
        }

        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
        ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()));
        ligneCommandeFournisseur.setCommandeFournisseur(CommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto.getCommandeFournisseur()));
        ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
        ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());
        return ligneCommandeFournisseur;
    }
}
