package com.yazzer.gestiondestock.validator;

import com.yazzer.gestiondestock.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VentesValidator {

    public static List<String> validate(VentesDto ventesDto) {
        List<String> errors = new ArrayList<>();

        if(ventesDto == null){
            errors.add("Veuillez renseigner le nom du client");
            errors.add("Veuillez renseigner le prenom du client");
            return errors;
        }

        if (!StringUtils.hasLength(ventesDto.getCode())) {
            errors.add("Veuillez renseigner le code vente");
        }
        if (ventesDto.getDateVente() == null) {
            errors.add("Veuillez renseigner la date du vente");
        }

        return errors;
    }
}
