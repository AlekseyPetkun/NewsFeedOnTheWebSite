package com.example.newsFeedApp.dto;

import com.example.newsFeedApp.entity.NewsCategory;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO создание новости
 */
@RequiredArgsConstructor
@Data
public class CreateFeedDto {

    private String title; //название новости
    private String content; //содержание новости
    private NewsCategory newsCategory; //категория новости
}
