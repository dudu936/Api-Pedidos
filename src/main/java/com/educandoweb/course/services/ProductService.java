package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exeptions.ConstraintException;
import com.educandoweb.course.services.exeptions.DatabaseException;
import com.educandoweb.course.services.exeptions.IllegalArgument;
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

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundExeption(id));
	}

	public Product insert(Product product) {
		try {
			if (validate.categoryIsValid(product)) {
				Product entity = addCategories(product);
				return repository.save(entity);
			} else {
				throw new ConstraintException("There are categories that do not exist, send a valid category.");
			}
		} catch (ConstraintViolationException e) {
			throw new ConstraintException(
					"Required parameters are missing {name, description, price}\n" + e.getMessage());
		} catch (InvalidDataAccessApiUsageException e) {
			throw new IllegalArgument();
		}
	}

	public void remove(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Cannot remove used data.\n" + e.getMessage());
		}
	}

	public Product update(Long id, Product product) {
		try {
			Product entity = repository.getReferenceById(id);
			entity = updateProduct(entity, product);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExeption(id);

		} catch (TransactionSystemException e) {
			throw new ConstraintException(
					"Required parameters are missing {name, description, price}\n" + e.getMessage());
		}
	}

	private Product updateProduct(Product entity, Product product) {
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setPrice(product.getPrice());
		if (product.getImgUrl() != null) {
			entity.setImgUrl(product.getImgUrl());
		}
		if (!product.getCategories().isEmpty()) {
			try {
				if (validate.categoryIsValid(product)) {
					for (Category category : product) {
						if (!entity.getCategories().contains(category)) {
							entity.addCategory(category);
						}
					}
				}
			} catch (NullPointerException e) {
				throw new ConstraintException(
						"There are categories that do not exist, send a valid category.\n" + e.getMessage());
			}
		}
		return entity;
	}

	private Product addCategories(Product product) {
		Product entity = new Product.Builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).imgUrl(product.getImgUrl()).Build();
		for (Category category : product) {
			entity.addCategory(service.findById(category.getId()));
		}
		return entity;
	}
}
