package com.example.newsFeedApp.controller;

import com.example.newsFeedApp.dto.CategoryDto;
import com.example.newsFeedApp.dto.CreateCategoryDto;
import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            description = "Нужно заполнить параметры для создания категории",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Категория была добавлена (Created)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CategoryDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Категория не добавлена (Bad Request)"
                    ),
                    @ApiResponse(
                            responseCode = "406",
                            description = "Категория не добавлена (Not Acceptable)"
                    )
            }
    )
    public CategoryDto addCategory(@RequestBody CreateCategoryDto dto) {

        return categoryService.addCategory(dto);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Обновить информацию о категории",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация успешно обновилась (Ok)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CategoryDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Информация не обновилась (Bad Request)"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Информация не обновилась (Not Found)"
                    )
            }
    )
    public CategoryDto updateCategory(@PathVariable("id") Long id,
                              @RequestBody CreateCategoryDto dto) {

        return categoryService.updateCategoryById(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить категорию",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Категория удалена (No Content)"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Категория не удалена (Not Found)"
                    )
            }
    )
    public boolean removeCategoryById(@PathVariable("id") Long id) {

        return categoryService.deleteCategoryById(id);
    }

    @GetMapping
    @Operation(
            summary = "Получить все сохраненные категории",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список категорий (Ok)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CategoryDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список категорий не получен (Not Found)"
                    )
            }
    )
    public List<CategoryDto> getAllCategories() {

        return categoryService.getAllCategories();
    }
}
