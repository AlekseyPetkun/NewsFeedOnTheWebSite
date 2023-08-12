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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Бизнес-логика по маппингу категорий (Category).
 */
@Service
@Slf4j
@AllArgsConstructor
public class CategoryMapping {

    public CategoryDto mapToDto(Category entity) {

        CategoryDto dto = new CategoryDto();

        dto.setId(entity.getId());
        dto.setNewsCategory(entity.getNewsCategory());

        return dto;
    }

    public Category mapToEntity(CreateCategoryDto dto) {

        Category entity = new Category();

        entity.setNewsCategory(dto.getNewsCategory());

        return entity;
    }
}
