package com.yoda.webservice.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yoda.webservice.dto.DTO;

public abstract class BaseJPABackedService<D extends DTO<E>, E> implements JPABackedService<D, E> {
	
	@Override
	public Optional<D> findById(UUID id) {
		
		Optional<E> foundEntity = getJpaRepository().findById(id);

		if (foundEntity.isPresent()) {

			return Optional.<D> of(buildDto(foundEntity.get()));
		}

		return Optional.<D> empty();
	}
	
	@Override
	public D save(D dto) {
		
		E createdEntity = getJpaRepository().saveAndFlush(dto.createJPAEntity());
		dto.synchronizeWithEntity(createdEntity);

		return dto;
	}
	
	@Override
	public D update(D dto) {
		return save(dto);
	}

	@Override
	public void delete(UUID id) {
		getJpaRepository().deleteById(id);
	}
	
	protected abstract D buildDto(E entity);
	
	protected abstract JpaRepository<E, UUID> getJpaRepository();
	
}