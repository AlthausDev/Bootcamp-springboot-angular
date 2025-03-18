package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import com.althaus.gemini.bootcamp.domains.contracts.repositories.CoreRepository;


public class CoreServiceImplTest {

    @Mock
    private CoreRepository<TestEntity, Integer> repository;

    @InjectMocks
    private CoreServiceImpl<TestEntity> coreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        TestEntity entity = new TestEntity();
        when(repository.save(entity)).thenReturn(entity);

        TestEntity result = coreService.create(entity);

        assertNotNull(result);
        assertEquals(entity, result);
        verify(repository, times(1)).save(entity);
    }

    @Test
    public void testRead() {
        TestEntity entity = new TestEntity();
        when(repository.findById(1)).thenReturn(Optional.of(entity));

        Optional<TestEntity> result = coreService.read(1);

        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
        verify(repository, times(1)).findById(1);
    }

    @Test
    public void testReadAllList() {
        TestEntity entity1 = new TestEntity();
        TestEntity entity2 = new TestEntity();
        List<TestEntity> entities = Arrays.asList(entity1, entity2);
        when(repository.findAll()).thenReturn(entities);

        List<TestEntity> result = coreService.readAllList();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testUpdate() {
        TestEntity entity = new TestEntity();
        when(repository.saveAndFlush(entity)).thenReturn(entity);

        TestEntity result = coreService.update(entity);

        assertNotNull(result);
        assertEquals(entity, result);
        verify(repository, times(1)).saveAndFlush(entity);
    }

    @Test
    public void testDelete() {
        TestEntity entity = new TestEntity();

        coreService.delete(entity);

        verify(repository, times(1)).delete(entity);
    }

    @Test
    public void testDeleteById() {
        coreService.deleteById(1);

        verify(repository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteAll() {
        TestEntity entity1 = new TestEntity();
        TestEntity entity2 = new TestEntity();
        List<TestEntity> entities = Arrays.asList(entity1, entity2);

        coreService.deleteAll(entities);

        verify(repository, times(1)).deleteAll(entities);
    }

    // Dummy entity class for testing purposes
    private static class TestEntity {
    }
}