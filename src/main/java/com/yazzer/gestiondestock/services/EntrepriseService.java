package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save (EntrepriseDto dto);
    EntrepriseDto findById(Integer id);
    List<EntrepriseDto> findALL();
    void delete(Integer id);
}
