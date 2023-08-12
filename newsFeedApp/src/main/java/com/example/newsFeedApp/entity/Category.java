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
    private String newsCategory;

    /**
     * Новости категории
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Feed> feeds;
}
