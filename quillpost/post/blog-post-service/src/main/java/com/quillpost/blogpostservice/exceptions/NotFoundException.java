package com.quillpost.blogpostservice.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	long fieldValue;
	public NotFoundException(String resourceName, String fieldName, Integer fieldValue){
		super(String.format("%s not found with %s : %s ", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public NotFoundException(String resourceName, String fieldName, Long fieldValue){
		super(String.format("%s not found with %s : %s ", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
