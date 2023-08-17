package com.example.newsFeedApp.repository;

import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для категорий
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Поиск по названию категории
     *
     * @param newsCategory категория
     * @return сущность
     */
    Category findByNewsCategoryContainsIgnoreCase(String newsCategory);

    /**
     * Показать все сохраненные категории
     *
     * @param pageable пагинация
     * @return страница категорий
     */
    @Override
    Page<Category> findAll(Pageable pageable);
}
