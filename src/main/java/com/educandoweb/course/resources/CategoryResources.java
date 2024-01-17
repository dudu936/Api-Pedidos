package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryServices;

@RestController
public class CategoryResources {

	@Autowired
	private CategoryServices services;

	@GetMapping(value = "/categories")
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok().body(services.findAll());
	}
	
	@GetMapping(value = "/Category/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = services.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
