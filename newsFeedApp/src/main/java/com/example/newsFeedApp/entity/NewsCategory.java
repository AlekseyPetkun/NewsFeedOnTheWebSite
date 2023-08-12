package com.example.newsFeedApp.entity;

import com.example.newsFeedApp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Категория новости
 */
public enum NewsCategory {

    POLITICAL("Политика"),
    ECONOMIC("Экономика"),
    SPORTS("Спорт"),
    SOCIAL("Социальная"),
    SCIENTIFIC("Научная");

    private final String translation;

    NewsCategory(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
