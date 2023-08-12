package com.example.newsFeedApp.repository;

import com.example.newsFeedApp.entity.Feed;
import com.example.newsFeedApp.entity.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для новостей
 */
@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

    /**
     * Поиск новостей по категории
     *
     * @param newsCategory категория
     * @return список найденный новостей
     */
    List<Feed> findByCategoryId(Long newsCategory);

    /**
     * Поиск новостей по названию
     *
     * @param title название
     * @return список найденных новостей
     */
    List<Feed> findByTitleContainingIgnoreCase(String title);

    /**
     * Поиск новостей по содержанию
     *
     * @param content содержание
     * @return список найденных новостей
     */
    List<Feed> findByContentContainingIgnoreCase(String content);
}
