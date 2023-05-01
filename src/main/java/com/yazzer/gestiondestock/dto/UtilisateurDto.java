package com.yazzer.gestiondestock.dto;

import com.yazzer.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private Instant dateDeNaissance;

    private String moteDePasse;

    private AdresseDto adresse;

    private String photo;

    private EntrepriseDto entreprise;

    private List<RolesDto> roles;


    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
            // TODO throw an exception
        }

        return UtilisateurDto.builder()
                .id (utilisateur.getId())
                .nom (utilisateur.getNom())
                .prenom (utilisateur.getPrenom())
                .adresse (AdresseDto.fromEntity(utilisateur.getAdresse()))
                .moteDePasse (utilisateur.getMoteDePasse())
                .photo (utilisateur.getPhoto())
                .email (utilisateur.getEmail())
                .dateDeNaissance (utilisateur.getDateDeNaissance())
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .roles(
                        utilisateur.getRoles() != null ?
                                utilisateur.getRoles().stream()
                                        .map(RolesDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }
    public static Utilisateur toEntity (UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
            // TODO throw an exception
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMoteDePasse(utilisateurDto.getMoteDePasse());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
        return utilisateur;
    }
}
