package com.yazzer.gestiondestock.services.Implementation;

import com.yazzer.gestiondestock.dto.CommandeClientDto;
import com.yazzer.gestiondestock.dto.LigneVenteDto;
import com.yazzer.gestiondestock.dto.VentesDto;
import com.yazzer.gestiondestock.exception.EntityNotFoundException;
import com.yazzer.gestiondestock.exception.ErrorCodes;
import com.yazzer.gestiondestock.exception.InvalidEntityException;
import com.yazzer.gestiondestock.model.Article;
import com.yazzer.gestiondestock.model.LigneVente;
import com.yazzer.gestiondestock.model.Ventes;
import com.yazzer.gestiondestock.repository.ArticleRepository;
import com.yazzer.gestiondestock.repository.LigneVenteRepository;
import com.yazzer.gestiondestock.repository.VentesRepository;
import com.yazzer.gestiondestock.services.VentesService;
import com.yazzer.gestiondestock.validator.CommandeFournisseurValidator;
import com.yazzer.gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {

        //verifier que l'objet ligne vente n'est pas vide
        List<String> errors = VentesValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Ventes is not valide", dto);
            throw new InvalidEntityException("L'objet Vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()) {
                articleErrors.add("Aucun article avec l'ID" + ligneVenteDto.getArticle().getId() + "n'a ete trouve dans La BDD");
            }
        });

        if (!articleErrors.isEmpty()) {

        log.error("One or more articles were not found in the DB, ", errors);
        throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD", ErrorCodes. VENTE_NOT_VALID, errors);
        }
        
     // Charger Ventes --> DB
        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));

     // Charger LigneVentes --> DB
        dto.getLigneVentes ().forEach (ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity (ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });

        return VentesDto.fromEntity(savedVentes);

    }

    @Override
    public VentesDto findById(Integer id) {
        if (id == null) {
            log.error("Vente ID is NULL");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() ->new EntityNotFoundException("Aucune vente n'a ete trouve dans BDD", ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Vente CODE is NULL");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() ->new EntityNotFoundException("Aucune vente n'a ete trouve avec le CODE "+ code, ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VentesDto> findALL() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Vente ID is NULL");
            return;
        }
        ventesRepository.deleteById(id);
    }
}
