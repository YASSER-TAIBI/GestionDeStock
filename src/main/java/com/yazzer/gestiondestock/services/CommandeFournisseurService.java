package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournisseurService {


    CommandeFournisseurDto save (CommandeFournisseurDto dto);
    CommandeFournisseurDto findById(Integer id);
    CommandeFournisseurDto findByCode (String code);
    List<CommandeFournisseurDto> findALL();
    void delete(Integer id);
}
