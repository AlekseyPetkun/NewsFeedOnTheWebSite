package com.example.newsFeedApp.service.impl;

import com.example.newsFeedApp.dto.CategoryDto;
import com.example.newsFeedApp.dto.CreateCategoryDto;
import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.exception.EntityAlreadyExistsException;
import com.example.newsFeedApp.exception.ValidationException;
import com.example.newsFeedApp.mapper.CategoryMapping;
import com.example.newsFeedApp.repository.CategoryRepository;
import com.example.newsFeedApp.service.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapping categoryMapping;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        // Здесь можно инициализировать зависимости для тестов
    }

    @Test
    public void testAddCategory() {
        // Создаем тестовые данные
        CreateCategoryDto createDto = new CreateCategoryDto("TECHNOLOGY");

        // Определяем ожидаемый результат
        CategoryDto expectedDto = new CategoryDto(1L, "TECHNOLOGY");

        // Определяем результат, который вернет метод categoryService.addCategory()
        Category entity = new Category();
        when(categoryRepository.findByNewsCategoryContainsIgnoreCase("TECHNOLOGY")).thenReturn(null);
        when(validationService.validate(createDto)).thenReturn(true);
        when(categoryMapping.map(createDto)).thenReturn(entity);
        when(categoryRepository.save(entity)).thenReturn(entity);
        when(categoryMapping.map(entity)).thenReturn(expectedDto);

        // Вызываем метод categoryService.addCategory() и проверяем результат
        CategoryDto actualDto = categoryService.addCategory(createDto);
        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void testAddCategoryWithExistingCategory() {
        // Создаем тестовые данные
        CreateCategoryDto createDto = new CreateCategoryDto("TECHNOLOGY");

        // Определяем результат, который вернет метод categoryService.addCategory()
        when(categoryRepository.findByNewsCategoryContainsIgnoreCase("TECHNOLOGY")).thenReturn(new Category());

        // Вызываем метод categoryService.addCategory() и проверяем, что выбрасывается исключение EntityAlreadyExistsException
        assertThrows(ValidationException.class, () -> categoryService.addCategory(createDto));
    }



}