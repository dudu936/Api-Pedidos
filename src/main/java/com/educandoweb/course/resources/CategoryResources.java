package com.educandoweb.course.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryResources extends DataResources<Category, Long> {

	public CategoryResources(CategoryService service) {
		super(service);
	}

}
