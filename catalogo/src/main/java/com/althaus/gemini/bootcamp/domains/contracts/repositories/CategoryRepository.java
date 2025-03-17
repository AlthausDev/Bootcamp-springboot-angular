package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import java.sql.Timestamp;
import java.util.List;

import com.althaus.gemini.bootcamp.domains.entities.Category;

public interface CategoryRepository extends CoreRepository<Category, Integer> {
    List<Category> findAllByOrderByName();
	List<Category> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
    List<Category> findByCategoryIdGreaterThan(int categoryId);    
}
