package com.yazzer.gestiondestock.controller;

import com.yazzer.gestiondestock.controller.api.EntrepriseApi;
import com.yazzer.gestiondestock.dto.EntrepriseDto;
import com.yazzer.gestiondestock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EntrepriseController implements EntrepriseApi {


    private EntrepriseService entrepriseService;

    //Constructor Injection
    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseDto> findALL() {
        return entrepriseService.findALL();
    }

    @Override
    public void delete(Integer id) {
        entrepriseService.delete(id);
    }
}
