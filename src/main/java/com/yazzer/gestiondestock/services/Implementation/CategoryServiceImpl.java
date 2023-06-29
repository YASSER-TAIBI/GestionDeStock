package com.yazzer.gestiondestock.services.Implementation;

import com.yazzer.gestiondestock.dto.ArticleDto;
import com.yazzer.gestiondestock.dto.CategoryDto;
import com.yazzer.gestiondestock.exception.EntityNotFoundException;
import com.yazzer.gestiondestock.exception.ErrorCodes;
import com.yazzer.gestiondestock.exception.InvalidEntityException;
import com.yazzer.gestiondestock.model.Article;
import com.yazzer.gestiondestock.model.Category;
import com.yazzer.gestiondestock.repository.ArticleRepository;
import com.yazzer.gestiondestock.repository.CategoryRepository;
import com.yazzer.gestiondestock.services.CategoryService;
import com.yazzer.gestiondestock.validator.ArticleValidator;
import com.yazzer.gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl  implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Category is not valid {}", dto);
            throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }

        Category savedCategory = categoryRepository.save(CategoryDto.toEntity(dto));

        return CategoryDto.fromEntity(savedCategory);
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null) {
            log.error("Category ID is null");
            return null;
        }

        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun category avec l'ID = " + id + " n' ete trouve dans la BDD", ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Category CODE is null");
            return null;
        }
        Optional<Category> category = categoryRepository.findCategoryByCode(code);

        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun category avec le CODE = " + code + "n' ete trouve dans la BDD", ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findALL() {
        return categoryRepository.findAll().stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Category ID is null");
            return;
        }
        categoryRepository.deleteById(id);
    }
}
