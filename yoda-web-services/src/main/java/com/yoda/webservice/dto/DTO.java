package com.yoda.webservice.dto;

public interface DTO<E> {

	public E createJPAEntity();
	
	public void synchronizeWithEntity(E entity);
}
