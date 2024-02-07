package com.educandoweb.course.services.validate;

import org.springframework.beans.factory.annotation.Autowired;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.exeptions.ConstraintException;

public class ProductUpdater {
	@Autowired
	private ProductValidate validate;
	
	public final Product updateProduct(Product entity, Product product) {
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
}
