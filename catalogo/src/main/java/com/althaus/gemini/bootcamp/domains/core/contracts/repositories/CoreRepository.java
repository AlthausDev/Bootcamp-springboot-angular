package com.althaus.gemini.bootcamp.domains.core.contracts.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface CoreRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    <U> List<U> findAllBy(Class<U> tipo);
	<U> List<U> findAllBy(Sort orden, Class<U> tipo);
	<U> Page<U> findAllBy(Pageable page, Class<U> tipo);
}