package com.example.newsFeedApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO всех категорий.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapperCategories {

    private Long count; // общее количество категорий
    private List<CategoryDto> results; // все категории
}
