package com.yazzer.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yazzer.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseur;


    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
            // TODO throw an exception
        }

        return FournisseurDto.builder()
                .id (fournisseur.getId())
                .nom (fournisseur.getNom())
                .prenom (fournisseur.getPrenom())
                .adresse (AdresseDto.fromEntity(fournisseur.getAdresse()))
                .photo (fournisseur.getPhoto())
                .idEntreprise(fournisseur.getIdEntreprise())
                .mail (fournisseur.getMail())
                .numTel (fournisseur.getNumTel())
                .build();
    }
    public static Fournisseur toEntity (FournisseurDto fournisseurDto) {
        if (fournisseurDto == null) {
            return null;
            // TODO throw an exception
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());
        fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresse()));
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setMail(fournisseurDto.getMail());
        fournisseur.setNumTel(fournisseurDto.getNumTel());
        return fournisseur;
    }
}
