package com.yazzer.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yazzer.gestiondestock.model.Roles;
import com.yazzer.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {

    private Integer id;

    private String roleName;

    @JsonIgnore
    private UtilisateurDto utilisateur;


    public static RolesDto fromEntity(Roles roles) {
        if (roles == null) {
            return null;
            // TODO throw an exception
        }

        return RolesDto.builder()
                .id (roles.getId())
                .roleName (roles.getRoleName())
                .build();
    }
    public static Roles toEntity (RolesDto rolesDto) {
        if (rolesDto == null) {
            return null;
            // TODO throw an exception
        }

        Roles roles = new Roles();
        roles.setId(rolesDto.getId());
        roles.setRoleName(rolesDto.getRoleName());
        roles.setUtilisateur(UtilisateurDto.toEntity(rolesDto.getUtilisateur()));
        return roles;
    }
}
