package com.yazzer.gestiondestock.services.Implementation;

import com.yazzer.gestiondestock.dto.CommandeFournisseurDto;
import com.yazzer.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.yazzer.gestiondestock.exception.EntityNotFoundException;
import com.yazzer.gestiondestock.exception.ErrorCodes;
import com.yazzer.gestiondestock.exception.InvalidEntityException;
import com.yazzer.gestiondestock.model.Article;
import com.yazzer.gestiondestock.model.Fournisseur;
import com.yazzer.gestiondestock.model.CommandeFournisseur;
import com.yazzer.gestiondestock.model.LigneCommandeFournisseur;
import com.yazzer.gestiondestock.repository.ArticleRepository;
import com.yazzer.gestiondestock.repository.FournisseurRepository;
import com.yazzer.gestiondestock.repository.CommandeFournisseurRepository;
import com.yazzer.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.yazzer.gestiondestock.validator.CommandeFournisseurValidator;
import com.yazzer.gestiondestock.services.CommandeFournisseurService;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    @Autowired
    public CommandeFournisseurServiceImpl(
            CommandeFournisseurRepository commandeFournisseurRepository,
            FournisseurRepository fournisseurRepository,
            ArticleRepository articleRepository,
            LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository
    ) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.articleRepository = articleRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }
//16:43 Partie 14
    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande Fournisseur is not valide", dto);
            throw new InvalidEntityException("La Commande Fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOUTNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} was not found in the DB", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID" + dto.getFournisseur().getId() + "n'a ete trouve dans la BDD", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(ligCmdFr -> {
                if (ligCmdFr.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdFr.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + ligCmdFr.getArticle().getId() + "n'existe pas");
                    }
                } else {
                    articleErrors.add("Impossible d'enregister une commande avec un article NULL");
                }
            });
        }

        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur savedCmdFr = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(ligCmdFr -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFr);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFr);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.fromEntity(savedCmdFr);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("Commande fournisseur ID is NULL");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() ->new EntityNotFoundException("Aucune commande fournisseur n'a ete trouve avec l'ID "+ id, ErrorCodes.COMMANDE_FOUTNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Commande fournisseur CODE is NULL");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() ->new EntityNotFoundException("Aucune commande fournisseur n'a ete trouve avec le CODE "+ code, ErrorCodes.COMMANDE_FOUTNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findALL() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Commande fournisseur ID is NULL");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
