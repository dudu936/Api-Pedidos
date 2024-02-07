package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.services.exeptions.ConstraintException;
import com.educandoweb.course.services.exeptions.DatabaseException;
import com.educandoweb.course.services.exeptions.ResourceNotFoundExeption;

import jakarta.validation.ConstraintViolationException;

public abstract class DataService<T, ID> {
	protected JpaRepository<T, ID> repository;
	
	public DataService(JpaRepository<T, ID> repository) {
		this.repository = repository;
	}

	public List<T> findAll() {
		return repository.findAll();
	}
	
	public T findById(ID id) {
		Optional<T> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundExeption(id));
	}
	
	public T insert(T entity) {
		try {
			return repository.save(entity);
		}catch(ConstraintViolationException e) {
			throw new ConstraintException(e.getMessage());
		}
	}
	
	public void remove(ID id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Cannot remove used data.\n" + e.getMessage());
		}
	}
}
