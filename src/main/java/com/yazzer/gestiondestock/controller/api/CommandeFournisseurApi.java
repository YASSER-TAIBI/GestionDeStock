package com.yazzer.gestiondestock.controller.api;

import com.yazzer.gestiondestock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yazzer.gestiondestock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;

@Api(COMMANDE_FOURNISSEUR_ENDPOINT)
public interface CommandeFournisseurApi {

    @PostMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/create")
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);
    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur)" )
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Integer id);
    @GetMapping( COMMANDE_FOURNISSEUR_ENDPOINT + "/{codeCommandeFournisseur)")
    CommandeFournisseurDto findByCode (@PathVariable("codeCommandeFournisseur") String code);
    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/all")
    List<CommandeFournisseurDto> findAll();
    @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur)")
    void delete (@PathVariable("idCommandeFournisseur") Integer id);
}
