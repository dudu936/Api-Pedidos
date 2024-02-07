package com.educandoweb.course.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

@RestController
@RequestMapping(value="orders")
public class OrderResource extends DataResources<Order, Long>{

	public OrderResource(OrderService service) {
		super(service);
	}
	
}
