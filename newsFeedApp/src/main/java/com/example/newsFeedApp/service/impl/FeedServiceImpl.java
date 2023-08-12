package com.example.newsFeedApp.service.impl;

import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.FeedDto;
import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.Feed;
import com.example.newsFeedApp.entity.NewsCategory;
import com.example.newsFeedApp.exception.NotFindFeedException;
import com.example.newsFeedApp.exception.ValidationException;
import com.example.newsFeedApp.mapper.FeedMapping;
import com.example.newsFeedApp.repository.CategoryRepository;
import com.example.newsFeedApp.repository.FeedRepository;
import com.example.newsFeedApp.service.FeedService;
import com.example.newsFeedApp.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final ValidationService validationService;

    @Override
    public FeedDto addFeed(CreateFeedDto dto) {

        if(!validationService.validate(dto)){
            throw new ValidationException(dto.toString());
        }
        Feed entity = feedMapping.mapToEntity(dto);
        feedRepository.save(entity);

        return feedMapping.mapToDto(entity);
    }

    @Override
    public FeedDto updateFeedById(Long id, CreateFeedDto dto) {

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new NotFindFeedException(id));

        Category category = categoryRepository.findByNewsCategory(dto.getNewsCategory());

        feed.setTitle(dto.getTitle());
        feed.setContent(dto.getContent());
        feed.setCategory(category);

        feedRepository.save(feed);

        return feedMapping.mapToDto(feed);
    }

    @Override
    public boolean deleteFeedById(Long id) {
        return false;
    }

    @Override
    public List<FeedDto> getAllFeeds() {
        return null;
    }

    @Override
    public List<FeedDto> findByNewsCategory(NewsCategory newsCategory) {
        return null;
    }

    @Override
    public List<FeedDto> findByTitleFeed(String Title) {
        return null;
    }

    @Override
    public List<FeedDto> findByContentFeed(String content) {
        return null;
    }
}
