package com.yazzer.gestiondestock.controller.api;

import com.yazzer.gestiondestock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.yazzer.gestiondestock.utils.Constants.APP_ROOT;

public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + "/entreprise/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save (EntrepriseDto dto);

    @GetMapping(value = APP_ROOT + "/entreprise/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = APP_ROOT + "/entreprise/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findALL();

    @DeleteMapping(value = APP_ROOT + "/entreprise/delete/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Integer id);
}
