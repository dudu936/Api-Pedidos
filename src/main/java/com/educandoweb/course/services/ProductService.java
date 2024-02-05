package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id){
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
	public Product insert(Product product) {
		return repository.save(product);
	}
	
	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	public Product update(Long id, Product product) {
		Product entity = repository.getReferenceById(id);
		entity = updateProduct(entity, product);
		return repository.save(entity);
	}

	private Product updateProduct(Product entity, Product product) {
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setPrice(product.getPrice());
		entity.setImgUrl(product.getImgUrl());
		return entity;
	}
}
