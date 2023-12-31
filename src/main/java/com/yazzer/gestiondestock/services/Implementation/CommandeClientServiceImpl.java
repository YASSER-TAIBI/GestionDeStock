package com.yazzer.gestiondestock.services.Implementation;

import com.yazzer.gestiondestock.dto.ArticleDto;
import com.yazzer.gestiondestock.dto.ClientDto;
import com.yazzer.gestiondestock.dto.CommandeClientDto;
import com.yazzer.gestiondestock.dto.LigneCommandeClientDto;
import com.yazzer.gestiondestock.dto.MvtStkDto;
import com.yazzer.gestiondestock.exception.EntityNotFoundException;
import com.yazzer.gestiondestock.exception.ErrorCodes;
import com.yazzer.gestiondestock.exception.InvalidEntityException;
import com.yazzer.gestiondestock.exception.InvalidOperationException;
import com.yazzer.gestiondestock.model.Article;
import com.yazzer.gestiondestock.model.Client;
import com.yazzer.gestiondestock.model.CommandeClient;
import com.yazzer.gestiondestock.model.EtatCommande;
import com.yazzer.gestiondestock.model.LigneCommandeClient;
import com.yazzer.gestiondestock.model.SourceMvtStk;
import com.yazzer.gestiondestock.model.TypeMvtStk;
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

import java.math.BigDecimal;
import java.time.Instant;
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

        if (dto.getId() != null && dto.isCommandeLivree()) {
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
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

        // Charger CommandeClient --> DB
        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        // Charger LigneCommandeClients --> DB
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

    @Override
  public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    checkIdCommande(idCommande);
    if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
      log.error("L'etat de la commande client is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
    commandeClient.setEtatCommande(etatCommande);

    CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient));
    // if (commandeClient.isCommandeLivree()) {
    //   updateMvtStk(idCommande);
    // }

    return CommandeClientDto.fromEntity(savedCmdClt);
  }

  @Override
  public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
      log.error("L'ID de la ligne commande is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO",
          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }

    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
    Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);

    LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
    ligneCommandeClient.setQuantite(quantite);
    ligneCommandeClientRepository.save(ligneCommandeClient);

    return commandeClient;
  }

   @Override
  public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
    checkIdCommande(idCommande);
    if (idClient == null) {
      log.error("L'ID du client is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID client null",
          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
    Optional<Client> clientOptional = clientRepository.findById(idClient);
    if (clientOptional.isEmpty()) {
      throw new EntityNotFoundException(
          "Aucun client n'a ete trouve avec l'ID " + idClient, ErrorCodes.CLIENT_NOT_FOUND);
    }
    commandeClient.setClient(ClientDto.fromEntity(clientOptional.get()));

    return CommandeClientDto.fromEntity(
        commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient))
    );
  }

  private CommandeClientDto checkEtatCommande(Integer idCommande) {
    CommandeClientDto commandeClient = findById(idCommande);
    if (commandeClient.isCommandeLivree()) {
      throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
    return commandeClient;
  }

  private Optional<LigneCommandeClient> findLigneCommandeClient(Integer idLigneCommande) {
    Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository.findById(idLigneCommande);
    if (ligneCommandeClientOptional.isEmpty()) {
      throw new EntityNotFoundException(
          "Aucune ligne commande client n'a ete trouve avec l'ID " + idLigneCommande, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
    }
    return ligneCommandeClientOptional;
  }

    private void checkIdCommande(Integer idCommande) {
        if (idCommande == null) {
          log.error("Commande client ID is NULL");
          throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
      }
    
      private void checkIdLigneCommande(Integer idLigneCommande) {
        if (idLigneCommande == null) {
          log.error("L'ID de la ligne commande is NULL");
          throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
      }

   

    //   private void updateMvtStk(Integer idCommande) {
    //     List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(idCommande);
    //     ligneCommandeClients.forEach(lig -> {
    //       effectuerSortie(lig);
    //     });
    //   }

//       private void effectuerSortie(LigneCommandeClient lig) {
//     MvtStkDto mvtStkDto = MvtStkDto.builder()
//         .article(ArticleDto.fromEntity(lig.getArticle()))
//         .dateMvt(Instant.now())
//         .typeMvt(TypeMvtStk.SORTIE)
//         .sourceMvt(SourceMvtStk.COMMANDE_CLIENT)
//         .quantite(lig.getQuantite())
//         .idEntreprise(lig.getIdEntreprise())
//         .build();
//     mvtStkService.sortieStock(mvtStkDto);
//   }
}
