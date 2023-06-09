package com.yazzer.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yazzer.gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    @JsonIgnore
    private List<CommandeClientDto> commandeClients;


    public static ClientDto fromEntity(Client client) {
        if (client == null) {
            return null;
            // TODO throw an exception
        }

        return ClientDto.builder()
                .id (client.getId())
                .nom (client.getNom())
                .prenom (client.getPrenom())
                .adresse (AdresseDto.fromEntity(client.getAdresse()))
                .photo (client.getPhoto())
                .idEntreprise(client.getIdEntreprise())
                .mail (client.getMail())
                .numTel (client.getNumTel())
                .build();
    }
    public static Client toEntity (ClientDto clientDto) {
        if (clientDto == null) {
            return null;
            // TODO throw an exception
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAdresse(AdresseDto.toEntity(clientDto.getAdresse()));
        client.setPhoto(clientDto.getPhoto());
        client.setIdEntreprise(clientDto.getIdEntreprise());
        client.setMail(clientDto.getMail());
        client.setNumTel(clientDto.getNumTel());
        return client;
    }
}
