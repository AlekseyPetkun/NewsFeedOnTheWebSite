package com.example.newsFeedApp.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.Feed;
import com.example.newsFeedApp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Бизнес-логика по маппингу новостей (Feed).
 */
@Mapper(componentModel = "spring")
public interface FeedMapping {

    /**
     * Преобразует сущность в дто
     *
     * @param entity сущность
     * @return дто
     */
    FeedDto map(Feed entity);

    /**
     * Преобразует дто в сущность
     *
     * @param dto дто
     * @return сущность
     */
    @InheritInverseConfiguration
    Feed map(CreateFeedDto dto);


    /*private final CategoryRepository categoryRepository;

    public FeedDto mapToDto(Feed entity) {

        FeedDto dto = new FeedDto();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setDateTime(entity.getDateTime());
        dto.setCategory(entity.getCategory().getId());

        return dto;
    }

    public Feed mapToEntity(CreateFeedDto dto) {

        Feed entity = new Feed();
        Category category = categoryRepository.findByNewsCategory(dto.getNewsCategory());

        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setDateTime(LocalDateTime.now());
        entity.setCategory(category);

        return entity;
    }*/
}
