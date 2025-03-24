package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.althaus.gemini.bootcamp.domains.core.contracts.repositories.CoreRepository;
import com.althaus.gemini.bootcamp.domains.entities.Category;

@RepositoryRestResource(exported = false)
public interface CategoryRepository extends CoreRepository<Category, Integer> {
    List<Category> findAllByOrderByName();
	List<Category> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
    List<Category> findByCategoryIdGreaterThan(int categoryId);    
}
