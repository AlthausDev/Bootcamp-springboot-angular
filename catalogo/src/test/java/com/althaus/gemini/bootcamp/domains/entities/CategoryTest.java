package com.althaus.gemini.bootcamp.domains.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Timestamp;

public class CategoryTest {

    @Test
    public void testCategoryConstructor() {
        Category category = new Category(1);
        assertEquals(1, category.getCategoryId());
    }

    @Test
    public void testSetAndGetCategoryId() {
        Category category = new Category();
        category.setCategoryId(2);
        assertEquals(2, category.getCategoryId());
    }

    @Test
    public void testSetAndGetLastUpdate() {
        Category category = new Category();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        category.setLastUpdate(timestamp);
        assertEquals(timestamp, category.getLastUpdate());
    }

    @Test
    public void testSetAndGetName() {
        Category category = new Category();
        category.setName("Action");
        assertEquals("Action", category.getName());
    }
}