package com.quillpost.blogpostservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.quillpost.blogpostservice.models.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {
	
}
