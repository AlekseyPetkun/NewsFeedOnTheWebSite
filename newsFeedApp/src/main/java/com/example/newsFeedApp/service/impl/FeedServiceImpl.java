package com.example.newsFeedApp.service.impl;

import com.example.newsFeedApp.dto.CategoryDto;
import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.Feed;
import com.example.newsFeedApp.exception.*;
import com.example.newsFeedApp.mapper.CategoryMapping;
import com.example.newsFeedApp.mapper.FeedMapping;
import com.example.newsFeedApp.repository.CategoryRepository;
import com.example.newsFeedApp.repository.FeedRepository;
import com.example.newsFeedApp.service.FeedService;
import com.example.newsFeedApp.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Бизнес-логика по работе с новостями
 */
@Service
@AllArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final CategoryRepository categoryRepository;
    private final FeedMapping feedMapping;
    private final CategoryMapping categoryMapping;
    private final ValidationService validationService;

    @Override
    public FeedDto addFeed(CreateFeedDto dto) {

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        Category category = categoryRepository.findByNewsCategoryContainsIgnoreCase(dto.getNewsCategory());
        if(category == null){
            throw new NotFindNewsCategoryException(dto.getNewsCategory());
        }
        Feed entity = feedMapping.map(dto);
        entity.setDateTime(LocalDateTime.now());
        entity.setCategory(category);
        feedRepository.save(entity);

        return feedMapping.map(entity);
    }

    @Override
    public FeedDto updateFeedById(Long id, CreateFeedDto dto) {

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new NotFindFeedException(id));

        Category category = categoryRepository.findByNewsCategoryContainsIgnoreCase(dto.getNewsCategory());
        if(category == null){
            throw new NullPointerException();
        }
        feed.setTitle(dto.getTitle());
        feed.setContent(dto.getContent());
        feed.setCategory(category);

        feedRepository.save(feed);

        return feedMapping.map(feed);
    }

    @Override
    public boolean deleteFeedById(Long id) {

        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new NotFindFeedException(id));

        feedRepository.delete(feed);

        return true;
    }

    @Override
    public List<FeedDto> getAllFeeds() {

        List<FeedDto> dtoList = feedRepository
                .findAll().stream()
                .map(feedMapping::map)
                .toList();

        if(dtoList != null){
            return dtoList;
        } else {
            throw new NotFindListException();
        }
    }

    @Override
    public List<FeedDto> findByNewsCategory(String newsCategory) {

        Category category = categoryRepository.findByNewsCategoryContainsIgnoreCase(newsCategory);
        if(category == null){
            throw new NullPointerException();
        }

        CategoryDto dto = categoryMapping.map(category);
        List<FeedDto> dtoList = feedRepository
                .findByCategoryId(dto.getId()).stream()
                .map(feedMapping::map)
                .toList();

        if(dtoList != null){
            return dtoList;
        } else {
            throw new NotFindListException();
        }
    }

    @Override
    public List<FeedDto> findByTitleFeed(String title) {

        List<FeedDto> dtoList = feedRepository
                .findByTitleContainingIgnoreCase(title).stream()
                .map(feedMapping::map)
                .toList();

        if(dtoList != null){
            return dtoList;
        } else {
            throw new NotFindListException();
        }
    }

    @Override
    public List<FeedDto> findByContentFeed(String content) {

        List<FeedDto> dtoList = feedRepository
                .findByContentContainingIgnoreCase(content).stream()
                .map(feedMapping::map)
                .toList();

        if(dtoList != null){
            return dtoList;
        } else {
            throw new NotFindListException();
        }
    }
}
