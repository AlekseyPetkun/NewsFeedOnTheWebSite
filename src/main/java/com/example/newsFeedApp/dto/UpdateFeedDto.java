package com.example.newsFeedApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO изменение новости
 */
@RequiredArgsConstructor
@Data
public class UpdateFeedDto {

    private String title; //название новости
    private String content; //содержание новости
    private String newsCategory; //категория новости
}
