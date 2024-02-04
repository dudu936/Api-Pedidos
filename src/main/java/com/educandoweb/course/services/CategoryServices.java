package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

@Service
public class CategoryServices {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
	
	public Category insert(Category category) {
		return repository.save(category);
	}
	
	public void remove(Long id) {
		repository.deleteById(id);
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
