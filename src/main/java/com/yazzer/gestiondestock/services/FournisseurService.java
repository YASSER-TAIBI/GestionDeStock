package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save (FournisseurDto dto);
    FournisseurDto findById(Integer id);
    List<FournisseurDto> findALL();
    void delete(Integer id);
}
