package com.yazzer.gestiondestock.services.Implementation;

import com.yazzer.gestiondestock.dto.ArticleDto;
import com.yazzer.gestiondestock.dto.EntrepriseDto;
import com.yazzer.gestiondestock.exception.EntityNotFoundException;
import com.yazzer.gestiondestock.exception.ErrorCodes;
import com.yazzer.gestiondestock.exception.InvalidEntityException;
import com.yazzer.gestiondestock.model.Article;
import com.yazzer.gestiondestock.model.Entreprise;
import com.yazzer.gestiondestock.repository.ArticleRepository;
import com.yazzer.gestiondestock.repository.EntrepriseRepository;
import com.yazzer.gestiondestock.services.EntrepriseService;
import com.yazzer.gestiondestock.validator.ArticleValidator;
import com.yazzer.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    @Autowired
    public EntrepriseServiceImpl(
            EntrepriseRepository entrepriseRepository
    ) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", dto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }

        Entreprise savedEntreprise = entrepriseRepository.save(EntrepriseDto.toEntity(dto));

        return EntrepriseDto.fromEntity(savedEntreprise);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return null;
        }

        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);

        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun entreprise avec l'ID = " + id + "n' ete trouve dans la BDD", ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findALL() {
        return entrepriseRepository.findAll().stream().map(EntrepriseDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
