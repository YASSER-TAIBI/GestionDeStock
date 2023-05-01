package com.yazzer.gestiondestock.validator;

import com.yazzer.gestiondestock.dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {


    public static List<String> validate(LigneVenteDto ligneVenteDto ) {
        List<String> errors = new ArrayList<>();

        if(ligneVenteDto == null){
            errors.add("Veuillez selectionner une vente");
            errors.add("Veuillez renseigner la quantite");
            errors.add("Veuillez renseigner le prix unitaire");
            return errors;
        }

        if (ligneVenteDto.getVente() == null) {
            errors.add("Veuillez selectionner une vente");
        }
        if (ligneVenteDto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantite");
        }
        if (ligneVenteDto.getPrixUnitaire() == null) {
            errors.add("Veuillez renseigner le prix unitaire");
        }
        return errors;
    }
}
