package com.example.newsFeedApp.controller;

import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.service.FeedService;
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
 * Контроллер для работы с новостями
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feeds")
@Tag(name = "API по работе с новостями",
        description = "CRUD-операции для работы с новостями")
public class FeedController {

    private final FeedService feedService;

    @PostMapping
    @Operation(
            summary = "Добавление новой новости",
            description = "Нужно заполнить параметры для создания новости",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Новость была добавлена (Created)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FeedDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Новость не добавлена (Bad Request)"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Новость не добавлена (Not Found)"
                    )
            }
    )
    public FeedDto addFeed(@RequestBody CreateFeedDto dto) {

        return feedService.addFeed(dto);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Обновить информацию о новости",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация успешно обновилась (Ok)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FeedDto.class)
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
    public FeedDto updateFeed(@PathVariable("id") Long id,
                              @RequestBody CreateFeedDto dto) {

        return feedService.updateFeedById(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить новость",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Новость удалена (No Content)"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Новость не удалена (Not Found)"
                    )
            }
    )
    public boolean removeFeedById(@PathVariable("id") Long id) {

        return feedService.deleteFeedById(id);
    }

    @GetMapping
    @Operation(
            summary = "Получить все сохраненные новости",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список новостей (Ok)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FeedDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список новостей не получен (Not Found)"
                    )
            }
    )
    public List<FeedDto> getAllFeeds() {

        return feedService.getAllFeeds();
    }

    @GetMapping("/found_feeds/by_categories")
    @Operation(
            summary = "Поиск новостей по категории",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список новостей (Ok)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FeedDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список новостей не получен (Not Found)"
                    )
            }
    )
    public List<FeedDto> findByCategory(@RequestParam(required = false) String newsCategory) {

        return feedService.findByNewsCategory(newsCategory);
    }

    @GetMapping("/found_feeds/by_title")
    @Operation(
            summary = "Поиск новостей по названию",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список новостей (Ok)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FeedDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список новостей не получен (Not Found)"
                    )
            }
    )
    public List<FeedDto> findByTitle(@RequestParam(required = false) String title) {

        return feedService.findByTitleFeed(title);
    }

    @GetMapping("/found_feeds/by_content")
    @Operation(
            summary = "Поиск новостей по содержанию",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список новостей (Ok)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FeedDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Список новостей не получен (Not Found)"
                    )
            }
    )
    public List<FeedDto> findByContent(@RequestParam(required = false) String content) {

        return feedService.findByContentFeed(content);
    }
}
