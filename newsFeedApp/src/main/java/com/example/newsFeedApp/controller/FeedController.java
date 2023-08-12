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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            description = "Нужно заполнить параметры для создания новости"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Новость была добавлена"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Новость не добавлена"
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
                            description = "Информация успешно обновилась",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FeedDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            },
            tags = "Новости"

    )
    public FeedDto updateFeed(@PathVariable("id") Long id,
                              @RequestBody CreateFeedDto dto) {

        return feedService.updateFeedById(id, dto);
    }
}
