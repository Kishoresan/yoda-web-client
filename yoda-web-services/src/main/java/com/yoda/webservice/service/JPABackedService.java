package com.yoda.webservice.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.yoda.webservice.dto.DTO;

@Component
public interface JPABackedService <T extends DTO<E>, E> {

	public Optional<T> findById(UUID id);
	
	public T save(T mediaFile);

	public T update(T mediaFile);
	
	public void delete(UUID id);
}