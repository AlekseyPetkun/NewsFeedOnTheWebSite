package com.example.newsFeedApp.mapper;

import com.example.newsFeedApp.dto.CategoryDto;
import com.example.newsFeedApp.dto.CreateCategoryDto;
import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.Feed;
import com.example.newsFeedApp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Бизнес-логика по маппингу категорий (Category).
 */
@Mapper(componentModel = "spring")
public interface CategoryMapping {

    /**
     * Преобразует сущность в дто
     *
     * @param entity сущность
     * @return дто
     */
    CategoryDto map(Category entity);

    /**
     * Преобразует дто в сущность
     *
     * @param dto дто
     * @return сущность
     */
    @InheritInverseConfiguration
    Category map(CreateCategoryDto dto);

    /*public CategoryDto mapToDto(Category entity) {

        CategoryDto dto = new CategoryDto();

        dto.setId(entity.getId());
        dto.setNewsCategory(entity.getNewsCategory());

        return dto;
    }

    public Category mapToEntity(CreateCategoryDto dto) {

        Category entity = new Category();

        entity.setNewsCategory(dto.getNewsCategory());

        return entity;
    }*/
}
