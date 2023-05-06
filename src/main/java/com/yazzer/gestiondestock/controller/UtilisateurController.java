package com.yazzer.gestiondestock.controller;

import com.yazzer.gestiondestock.controller.api.UtilisateurApi;
import com.yazzer.gestiondestock.dto.UtilisateurDto;
import com.yazzer.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {


    private UtilisateurService utilisateurService;

    //Constructor Injection
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> findALL() {
        return utilisateurService.findALL();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
