package com.example.newsFeedApp.service;

import com.example.newsFeedApp.dto.CategoryDto;
import com.example.newsFeedApp.dto.CreateCategoryDto;
import com.example.newsFeedApp.entity.NewsCategory;

import java.util.List;

/**
 * Сервис по работе с категориями
 */
public interface CategoryService {

    /**
     * Создание категории по параметрам
     *
     * @param dto параметры новости
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
    boolean deleteCategoryById(Long id);

    /**
     * Показать весь список категорий
     *
     * @return список категорий
     */
    List<CategoryDto> getAllCategories();

    /**
     * Сохранение первой категорий в БД
     *
     * @param newsCategory первая категория
     * @return сохраненная категория
     */
    CategoryDto saveFirstCategory(NewsCategory newsCategory);
}
