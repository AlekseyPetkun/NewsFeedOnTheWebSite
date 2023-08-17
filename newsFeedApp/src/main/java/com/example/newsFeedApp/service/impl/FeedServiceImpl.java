package com.example.newsFeedApp.service.impl;

import com.example.newsFeedApp.dto.*;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private static final Pageable PAGEABLE = PageRequest.of(0, 10, Sort.by("title")
            .ascending());

    @Override
    public FeedDto addFeed(CreateFeedDto dto) {

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        Category category = categoryRepository.findByNewsCategoryContainsIgnoreCase(dto.getNewsCategory());
        checkCategory(dto.getNewsCategory(), category);

        Feed entity = feedMapping.map(dto);
        entity.setDateTime(LocalDateTime.now());
        entity.setCategory(category);
        feedRepository.save(entity);

        return feedMapping.map(entity);
    }

    @Override
    public FeedDto updateFeedById(Long id, UpdateFeedDto dto) {

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new NotFindFeedException(id));

        Category category = categoryRepository.findByNewsCategoryContainsIgnoreCase(dto.getNewsCategory());
        checkCategory(dto.getNewsCategory(), category);

        feedMapping.patch(dto, feed);

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
    public ResponseWrapperFeeds getAllFeeds() {

        List<FeedDto> dtoList = feedRepository
                .findAll(PAGEABLE).stream()
                .map(feedMapping::map)
                .toList();

        return new ResponseWrapperFeeds(dtoList.size(), dtoList);
    }

    @Override
    public ResponseWrapperFeeds findByNewsCategory(String newsCategory) {

        Category category = categoryRepository.findByNewsCategoryContainsIgnoreCase(newsCategory);
        checkCategory(newsCategory, category);

        CategoryDto dto = categoryMapping.map(category);
        List<FeedDto> dtoList = feedRepository
                .findByCategoryId(dto.getId(), PAGEABLE).stream()
                .map(feedMapping::map)
                .toList();

        return new ResponseWrapperFeeds(dtoList.size(), dtoList);
    }

    @Override
    public ResponseWrapperFeeds findByTitleFeed(String title) {

        List<FeedDto> dtoList = feedRepository
                .findByTitleContainingIgnoreCase(title, PAGEABLE).stream()
                .map(feedMapping::map)
                .toList();

        return new ResponseWrapperFeeds(dtoList.size(), dtoList);
    }

    @Override
    public ResponseWrapperFeeds findByContentFeed(String content) {

        List<FeedDto> dtoList = feedRepository
                .findByContentContainingIgnoreCase(content, PAGEABLE).stream()
                .map(feedMapping::map)
                .toList();

        return new ResponseWrapperFeeds(dtoList.size(), dtoList);
    }

    private void checkCategory(String newsCategory, Category category) {

        if (category == null) {
            throw new NotFindNewsCategoryException(newsCategory);
        }
    }
}
