package com.yazzer.gestiondestock.controller.api;

import com.yazzer.gestiondestock.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.yazzer.gestiondestock.utils.Constants.APP_ROOT;

public interface CategoryApi {
    @PostMapping(value = APP_ROOT + "/category/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save (CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/category/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode (@PathVariable("code") String code);

    @GetMapping(value = APP_ROOT + "/category/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findALL();

    @GetMapping(value = APP_ROOT + "/category/delete/{idCategory}")
    void delete(@PathVariable("idCategory") Integer id);
}
