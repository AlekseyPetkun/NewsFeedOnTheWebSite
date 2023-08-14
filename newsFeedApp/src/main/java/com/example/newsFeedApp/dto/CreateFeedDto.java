package com.example.newsFeedApp.dto;

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
    private String newsCategory; //категория новости
}
