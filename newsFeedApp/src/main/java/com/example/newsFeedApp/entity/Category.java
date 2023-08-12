package com.example.newsFeedApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Сущность категория
 */
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    /**
     * Идентификатор категории
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название категории
     */
    @Column(name = "news_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private NewsCategory newsCategory;

    /**
     * Новости категории
     */
    @OneToMany(fetch = FetchType.LAZY, //mappedBy = "Feed",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Feed> feeds;
}
