package com.yazzer.gestiondestock.services.Implementation;

import com.yazzer.gestiondestock.dto.CommandeClientDto;
import com.yazzer.gestiondestock.dto.LigneCommandeClientDto;
import com.yazzer.gestiondestock.exception.EntityNotFoundException;
import com.yazzer.gestiondestock.exception.ErrorCodes;
import com.yazzer.gestiondestock.exception.InvalidEntityException;
import com.yazzer.gestiondestock.model.Article;
import com.yazzer.gestiondestock.model.Client;
import com.yazzer.gestiondestock.model.CommandeClient;
import com.yazzer.gestiondestock.model.LigneCommandeClient;
import com.yazzer.gestiondestock.repository.ArticleRepository;
import com.yazzer.gestiondestock.repository.ClientRepository;
import com.yazzer.gestiondestock.repository.CommandeClientRepository;
import com.yazzer.gestiondestock.repository.LigneCommandeClientRepository;
import com.yazzer.gestiondestock.services.CommandeClientService;
import com.yazzer.gestiondestock.validator.ArticleValidator;
import com.yazzer.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    @Autowired
    public CommandeClientServiceImpl(
            CommandeClientRepository commandeClientRepository,
            ClientRepository clientRepository,
            ArticleRepository articleRepository,
            LigneCommandeClientRepository ligneCommandeClientRepository
    ) {
        this.commandeClientRepository = commandeClientRepository;
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande Client is not valide", dto);
            throw new InvalidEntityException("La Commande Client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
            if (client.isEmpty()) {
        log.warn("Client with ID {} was not found in the DB", dto.getClient().getId());
        throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getClient().getId() + "n'a ete trouve dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligCmdClt -> {
                if (ligCmdClt.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + ligCmdClt.getArticle().getId() + "n'existe pas");
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

        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

            if (dto.getLigneCommandeClients() != null) {
                dto.getLigneCommandeClients().forEach(ligCmdclt -> {
                    LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdclt);
                    ligneCommandeClient.setCommandeClient(savedCmdClt);
                    ligneCommandeClientRepository.save(ligneCommandeClient);
                });
            }
        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null) {
            log.error("Commande client ID is NULL");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() ->new EntityNotFoundException("Aucune commande client n'a ete trouve avec l'ID "+ id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Commande client CODE is NULL");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() ->new EntityNotFoundException("Aucune commande client n'a ete trouve avec le CODE "+ code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findALL() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Commande client ID is NULL");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
