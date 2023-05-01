package com.yazzer.gestiondestock.validator;

import com.yazzer.gestiondestock.dto.LigneCommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {


    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto ) {
        List<String> errors = new ArrayList<>();

        if(ligneCommandeClientDto == null){
            errors.add("Veuillez selectionner un article ");
            errors.add("Veuillez selectionner une commande client");
            errors.add("Veuillez renseigner la quantite");
            errors.add("Veuillez renseigner le prix unitaire");
            return errors;
        }

        if (ligneCommandeClientDto.getArticle() == null) {
            errors.add("Veuillez selectionner un article ");
        }
        if (ligneCommandeClientDto.getCommandeClient() == null) {
            errors.add("Veuillez selectionner une commande client");
        }
        if (ligneCommandeClientDto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantite");
        }
        if (ligneCommandeClientDto.getPrixUnitaire() == null) {
            errors.add("Veuillez renseigner le prix unitaire");
        }
        return errors;
    }
}
