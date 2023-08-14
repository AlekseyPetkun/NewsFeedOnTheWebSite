package com.example.newsFeedApp.service.impl;

import com.example.newsFeedApp.dto.CreateCategoryDto;
import com.example.newsFeedApp.dto.CreateFeedDto;
import com.example.newsFeedApp.dto.UpdateFeedDto;
import com.example.newsFeedApp.service.ValidationService;
import org.springframework.stereotype.Service;

/**
 * Бизнес-логика по работе с валидностью
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(Object object) {

        if (object instanceof CreateFeedDto) {
            return ((CreateFeedDto) object).getTitle() != null
                    && ((CreateFeedDto) object).getContent() != null
                    && ((CreateFeedDto) object).getNewsCategory() != null;

        } else if (object instanceof CreateCategoryDto) {
            return ((CreateCategoryDto) object).getNewsCategory() != null;

        } else if (object instanceof UpdateFeedDto) {
            return ((UpdateFeedDto) object).getNewsCategory() != null;
        }

        return false;
    }
}
