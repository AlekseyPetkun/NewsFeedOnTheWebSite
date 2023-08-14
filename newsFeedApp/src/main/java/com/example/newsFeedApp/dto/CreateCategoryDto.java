package com.example.newsFeedApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO создание категории
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCategoryDto {

    private String newsCategory; //категория
}
