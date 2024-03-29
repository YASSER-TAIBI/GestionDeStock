package com.yazzer.gestiondestock.dto;

import com.yazzer.gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder // Il permet de créer un builder en utilisant le design pattern builder. C'est une classe qui permet de construire un objet en exposant une méthode ayant le même nom que l'attribut et renvoyant la même méthode
public class ArticleDto {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto category;

    private Integer idEntreprise;

    public static ArticleDto fromEntity(Article article) {
        if (article == null) {
            return null;
            // TODO throw an exception
        }

        return ArticleDto.builder()
                .id (article.getId())
                .codeArticle (article.getCodeArticle())
                .designation (article.getDesignation())
                .prixUnitaireHt (article.getPrixUnitaireHt())
                .tauxTva (article.getTauxTva())
                .idEntreprise (article.getIdEntreprise())
                .prixUnitaireTtc (article.getPrixUnitaireTtc())
                .photo (article.getPhoto())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }
    public static Article toEntity (ArticleDto articleDto) {
        if (articleDto == null) {
            return null;
            // TODO throw an exception
        }

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setPhoto(articleDto.getPhoto());
        article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));

        return article;
    }
}
