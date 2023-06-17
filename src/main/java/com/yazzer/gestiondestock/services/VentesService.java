package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.VentesDto;

import java.util.List;

public interface VentesService {

    VentesDto save (VentesDto dto);
    VentesDto findById(Integer id);
    VentesDto findByCode (String code);
    List<VentesDto> findALL();
    void delete(Integer id);
}
