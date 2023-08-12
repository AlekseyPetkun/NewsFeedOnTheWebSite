package com.example.newsFeedApp.service;

import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.entity.NewsCategory;

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
    FeedDto updateFeedById(Long id, CreateFeedDto dto);

    /**
     * Удаление новости по идентификатору
     *
     * @param id идентификатор новости
     * @return true or false
     */
    boolean deleteFeedById(Long id);

    /**
     * Показать весь список новостей
     *
     * @return список новостей
     */
    List<FeedDto> getAllFeeds();

    /**
     * Поиск новостей по категории
     *
     * @param newsCategory категория новости
     * @return список новостей
     */
    List<FeedDto> findByNewsCategory(NewsCategory newsCategory);

    /**
     * Поиск новостей по названию (заголовку)
     *
     * @param title название новости (заголовок)
     * @return список новостей
     */
    List<FeedDto> findByTitleFeed(String title);

    /**
     * Поиск новостей по содержанию
     *
     * @param content содержание новости
     * @return список новостей
     */
    List<FeedDto> findByContentFeed(String content);
}
