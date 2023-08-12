package com.example.newsFeedApp.service.impl;

import com.example.newsFeedApp.dto.CategoryDto;
import com.example.newsFeedApp.dto.CreateCategoryDto;
import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.NewsCategory;
import com.example.newsFeedApp.mapper.CategoryMapping;
import com.example.newsFeedApp.repository.CategoryRepository;
import com.example.newsFeedApp.service.CategoryService;
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
    @Override
    public CategoryDto addCategory(CreateCategoryDto dto) {
        return null;
    }

    @Override
    public CategoryDto updateCategoryById(Long id, CreateCategoryDto dto) {
        return null;
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        return false;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return null;
    }

    @Override
    public CategoryDto saveFirstCategory(NewsCategory newsCategory) {

        CreateCategoryDto dto = new CreateCategoryDto(newsCategory);

        Category entity = categoryMapping.mapToEntity(dto);

        categoryRepository.save(entity);

        return categoryMapping.mapToDto(entity);
    }
}
