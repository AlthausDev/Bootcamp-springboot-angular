package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface CoreRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}