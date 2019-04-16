package com.yoda.webservice.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.yoda.webservice.dto.Dto;

@Component
public interface JpaBackedService <T extends Dto<E>, E> {

	public Optional<T> findById(UUID id);
	
	public T save(T dto);

	public T update(T dto);
	
	public void delete(UUID id);
}