package com.example.newsFeedApp.repository;

import com.example.newsFeedApp.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * @param pageable     пагинация
     * @return список найденный новостей
     */
    List<Feed> findByCategoryId(Long newsCategory, Pageable pageable);

    /**
     * Поиск новостей по названию
     *
     * @param title    название
     * @param pageable пагинация
     * @return список найденных новостей
     */
    List<Feed> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    /**
     * Поиск новостей по содержанию
     *
     * @param content  содержание
     * @param pageable пагинация
     * @return список найденных новостей
     */
    List<Feed> findByContentContainingIgnoreCase(String content, Pageable pageable);

    /**
     * Показать все сохраненные новости
     *
     * @param pageable пагинация
     * @return страница новостей
     */
    @Override
    Page<Feed> findAll(Pageable pageable);
}
