package com.althaus.gemini.bootcamp.domains.core.contracts.services.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.althaus.gemini.bootcamp.domains.core.contracts.repositories.CoreRepository;

class CoreServiceImplTest<T, K extends Serializable> {

    @Mock
    private CoreRepository<T, K> repository;

    @InjectMocks
    private CoreServiceImpl<T, K> coreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        T entity = (T) mock(Object.class);
        when(repository.save(entity)).thenReturn(entity);

        T result = coreService.create(entity);

        assertNotNull(result);
        assertEquals(entity, result);
        verify(repository, times(1)).save(entity);
    }

    @Test
    void testRead() {
        K id = (K) mock(Serializable.class);
        T entity = (T) mock(Object.class);
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        Optional<T> result = coreService.read(id);

        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testReadAllList() {
        List<T> entities = Arrays.asList((T) mock(Object.class), (T) mock(Object.class));
        when(repository.findAll()).thenReturn(entities);

        List<T> result = coreService.readAllList();

        assertNotNull(result);
        assertEquals(entities.size(), result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetAllWithSort() {
        Sort sort = mock(Sort.class);
        List<T> entities = (List<T>) Arrays.asList(mock(Object.class), mock(Object.class));
        when(repository.findAll(sort)).thenReturn(entities);

        Iterable<T> result = coreService.getAll(sort);

        assertNotNull(result);
        verify(repository, times(1)).findAll(sort);
    }

    @Test
    void testGetAllWithPageable() {
        Pageable pageable = mock(Pageable.class);
        Page<T> page = new PageImpl<T>((List<T>) Arrays.asList(mock(Object.class), mock(Object.class)));
        when(repository.findAll(pageable)).thenReturn(page);

        Page<T> result = coreService.getAll(pageable);

        assertNotNull(result);
        assertEquals(page.getTotalElements(), result.getTotalElements());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void testUpdate() {
        T entity = (T) mock(Object.class);
        when(repository.saveAndFlush(entity)).thenReturn(entity);

        T result = coreService.update(entity);

        assertNotNull(result);
        assertEquals(entity, result);
        verify(repository, times(1)).saveAndFlush(entity);
    }

    @Test
    void testDelete() {
        T entity = (T) mock(Object.class);

        coreService.delete(entity);

        verify(repository, times(1)).delete(entity);
    }

    @Test
    void testDeleteById() {
        K id = (K) mock(Serializable.class);

        coreService.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteAll() {
        List<T> entities = (List<T>) Arrays.asList(mock(Object.class), mock(Object.class));

        coreService.deleteAll(entities);

        verify(repository, times(1)).deleteAll(entities);
    }

    @Test
    void testGetByProjection() {
        Class<Object> type = Object.class;
        List<Object> projections = Arrays.asList(mock(Object.class), mock(Object.class));
        when(repository.findAllBy(type)).thenReturn(projections);

        List<Object> result = coreService.getByProjection(type);

        assertNotNull(result);
        assertEquals(projections.size(), result.size());
        verify(repository, times(1)).findAllBy(type);
    }

    @Test
    void testGetByProjectionWithSort() {
        Sort sort = mock(Sort.class);
        Class<Object> type = Object.class;
        List<Object> projections = Arrays.asList(mock(Object.class), mock(Object.class));
        when(repository.findAllBy(sort, type)).thenReturn(projections);

        Iterable<Object> result = coreService.getByProjection(sort, type);

        assertNotNull(result);
        verify(repository, times(1)).findAllBy(sort, type);
    }

    @Test
    void testGetByProjectionWithPageable() {
        Pageable pageable = mock(Pageable.class);
        Class<Object> type = Object.class;
        Page<Object> page = new PageImpl<>(Arrays.asList(mock(Object.class), mock(Object.class)));
        when(repository.findAllBy(pageable, type)).thenReturn(page);

        Page<Object> result = coreService.getByProjection(pageable, type);

        assertNotNull(result);
        assertEquals(page.getTotalElements(), result.getTotalElements());
        verify(repository, times(1)).findAllBy(pageable, type);
    }
}