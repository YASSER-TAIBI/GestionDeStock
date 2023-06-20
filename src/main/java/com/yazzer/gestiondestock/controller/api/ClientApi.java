package com.yazzer.gestiondestock.controller.api;

import com.yazzer.gestiondestock.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yazzer.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/clients")
public interface ClientApi {
    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client", notes = "Cette methode permet d'enregistrer ou modifier un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client cree / modifier"),
            @ApiResponse(code = 400, message = "Aucun client n'est pas valide")
    })
    ClientDto save (@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client par ID", notes = "Cette methode permet de chercher un client par son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun client n'existe dans la BDD avec l'ID fourni")
    })
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des clients", notes = "Cette methode permet de chercher et renvoyer la liste des clients qui existent dans la BDD", responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des clients / Une liste vide")
    })
    List<ClientDto> findALL();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    @ApiOperation(value = "Supprimer un client", notes = "Cette methode permet de supprimer un client par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a ete supprimer")
    })
    void delete(@PathVariable("idClient") Integer id);
}
