package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save (CategoryDto dto);
    CategoryDto findById(Integer id);
    CategoryDto findByCode (String code);
    List<CategoryDto> findALL();
    void delete(Integer id);
}
