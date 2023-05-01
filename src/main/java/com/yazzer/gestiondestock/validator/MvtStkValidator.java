package com.yazzer.gestiondestock.validator;

import com.yazzer.gestiondestock.dto.MvtStkDto;

import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {


    public static List<String> validate(MvtStkDto mvtStkDto ) {
        List<String> errors = new ArrayList<>();

        if(mvtStkDto == null){
            errors.add("Veuillez renseigner la date de mouvement de stock");
            errors.add("Veuillez renseigner la quantite");
            errors.add("Veuillez selectionner un article");
            return errors;
        }

        if (mvtStkDto.getDateMvt() == null) {
            errors.add("Veuillez renseigner la date de mouvement de stock");
        }
        if (mvtStkDto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantite");
        }
        if (mvtStkDto.getArticle() == null) {
            errors.add("Veuillez selectionner un article");
        }
        return errors;
    }
}
