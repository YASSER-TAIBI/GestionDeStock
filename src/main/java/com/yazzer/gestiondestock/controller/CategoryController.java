package com.yazzer.gestiondestock.controller;

import com.yazzer.gestiondestock.controller.api.CategoryApi;
import com.yazzer.gestiondestock.dto.CategoryDto;
import com.yazzer.gestiondestock.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {


    private CategoryService categoryService;

    //Constructor Injection
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Override
    public CategoryDto save(CategoryDto dto) {
        return categoryService.save(dto);
    }

    @Override
    public CategoryDto findById(Integer idCategory) {
        return categoryService.findById(idCategory);
    }

    @Override
    public CategoryDto findByCode(String codeCategory) {
        return categoryService.findByCode(codeCategory);
    }

    @Override
    public List<CategoryDto> findALL() {
        return categoryService.findALL();
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }
}
