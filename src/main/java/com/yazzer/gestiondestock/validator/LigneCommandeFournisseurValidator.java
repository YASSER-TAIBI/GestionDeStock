package com.yazzer.gestiondestock.validator;

import com.yazzer.gestiondestock.dto.LigneCommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseurValidator {

    public static List<String> validate(LigneCommandeFournisseurDto ligneCommandeFournisseurDto ) {
        List<String> errors = new ArrayList<>();

        if(ligneCommandeFournisseurDto == null){
            errors.add("Veuillez selectionner un article ");
            errors.add("Veuillez selectionner une commande client");
            errors.add("Veuillez renseigner la quantite");
            errors.add("Veuillez renseigner le prix unitaire");
            return errors;
        }

        if (ligneCommandeFournisseurDto.getArticle() == null) {
            errors.add("Veuillez selectionner un article ");
        }
        if (ligneCommandeFournisseurDto.getCommandeFournisseur() == null) {
            errors.add("Veuillez selectionner une commande fournisseur");
        }
        if (ligneCommandeFournisseurDto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantite");
        }
        if (ligneCommandeFournisseurDto.getPrixUnitaire() == null) {
            errors.add("Veuillez renseigner le prix unitaire");
        }
        return errors;
    }
}
