package com.example.newsFeedApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO категории
 */
@RequiredArgsConstructor
@Data
public class CategoryDto {

    private long id; //идентификатор категории
    private String newsCategory; //категория
}
