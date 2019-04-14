package com.yoda.webservice.dto;

import java.io.Serializable;

public interface Dto<E> extends Serializable{

	public E createJPAEntity();
	
	public void synchronizeWithEntity(E entity);
}