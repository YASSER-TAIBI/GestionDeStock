package com.yazzer.gestiondestock.services.Implementation;

import com.yazzer.gestiondestock.dto.ClientDto;
import com.yazzer.gestiondestock.exception.EntityNotFoundException;
import com.yazzer.gestiondestock.exception.ErrorCodes;
import com.yazzer.gestiondestock.exception.InvalidEntityException;
import com.yazzer.gestiondestock.model.Client;
import com.yazzer.gestiondestock.repository.ClientRepository;
import com.yazzer.gestiondestock.services.ClientService;
import com.yazzer.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(
            ClientRepository clientRepository
    ) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Client is not valid {}", dto);
            throw new InvalidEntityException("Le Client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        Client savedClient = clientRepository.save(ClientDto.toEntity(dto));

        return ClientDto.fromEntity(savedClient);
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return null;
        }

        Optional<Client> client = clientRepository.findById(id);

        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun client avec l'ID = " + id + "n' ete trouve dans la BDD", ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findALL() {
        return clientRepository.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return;
        }
        clientRepository.deleteById(id);
    }
}
