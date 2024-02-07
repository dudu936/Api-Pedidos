package com.educandoweb.course.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource extends DataResources<User, Long> {

	public UserResource(UserService service) {
		super(service);
	}

}
