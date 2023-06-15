package com.yazzer.gestiondestock.dto;

import com.yazzer.gestiondestock.model.MvtStk;
import com.yazzer.gestiondestock.model.Roles;
import com.yazzer.gestiondestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto article;

    private TypeMvtStk typeMvt;

    private Integer idEntreprise;


    public static MvtStkDto fromEntity(MvtStk mvtStk) {
        if (mvtStk == null) {
            return null;
            // TODO throw an exception
        }

        return MvtStkDto.builder()
                .id (mvtStk.getId())
                .dateMvt (mvtStk.getDateMvt())
                .quantite (mvtStk.getQuantite())
                .idEntreprise (mvtStk.getIdEntreprise())
                .article (ArticleDto.fromEntity(mvtStk.getArticle()))

                .build();
    }
    public static MvtStk toEntity (MvtStkDto mvtStkDto) {
        if (mvtStkDto == null) {
            return null;
            // TODO throw an exception
        }

        MvtStk mvtStk = new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setIdEntreprise(mvtStkDto.getIdEntreprise());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticle()));
        return mvtStk;
    }
}
