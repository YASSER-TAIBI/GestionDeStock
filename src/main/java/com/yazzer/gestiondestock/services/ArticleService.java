package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save (ArticleDto dto);
    ArticleDto findById(Integer id);
    ArticleDto findByCodeArticle (String codeArticle);
    List<ArticleDto> findALL();
    void delete(Integer id);
}
