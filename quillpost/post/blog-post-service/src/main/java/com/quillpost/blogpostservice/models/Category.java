package com.quillpost.blogpostservice.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Category {
	
	@Id
	private String categoryName;
	
	
	
	public Category() {
		
	}
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategory() {
		return categoryName;
	}
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<PostItem> posts;
	

}
