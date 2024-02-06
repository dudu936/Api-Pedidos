package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exeptions.ResourceNotFoundExeption;
import com.educandoweb.course.services.validate.ProductValidate;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryServices service;
	@Autowired
	private ProductValidate validate;

	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id){
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundExeption(id));
	}
	
	public Product insert(Product product){
		if(validate.categoryIsValid(product)) {
			Product entity = addCategories(product);
			return repository.save(entity);
		}
		return null;
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
	
	private Product addCategories(Product product) {
		Product entity = new Product.Builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.imgUrl(product.getImgUrl())
				.Build();
		for (Category category: product.getCategories()) {
			entity.addCategory(service.findById(category.getId()));
		}
		return entity;
	}
}
