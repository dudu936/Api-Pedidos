package com.educandoweb.course.services.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.CategoryRepository;

@Service
public class ProductValidate {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Boolean categoryIsValid(Product product) {
		List<Long> listId = new ArrayList<>();
		product.getCategories().forEach(category -> listId.add(category.getId()));
		if(listId.isEmpty()) return false;
		for(Long id: listId) {
			if(!categoryRepository.existsById(id)) return false;
		}
		return true;
	}
	
}
