package com.example.newsFeedApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO всех новостей.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapperFeeds {

    private Integer count; // общее количество новостей
    private List<FeedDto> results; // все найденные новости
}
