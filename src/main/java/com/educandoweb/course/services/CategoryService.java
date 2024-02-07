package com.educandoweb.course.services;

import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

@Service
public class CategoryService extends DataService<Category, Long> {
	
	public CategoryService(CategoryRepository repository) {
		super(repository);
	}
	
	public Category update(Long id, Category category) {
		Category entity = repository.getReferenceById(id);
		entity = updateCategory(entity, category);
		return repository.save(entity);
	}

	private Category updateCategory(Category entity, Category category) {
		entity.setName(category.getName());
		return entity;
	}
}
