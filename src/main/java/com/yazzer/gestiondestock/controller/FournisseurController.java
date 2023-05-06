package com.yazzer.gestiondestock.controller;

import com.yazzer.gestiondestock.controller.api.FournisseurApi;
import com.yazzer.gestiondestock.dto.FournisseurDto;
import com.yazzer.gestiondestock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {


    private FournisseurService fournisseurService;

    //Constructor Injection
    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findALL() {
        return fournisseurService.findALL();
    }

    @Override
    public void delete(Integer id) {
        fournisseurService.delete(id);
    }
}
