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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

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

        checkValidate(dto);

        TimeZone timeZone = TimeZone.getDefault();
        LocalDateTime localDateTime = LocalDateTime.now(timeZone.toZoneId());

        Category category = findCategoryByNewsCategory(dto.getNewsCategory());

        Feed entity = feedMapping.map(dto);
        entity.setDateTime(localDateTime);
        entity.setCategory(category);

        return feedMapping.map(feedRepository.save(entity));
    }

    @Override
    public FeedDto updateFeedById(Long id, UpdateFeedDto dto) {

        checkValidate(dto);

        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new NotFindFeedException(id));

        Category category = findCategoryByNewsCategory(dto.getNewsCategory());

        feedMapping.patch(dto, feed);

        feed.setCategory(category);

        return feedMapping.map(feedRepository.save(feed));
    }

    @Override
    public void deleteFeedById(Long id) {

        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new NotFindFeedException(id));

        feedRepository.delete(feed);
    }

    @Override
    public ResponseWrapperFeeds getAllFeeds() {

        Page<Feed> result = feedRepository.findAll(PAGEABLE);
        List<FeedDto> dtoList = feedRepository
                .findAll(PAGEABLE).stream()
                .map(feedMapping::map)
                .toList();

        return new ResponseWrapperFeeds(result.getTotalElements(), dtoList);
    }

    @Override
    public ResponseWrapperFeeds findByNewsCategory(String newsCategory) {

        Category category = findCategoryByNewsCategory(newsCategory);

        CategoryDto dto = categoryMapping.map(category);

        Page<Feed> result = feedRepository.findAll(PAGEABLE);
        List<FeedDto> dtoList = feedRepository
                .findByCategoryId(dto.getId(), PAGEABLE).stream()
                .map(feedMapping::map)
                .toList();

        return new ResponseWrapperFeeds(result.getTotalElements(), dtoList);
    }

    @Override
    public ResponseWrapperFeeds findByTitleFeed(String title) {

        Page<Feed> result = feedRepository.findAll(PAGEABLE);
        List<FeedDto> dtoList = feedRepository
                .findByTitleContainingIgnoreCase(title, PAGEABLE).stream()
                .map(feedMapping::map)
                .toList();

        return new ResponseWrapperFeeds(result.getTotalElements(), dtoList);
    }

    @Override
    public ResponseWrapperFeeds findByContentFeed(String content) {

        Page<Feed> result = feedRepository.findAll(PAGEABLE);
        List<FeedDto> dtoList = feedRepository
                .findByContentContainingIgnoreCase(content, PAGEABLE).stream()
                .map(feedMapping::map)
                .toList();

        return new ResponseWrapperFeeds(result.getTotalElements(), dtoList);
    }

    private void checkCategory(String newsCategory, Category category) {

        if (category == null) {
            throw new NotFindNewsCategoryException(newsCategory);
        }
    }

    private Category findCategoryByNewsCategory(String newsCategory) {

        Category entity = categoryRepository
                .findByNewsCategoryContainsIgnoreCase(newsCategory);
        checkCategory(newsCategory, entity);

        return entity;
    }

    private void checkValidate(Object object) {

        if (!validationService.validate(object)) {
            throw new ValidationException(object.toString());
        }
    }
}
