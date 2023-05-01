package com.yazzer.gestiondestock.validator;

import com.yazzer.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {


    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = new ArrayList<>();

        if(commandeFournisseurDto == null){
            errors.add("Veuillez renseigner le code du commande fournisseur");
            errors.add("Veuillez renseigner la date du commande fournisseur");
            errors.add("Veuillez selectionner un fournisseur");
            return errors;
        }

        if (!StringUtils.hasLength(commandeFournisseurDto.getCode())) {
            errors.add("Veuillez renseigner le code du commande fournisseur");
        }
        if (commandeFournisseurDto.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date du commande fournisseur");
        }
        if (commandeFournisseurDto.getFournisseur() == null) {
            errors.add("Veuillez selectionner un fournisseur");
        }
        return errors;
    }
}
