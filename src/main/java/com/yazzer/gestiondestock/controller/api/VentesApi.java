package com.yazzer.gestiondestock.controller.api;

import com.yazzer.gestiondestock.dto.VentesDto;
import com.yazzer.gestiondestock.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yazzer.gestiondestock.utils.Constants.VENTES_ENDPOINT;


@Api(VENTES_ENDPOINT)
public interface VentesApi {
    @PostMapping(VENTES_ENDPOINT + "/create")
    VentesDto save (@RequestBody VentesDto dto);
    @GetMapping(VENTES_ENDPOINT + "/{idVente}")
    VentesDto findById(@PathVariable("idVente") Integer id);
    @GetMapping(VENTES_ENDPOINT + "/{codeVente}")
    VentesDto findByCode (@PathVariable("codeVente")String code);
    @GetMapping(VENTES_ENDPOINT + "/all")
    List<VentesDto> findALL();
    @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
    void delete(@PathVariable("idVente") Integer id);
}
