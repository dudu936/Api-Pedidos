package com.educandoweb.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exeptions.ConstraintException;
import com.educandoweb.course.services.exeptions.IllegalArgument;
import com.educandoweb.course.services.exeptions.ResourceNotFoundExeption;
import com.educandoweb.course.services.validate.ProductUpdater;
import com.educandoweb.course.services.validate.ProductValidate;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@Service
public class ProductService extends DataService<Product, Long> {

	public ProductService(ProductRepository repository) {
		super(repository);
	}

	@Autowired
	private ProductValidate validate;
	@Autowired
	private ProductUpdater updater;

	@Override
	public Product insert(Product product) {
		try {
			if (validate.categoryIsValid(product)) {
				product = validate.addValidCategories(product);
				return super.repository.save(product);
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

	public Product update(Long id, Product product) {
		try {
			Product entity = repository.getReferenceById(id);
			entity = updater.updateProduct(entity, product);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExeption(id);

		} catch (TransactionSystemException e) {
			throw new ConstraintException(
					"Required parameters are missing {name, description, price}\n" + e.getMessage());
		} catch (InvalidDataAccessApiUsageException e) {
			throw new IllegalArgument();
		}
	}

}
