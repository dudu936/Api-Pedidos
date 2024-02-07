package com.educandoweb.course.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;

import com.educandoweb.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource extends DataResources<Product, Long> {

	public ProductResource(ProductService service) {
		super(service);
	}

}
