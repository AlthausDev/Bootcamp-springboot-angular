package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.althaus.gemini.bootcamp.domains.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
