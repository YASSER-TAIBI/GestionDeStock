package com.yazzer.gestiondestock.services;

import com.yazzer.gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save (UtilisateurDto dto);
    
    UtilisateurDto findById(Integer id);
    
    List<UtilisateurDto> findALL();
    
    void delete(Integer id);
    
    UtilisateurDto findByEmail (String email);
}
