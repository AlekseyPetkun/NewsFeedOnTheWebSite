package com.example.newsFeedApp.repository;

import com.example.newsFeedApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNewsCategoryContainsIgnoreCase(String newsCategory);
}
