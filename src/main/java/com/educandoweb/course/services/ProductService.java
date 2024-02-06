package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exeptions.ConstraintException;
import com.educandoweb.course.services.exeptions.DatabaseException;
import com.educandoweb.course.services.exeptions.ResourceNotFoundExeption;
import com.educandoweb.course.services.validate.ProductValidate;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

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
		try {
			if(validate.categoryIsValid(product)) {
				Product entity = addCategories(product);
				return repository.save(entity);
			}
			else {
				throw new ConstraintException("There are categories that do not exist.");
			}
		}catch(ConstraintViolationException e) {
			throw new ConstraintException("Required parameters are missing {name, description, price}");
		}
	}
	
	public void remove(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Cannot remove used data.");
		}
	}
	
	public Product update(Long id, Product product) {
		try {
			Product entity = repository.getReferenceById(id);
			entity = updateProduct(entity, product);
			return repository.save(entity); 
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundExeption(id);
			
		}catch(TransactionSystemException e) {
			throw new ConstraintException("Required parameters are missing {name, description, price}");
			
		}
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
