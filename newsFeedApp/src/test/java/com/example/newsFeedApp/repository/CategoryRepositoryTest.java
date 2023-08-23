package com.example.newsFeedApp.repository;

import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.Feed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void contextLoads() {
        assertNotNull(entityManager);
    }

    @Test
    void verifyRepositoryByPersistingAnCategory() {

        Category category = new Category();
        category.setNewsCategory("CATEGORY");

        assertNull(category.getId());
        categoryRepository.save(category);
        assertNotNull(category.getId());
    }

    @Test
    public void testFindByContentContainingIgnoreCase() {

        Category category1 = new Category();
        category1.setNewsCategory("1CATEGORY");

        Category category2 = new Category();
        category2.setNewsCategory("2CATEGORY");

        Category category3 = new Category();
        category3.setNewsCategory("3CATEGORY");

        assertNull(category1.getId());
        categoryRepository.save(category1);
        assertNotNull(category1.getId());

        assertNull(category2.getId());
        categoryRepository.save(category2);
        assertNotNull(category2.getId());

        assertNull(category3.getId());
        categoryRepository.save(category3);
        assertNotNull(category3.getId());


        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.persist(category3);
        entityManager.flush();

        Category result1 = categoryRepository.findByNewsCategoryContainsIgnoreCase("1CATEGORY");
        Category result2 = categoryRepository.findByNewsCategoryContainsIgnoreCase("2CATEGORY");
        Category result3 = categoryRepository.findByNewsCategoryContainsIgnoreCase("3CATEGORY");

        assertEquals("1CATEGORY", result1.getNewsCategory());
        assertEquals("2CATEGORY", result2.getNewsCategory());
        assertEquals("3CATEGORY", result3.getNewsCategory());
    }

    @Test
    public void testFindAll() {

        Category category1 = new Category();
        category1.setNewsCategory("1CATEGORY");

        Category category2 = new Category();
        category2.setNewsCategory("2CATEGORY");

        Category category3 = new Category();
        category3.setNewsCategory("3CATEGORY");

        assertNull(category1.getId());
        categoryRepository.save(category1);
        assertNotNull(category1.getId());

        assertNull(category2.getId());
        categoryRepository.save(category2);
        assertNotNull(category2.getId());

        assertNull(category3.getId());
        categoryRepository.save(category3);
        assertNotNull(category3.getId());


        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.persist(category3);
        entityManager.flush();

        Page<Category> result = categoryRepository.findAll(
                PageRequest.of(0, 10, Sort.by("newsCategory")
                .ascending()));
        assertEquals(7, result.getTotalElements());
    }
}