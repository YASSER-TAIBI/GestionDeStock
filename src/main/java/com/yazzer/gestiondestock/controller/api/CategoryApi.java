package com.yazzer.gestiondestock.controller.api;

import com.yazzer.gestiondestock.dto.ArticleDto;
import com.yazzer.gestiondestock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yazzer.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie", notes = "Cette methode permet d'enregistrer ou modifier un categorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie cree / modifier"),
            @ApiResponse(code = 400, message = "Aucun categorie n'est pas valide")
    })
    CategoryDto save (@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par Code", notes = "Cette methode permet de chercher un categorie par son Code", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun categorie n'existe dans la BDD avec le Code fourni")
    })
    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par ID", notes = "Cette methode permet de chercher un categorie par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDto findByCode (@PathVariable("codeCategory") String code);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des categories", notes = "Cette methode permet de chercher et renvoyer la liste des categories qui existent dans la BDD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / Une liste vide")
    })
    List<CategoryDto> findALL();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    @ApiOperation(value = "Supprimer un categorie", notes = "Cette methode permet de supprimer un categorie par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete supprimer")
    })
    void delete(@PathVariable("idCategory") Integer id);
}
