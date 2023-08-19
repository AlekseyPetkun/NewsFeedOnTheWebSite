package com.example.newsFeedApp.service;

import com.example.newsFeedApp.dto.CategoryDto;
import com.example.newsFeedApp.dto.CreateCategoryDto;
import com.example.newsFeedApp.dto.ResponseWrapperCategories;

import java.util.List;

/**
 * Сервис по работе с категориями
 */
public interface CategoryService {

    /**
     * Создание новой категории
     *
     * @param category новая категория
     * @return созданная категория
     */
    CategoryDto addCategory(CreateCategoryDto dto);

    /**
     * Изменить категорию по идентификатору
     *
     * @param id  идентификатор категории
     * @param dto изменяемые параметры категории
     * @return измененная категория
     */
    CategoryDto updateCategoryById(Long id, CreateCategoryDto dto);

    /**
     * Удаление категории по идентификатору
     *
     * @param id идентификатор категории
     * @return true or false
     */
    void deleteCategoryById(Long id);

    /**
     * Показать весь список категорий
     *
     * @return список категорий
     */
    ResponseWrapperCategories getAllCategories();
}
