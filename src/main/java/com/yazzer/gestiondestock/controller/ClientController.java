package com.yazzer.gestiondestock.controller;

import com.yazzer.gestiondestock.controller.api.ClientApi;
import com.yazzer.gestiondestock.dto.ClientDto;
import com.yazzer.gestiondestock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {


    private ClientService clientService;

    //Constructor Injection
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientDto> findALL() {
        return clientService.findALL();
    }

    @Override
    public void delete(Integer id) {
        clientService.delete(id);
    }
}
