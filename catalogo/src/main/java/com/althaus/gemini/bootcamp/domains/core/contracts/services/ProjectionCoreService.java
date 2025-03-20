package com.althaus.gemini.bootcamp.domains.core.contracts.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface ProjectionCoreService<T, K> extends PagingAndSortingCoreService<T, K> {
	<U> List<U> getByProjection(Class<U> type);
	<U> Iterable<U> getByProjection(Sort sort, Class<U> type);
	<U> Page<U> getByProjection(Pageable pageable, Class<U> type);
}