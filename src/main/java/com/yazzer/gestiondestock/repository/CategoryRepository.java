package com.yazzer.gestiondestock.repository;

import com.yazzer.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
