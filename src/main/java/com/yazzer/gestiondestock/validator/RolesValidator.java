package com.yazzer.gestiondestock.validator;

import com.yazzer.gestiondestock.dto.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {

    public static List<String> validate(RolesDto rolesDto) {
        List<String> errors = new ArrayList<>();

        if(rolesDto == null){
            errors.add("Veuillez renseigner le Role name d'utilisateur");
            errors.add("Veuillez selectionner un utilisateur");
            return errors;
        }

        if (!StringUtils.hasLength(rolesDto.getRoleName())) {
            errors.add("Veuillez renseigner le Role name d'utilisateur");
        }
        if (rolesDto.getUtilisateur() == null) {
            errors.add("Veuillez selectionner un utilisateur");
        }
        return errors;
    }
}
