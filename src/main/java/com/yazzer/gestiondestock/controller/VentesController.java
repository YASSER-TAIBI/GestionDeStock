package com.yazzer.gestiondestock.controller;

import com.yazzer.gestiondestock.controller.api.VentesApi;
import com.yazzer.gestiondestock.dto.VentesDto;
import com.yazzer.gestiondestock.services.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {


    private VentesService ventesService;

    @Autowired
    public VentesController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        return ventesService.save(dto);
    }

    @Override
    public VentesDto findById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public VentesDto findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public List<VentesDto> findALL() {
        return ventesService.findALL();
    }

    @Override
    public void delete(Integer id) {
        ventesService.delete(id);
    }
}
