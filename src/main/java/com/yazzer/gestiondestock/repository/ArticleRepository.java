package com.yazzer.gestiondestock.repository;

import com.yazzer.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {


}
