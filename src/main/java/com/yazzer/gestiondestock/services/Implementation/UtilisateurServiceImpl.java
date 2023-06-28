package com.yazzer.gestiondestock.services.Implementation;

import com.yazzer.gestiondestock.dto.UtilisateurDto;
import com.yazzer.gestiondestock.exception.EntityNotFoundException;
import com.yazzer.gestiondestock.exception.ErrorCodes;
import com.yazzer.gestiondestock.exception.InvalidEntityException;
import com.yazzer.gestiondestock.model.Utilisateur;
import com.yazzer.gestiondestock.repository.UtilisateurRepository;
import com.yazzer.gestiondestock.services.UtilisateurService;
import com.yazzer.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurServiceImpl(
            UtilisateurRepository utilisateurRepository
    ) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Utilisateur is not valid {}", dto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }

        Utilisateur savedUtilisateur = utilisateurRepository.save(UtilisateurDto.toEntity(dto));

        return UtilisateurDto.fromEntity(savedUtilisateur);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun utilisateur avec l'ID = " + id + "n' ete trouve dans la BDD", ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findALL() {
        return utilisateurRepository.findAll().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDto findByEmail (String email) {
        return utilisateurRepository.findUtilisateurByEmail (email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email = " + email + " n' ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
                );
    }
}
