package com.example.newsFeedApp.controller;

import com.example.newsFeedApp.dto.CategoryDto;
import com.example.newsFeedApp.dto.CreateCategoryDto;
import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.entity.NewsCategory;
import com.example.newsFeedApp.service.CategoryService;
import com.example.newsFeedApp.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с категориями
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "API по работе с категориями",
        description = "CRUD-операции для работы с категориями")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(
            summary = "Добавление новой категории",
            description = "Нужно заполнить параметры для создания категории"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Категория была добавлена"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Категория не добавлена"
    )
    public CategoryDto addCategory(@RequestBody CreateCategoryDto dto) {

        return categoryService.addCategory(dto);
    }




    @PostMapping("/save_admin")
    @Operation(
            summary = "сохранение первой категории",
            description = "Нужно выбрать из Enum для создания категории"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Категория была сохранена"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Категория не сохранена"
    )
    public CategoryDto saveCategory(@RequestParam(required = false) NewsCategory newsCategory) {

        return categoryService.saveFirstCategory(newsCategory);
    }
}
