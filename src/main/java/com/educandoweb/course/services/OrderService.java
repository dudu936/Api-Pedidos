package com.educandoweb.course.services;

import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

@Service
public class OrderService extends DataService<Order, Long> {

	public OrderService(OrderRepository repository) {
		super(repository);

	}

	@Override
	public Order update(Long id, Order entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
