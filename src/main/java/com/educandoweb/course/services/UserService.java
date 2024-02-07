package com.educandoweb.course.services;

import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service
public class UserService extends DataService<User, Long> {


	public UserService(UserRepository repository) {
		super(repository);
	}

	public User update(Long id, User user) {
		User entity = super.repository.getReferenceById(id);
		entity = updateUser(entity, user);
		return repository.save(entity);
	}

	private User updateUser(User entity, User user) {
		entity.setEmail(user.getEmail());
		entity.setName(user.getName());
		entity.setPhone(user.getPhone());
		return entity;
	}
}
