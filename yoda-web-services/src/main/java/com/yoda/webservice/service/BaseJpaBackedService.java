package com.yoda.webservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yoda.webservice.dto.Dto;

public abstract class BaseJpaBackedService<D extends Dto<E>, E, I> implements JpaBackedService<D, E, I> {

	@Override
	public List<D> findAll() {

		List<E> entities = getJpaRepository().findAll();

		return entities.parallelStream().map(entity -> buildDto(entity)).collect(Collectors.toList());
	}

	@Override
	public Optional<D> findById(I id) {
		return extractDtoFromOptional(getJpaRepository().findById(id));
	}

	protected Optional<D> extractDtoFromOptional(Optional<E> foundEntity) {

		if (foundEntity.isPresent()) {

			return Optional.<D>of(buildDto(foundEntity.get()));
		}

		return Optional.<D>empty();
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
	public void delete(I id) {
		getJpaRepository().deleteById(id);
	}

	protected abstract D buildDto(E entity);

	protected abstract JpaRepository<E, I> getJpaRepository();

}