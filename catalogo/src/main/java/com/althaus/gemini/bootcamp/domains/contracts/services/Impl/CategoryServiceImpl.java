package com.althaus.gemini.bootcamp.domains.contracts.services.Impl;

import org.springframework.stereotype.Service;

import com.althaus.gemini.bootcamp.domains.contracts.repositories.CategoryRepository;
import com.althaus.gemini.bootcamp.domains.contracts.services.CategoryService;
import com.althaus.gemini.bootcamp.domains.core.contracts.services.Impl.CoreServiceImpl;
import com.althaus.gemini.bootcamp.domains.entities.Category;

@Service
public class CategoryServiceImpl extends CoreServiceImpl<Category, Integer> implements CategoryService {

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super(categoryRepository);
	}
}
