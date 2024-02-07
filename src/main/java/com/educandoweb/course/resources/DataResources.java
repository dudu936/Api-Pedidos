package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.Identifiable;
import com.educandoweb.course.services.DataService;

@Controller
public abstract class DataResources<T extends Identifiable, ID> {
	protected DataService<T, ID> service;
	
	public DataResources(DataService<T, ID> service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<T>> getAll(){
		List<T> response = service.findAll();
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<T> getById(@PathVariable ID id){
		T response = service.findById(id);
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<T> post(@RequestBody T requestBody){
		T response = service.insert(requestBody);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(requestBody.getId()).toUri();
		return ResponseEntity.created(location).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable ID id){
		service.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<T> put(@PathVariable ID id, @RequestBody T requestBody){
		T response = service.update(id, requestBody);
		return ResponseEntity.ok().body(response);
	}
}
