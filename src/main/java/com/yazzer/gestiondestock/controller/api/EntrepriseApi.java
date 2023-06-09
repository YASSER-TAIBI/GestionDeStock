package com.yazzer.gestiondestock.controller.api;

import com.yazzer.gestiondestock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yazzer.gestiondestock.utils.Constants.ENTREPRISE_ENDPOINT;

@Api(ENTREPRISE_ENDPOINT)
public interface EntrepriseApi {

    @PostMapping(value = ENTREPRISE_ENDPOINT +"/create")
    EntrepriseDto save (@RequestBody EntrepriseDto dto);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/{idEntreprise}")
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/all")
    List<EntrepriseDto> findALL();

    @DeleteMapping(value = ENTREPRISE_ENDPOINT + "/delete/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Integer id);
}
