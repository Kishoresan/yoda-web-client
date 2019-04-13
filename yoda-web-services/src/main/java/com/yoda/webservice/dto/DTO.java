package com.yoda.webservice.dto;

import java.io.Serializable;

public interface DTO<E> extends Serializable{

	public E createJPAEntity();
	
	public void synchronizeWithEntity(E entity);
}
