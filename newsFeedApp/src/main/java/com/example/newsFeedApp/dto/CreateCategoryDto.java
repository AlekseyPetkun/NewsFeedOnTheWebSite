package com.example.newsFeedApp.dto;

import com.example.newsFeedApp.entity.NewsCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * DTO создание категории
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCategoryDto {

    private NewsCategory newsCategory; //категория
}
