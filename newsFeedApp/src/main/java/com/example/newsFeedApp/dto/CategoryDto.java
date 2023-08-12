package com.example.newsFeedApp.dto;

import com.example.newsFeedApp.entity.NewsCategory;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO категории
 */
@RequiredArgsConstructor
@Data
public class CategoryDto {

    private long id; //идентификатор категории
    private NewsCategory newsCategory; //категория
}
