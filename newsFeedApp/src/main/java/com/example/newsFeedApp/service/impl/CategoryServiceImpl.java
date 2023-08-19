package com.example.newsFeedApp.service.impl;

import com.example.newsFeedApp.dto.*;
import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.Feed;
import com.example.newsFeedApp.exception.*;
import com.example.newsFeedApp.mapper.CategoryMapping;
import com.example.newsFeedApp.repository.CategoryRepository;
import com.example.newsFeedApp.service.CategoryService;
import com.example.newsFeedApp.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private static final Pageable PAGEABLE = PageRequest.of(0, 10, Sort.by("newsCategory")
            .ascending());

    @Override
    public CategoryDto addCategory(CreateCategoryDto dto) {

        String categoryUpperCase = dto.getNewsCategory().toUpperCase();
        findCategoryByNewsCategory(categoryUpperCase);

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }

        Category entity = categoryMapping.map(dto);
        entity.setNewsCategory(categoryUpperCase);

        return categoryMapping.map(categoryRepository.save(entity));
    }

    @Override
    public CategoryDto updateCategoryById(Long id, CreateCategoryDto dto) {

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFindCategoryException(id));

        category.setNewsCategory(dto.getNewsCategory().toUpperCase());

        return categoryMapping.map(categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFindCategoryException(id));

        categoryRepository.delete(category);
    }

    @Override
    public ResponseWrapperCategories getAllCategories() {

        List<CategoryDto> dtoList = categoryRepository
                .findAll(PAGEABLE).stream()
                .map(categoryMapping::map)
                .toList();

        return new ResponseWrapperCategories(dtoList.size(), dtoList);
    }

    private void checkCategory(String newsCategory, Category category) {

        if (category != null) {
            throw new EntityAlreadyExistsException(newsCategory);
        }
    }

    private void findCategoryByNewsCategory(String newsCategory) {

        Category entity = categoryRepository
                .findByNewsCategoryContainsIgnoreCase(newsCategory);
        checkCategory(newsCategory, entity);
    }
}
