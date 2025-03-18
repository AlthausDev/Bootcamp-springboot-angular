

package com.althaus.gemini.bootcamp.domains.contracts.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import com.althaus.gemini.bootcamp.domains.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class CategoryRepositoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryRepositoryTest categoryRepositoryTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllByOrderByName() {
        Category category1 = new Category();
        category1.setName("Category A");
        Category category2 = new Category();
        category2.setName("Category B");

        when(categoryRepository.findAllByOrderByName()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryRepository.findAllByOrderByName();
        assertEquals(2, categories.size());
        assertEquals("Category A", categories.get(0).getName());
        assertEquals("Category B", categories.get(1).getName());
    }

    @Test
    public void testFindByLastUpdateGreaterThanEqualOrderByLastUpdate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Category category1 = new Category();
        category1.setLastUpdate(timestamp);
        Category category2 = new Category();
        category2.setLastUpdate(timestamp);

        when(categoryRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(timestamp))
                .thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(timestamp);
        assertEquals(2, categories.size());
        assertEquals(timestamp, categories.get(0).getLastUpdate());
        assertEquals(timestamp, categories.get(1).getLastUpdate());
    }

    @Test
    public void testFindByCategoryIdGreaterThan() {
        Category category1 = new Category();
        category1.setCategoryId(2);
        Category category2 = new Category();
        category2.setCategoryId(3);

        when(categoryRepository.findByCategoryIdGreaterThan(1)).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryRepository.findByCategoryIdGreaterThan(1);
        assertEquals(2, categories.size());
        assertEquals(2, categories.get(0).getCategoryId());
        assertEquals(3, categories.get(1).getCategoryId());
    }
}