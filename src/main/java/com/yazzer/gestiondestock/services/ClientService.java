package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save (ClientDto dto);
    ClientDto findById(Integer id);
    List<ClientDto> findALL();
    void delete(Integer id);
}
