package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {


    CommandeClientDto save (CommandeClientDto dto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode (String code);
    List<CommandeClientDto> findALL();
    void delete(Integer id);
}
