package com.example.newsFeedApp.service.impl;

import com.example.newsFeedApp.dto.CategoryDto;
import com.example.newsFeedApp.dto.CreateCategoryDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.Feed;
import com.example.newsFeedApp.exception.*;
import com.example.newsFeedApp.mapper.CategoryMapping;
import com.example.newsFeedApp.repository.CategoryRepository;
import com.example.newsFeedApp.service.CategoryService;
import com.example.newsFeedApp.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Бизнес-логика по работе с категориями
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapping categoryMapping;
    private final ValidationService validationService;

    @Override
    public CategoryDto addCategory(CreateCategoryDto dto) {

        String categoryUpperCase = dto.getNewsCategory().toUpperCase();
        Category check = categoryRepository.findByNewsCategoryContainsIgnoreCase(categoryUpperCase);
        if(check != null){
            throw new EntityAlreadyExistsException(categoryUpperCase);
        }

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }

        Category entity = categoryMapping.map(dto);
        entity.setNewsCategory(categoryUpperCase);
        categoryRepository.save(entity);

        return categoryMapping.map(entity);
    }

    @Override
    public CategoryDto updateCategoryById(Long id, CreateCategoryDto dto) {

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFindCategoryException(id));

        category.setNewsCategory(dto.getNewsCategory().toUpperCase());

        categoryRepository.save(category);

        return categoryMapping.map(category);
    }

    @Override
    public boolean deleteCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFindCategoryException(id));

        categoryRepository.delete(category);

        return true;
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<CategoryDto> dtoList = categoryRepository
                .findAll().stream()
                .map(categoryMapping::map)
                .toList();

        if(dtoList != null){
            return dtoList;
        } else {
            throw new NotFindListException();
        }
    }
}
