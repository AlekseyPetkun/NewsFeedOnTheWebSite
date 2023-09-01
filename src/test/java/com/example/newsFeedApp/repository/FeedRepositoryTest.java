package com.example.newsFeedApp.repository;

import com.example.newsFeedApp.entity.Category;
import com.example.newsFeedApp.entity.Feed;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class FeedRepositoryTest {

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
    void verifyRepositoryByPersistingAnFeed() {

        Category category1 = categoryRepository.findById(1L).orElseThrow();

        assertNotNull(category1.getId());

        Feed feed1 = new Feed();
        feed1.setTitle("Title 1");
        feed1.setContent("Content 1");
        feed1.setDateTime(LocalDateTime.now());
        feed1.setCategory(category1);

        assertNull(feed1.getId());
        feedRepository.save(feed1);
        assertNotNull(feed1.getId());
    }

    @Test
    public void testFindByCategoryId() {

        Category category1 = categoryRepository.findById(4L).orElseThrow();
        Category category2 = categoryRepository.findById(2L).orElseThrow();
        Category category3 = categoryRepository.findById(4L).orElseThrow();

        Feed feed1 = new Feed();
        feed1.setTitle("1Title 1");
        feed1.setContent("Content 1");
        feed1.setDateTime(LocalDateTime.now());
        feed1.setCategory(category1);

        Feed feed2 = new Feed();
        feed2.setTitle("1Title 2");
        feed2.setContent("Content 2");
        feed2.setDateTime(LocalDateTime.now());
        feed2.setCategory(category2);

        Feed feed3 = new Feed();
        feed3.setTitle("1Title 3");
        feed3.setContent("Content 3");
        feed3.setDateTime(LocalDateTime.now());
        feed3.setCategory(category3);

        assertNull(feed1.getId());
        feedRepository.save(feed1);
        assertNotNull(feed1.getId());

        assertNull(feed2.getId());
        feedRepository.save(feed2);
        assertNotNull(feed2.getId());

        assertNull(feed3.getId());
        feedRepository.save(feed3);
        assertNotNull(feed3.getId());


        entityManager.persist(feed1);
        entityManager.persist(feed2);
        entityManager.persist(feed3);
        entityManager.flush();

        List<Feed> result = feedRepository.findByCategoryId(4L,
                PageRequest.of(0, 10, Sort.by("title")
                .ascending()));
        assertEquals(2, result.size());
        assertEquals(feed1, result.get(0));
        assertEquals(feed3, result.get(1));
    }

    @Test
    public void testFindByTitleContainingIgnoreCase() {

        Category category1 = categoryRepository.findById(4L).orElseThrow();
        Category category2 = categoryRepository.findById(2L).orElseThrow();
        Category category3 = categoryRepository.findById(4L).orElseThrow();

        Feed feed1 = new Feed();
        feed1.setTitle("1Title 1");
        feed1.setContent("Content 1");
        feed1.setDateTime(LocalDateTime.now());
        feed1.setCategory(category1);

        Feed feed2 = new Feed();
        feed2.setTitle("1Title 2");
        feed2.setContent("Content 2");
        feed2.setDateTime(LocalDateTime.now());
        feed2.setCategory(category2);

        Feed feed3 = new Feed();
        feed3.setTitle("1Title 3");
        feed3.setContent("Content 3");
        feed3.setDateTime(LocalDateTime.now());
        feed3.setCategory(category3);

        assertNull(feed1.getId());
        feedRepository.save(feed1);
        assertNotNull(feed1.getId());

        assertNull(feed2.getId());
        feedRepository.save(feed2);
        assertNotNull(feed2.getId());

        assertNull(feed3.getId());
        feedRepository.save(feed3);
        assertNotNull(feed3.getId());


        entityManager.persist(feed1);
        entityManager.persist(feed2);
        entityManager.persist(feed3);
        entityManager.flush();

        List<Feed> result = feedRepository.findByTitleContainingIgnoreCase("title",
                PageRequest.of(0, 10, Sort.by("title")
                .ascending()));
        assertEquals(3, result.size());
        assertEquals(feed1, result.get(0));
        assertEquals(feed2, result.get(1));
        assertEquals(feed3, result.get(2));
    }

    @Test
    public void testFindByContentContainingIgnoreCase() {

        Category category1 = categoryRepository.findById(4L).orElseThrow();
        Category category2 = categoryRepository.findById(2L).orElseThrow();
        Category category3 = categoryRepository.findById(4L).orElseThrow();

        Feed feed1 = new Feed();
        feed1.setTitle("1Title 1");
        feed1.setContent("Content 1");
        feed1.setDateTime(LocalDateTime.now());
        feed1.setCategory(category1);

        Feed feed2 = new Feed();
        feed2.setTitle("1Title 2");
        feed2.setContent("Content 2");
        feed2.setDateTime(LocalDateTime.now());
        feed2.setCategory(category2);

        Feed feed3 = new Feed();
        feed3.setTitle("1Title 3");
        feed3.setContent("Content 3");
        feed3.setDateTime(LocalDateTime.now());
        feed3.setCategory(category3);

        assertNull(feed1.getId());
        feedRepository.save(feed1);
        assertNotNull(feed1.getId());

        assertNull(feed2.getId());
        feedRepository.save(feed2);
        assertNotNull(feed2.getId());

        assertNull(feed3.getId());
        feedRepository.save(feed3);
        assertNotNull(feed3.getId());


        entityManager.persist(feed1);
        entityManager.persist(feed2);
        entityManager.persist(feed3);
        entityManager.flush();

        List<Feed> result = feedRepository.findByContentContainingIgnoreCase("content",
                PageRequest.of(0, 10, Sort.by("title")
                .ascending()));
        assertEquals(3, result.size());
        assertEquals(feed1, result.get(0));
        assertEquals(feed2, result.get(1));
        assertEquals(feed3, result.get(2));
    }

    @Test
    public void testFindAll() {

        Category category1 = categoryRepository.findById(4L).orElseThrow();
        Category category2 = categoryRepository.findById(2L).orElseThrow();
        Category category3 = categoryRepository.findById(4L).orElseThrow();

        Feed feed1 = new Feed();
        feed1.setTitle("1Title 1");
        feed1.setContent("Content 1");
        feed1.setDateTime(LocalDateTime.now());
        feed1.setCategory(category1);

        Feed feed2 = new Feed();
        feed2.setTitle("1Title 2");
        feed2.setContent("Content 2");
        feed2.setDateTime(LocalDateTime.now());
        feed2.setCategory(category2);

        Feed feed3 = new Feed();
        feed3.setTitle("1Title 3");
        feed3.setContent("Content 3");
        feed3.setDateTime(LocalDateTime.now());
        feed3.setCategory(category3);

        assertNull(feed1.getId());
        feedRepository.save(feed1);
        assertNotNull(feed1.getId());

        assertNull(feed2.getId());
        feedRepository.save(feed2);
        assertNotNull(feed2.getId());

        assertNull(feed3.getId());
        feedRepository.save(feed3);
        assertNotNull(feed3.getId());


        entityManager.persist(feed1);
        entityManager.persist(feed2);
        entityManager.persist(feed3);
        entityManager.flush();

        Page<Feed> result = feedRepository.findAll(PageRequest.of(0, 10, Sort.by("title")
                .ascending()));
        assertEquals(28, result.getTotalElements());
    }
}