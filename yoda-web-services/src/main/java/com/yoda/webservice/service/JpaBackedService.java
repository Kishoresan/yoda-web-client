package com.yoda.webservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.yoda.webservice.dto.Dto;

@Component
public interface JpaBackedService <T extends Dto<E>, E, I> {

	public List<T> findAll();
	
	public Optional<T> findById(I id);
	
	public T save(T dto);

	public T update(T dto);
	
	public void delete(I id);
}