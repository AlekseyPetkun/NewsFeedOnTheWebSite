package com.example.newsFeedApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO новости
 */
@RequiredArgsConstructor
@Data
public class FeedDto {

    private long id; //идентификатор новости
    private String title; //название новости
    private String content; //содержание новости
    private LocalDateTime dateTime; //дата и время публикации
    private CategoryDto category; //категория новости
}
