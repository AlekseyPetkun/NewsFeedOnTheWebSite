package com.example.newsFeedApp.service;

import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.dto.ResponseWrapperFeeds;
import com.example.newsFeedApp.dto.UpdateFeedDto;

import java.util.List;

/**
 * Сервис по работе с новостями
 */
public interface FeedService {

    /**
     * Создание новости по параметрам
     *
     * @param dto параметры новости
     * @return созданная новость
     */
    FeedDto addFeed(CreateFeedDto dto);

    /**
     * Изменить новость по идентификатору
     *
     * @param id  идентификатор новости
     * @param dto изменяемые параметры новости
     * @return измененная новость
     */
    FeedDto updateFeedById(Long id, UpdateFeedDto dto);

    /**
     * Удаление новости по идентификатору
     *
     * @param id идентификатор новости
     */
    void deleteFeedById(Long id);

    /**
     * Показать весь список новостей
     *
     * @return список новостей
     */
    ResponseWrapperFeeds getAllFeeds();

    /**
     * Поиск новостей по категории
     *
     * @param newsCategory категория новости
     * @return список новостей
     */
    ResponseWrapperFeeds findByNewsCategory(String newsCategory);

    /**
     * Поиск новостей по названию (заголовку)
     *
     * @param title название новости (заголовок)
     * @return список новостей
     */
    ResponseWrapperFeeds findByTitleFeed(String title);

    /**
     * Поиск новостей по содержанию
     *
     * @param content содержание новости
     * @return список новостей
     */
    ResponseWrapperFeeds findByContentFeed(String content);
}
