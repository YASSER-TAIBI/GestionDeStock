package com.yazzer.gestiondestock.validator;

import com.yazzer.gestiondestock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto commandeClientDto) {
        List<String> errors = new ArrayList<>();

        if(commandeClientDto == null){
            errors.add("Veuillez renseigner le code du commande client");
            errors.add("Veuillez renseigner la date du commande client");
            errors.add("Veuillez selectionner un client");
            return errors;
        }

        if (!StringUtils.hasLength(commandeClientDto.getCode())) {
            errors.add("Veuillez renseigner le code du commande client");
        }
        if (commandeClientDto.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date du commande client");
        }
        if (commandeClientDto.getClient() == null) {
            errors.add("Veuillez selectionner un client");
        }
        return errors;
    }
}
