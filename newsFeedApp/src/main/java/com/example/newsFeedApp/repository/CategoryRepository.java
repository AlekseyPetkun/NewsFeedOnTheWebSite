package com.example.newsFeedApp.repository;

import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNewsCategory(NewsCategory newsCategory);
}
