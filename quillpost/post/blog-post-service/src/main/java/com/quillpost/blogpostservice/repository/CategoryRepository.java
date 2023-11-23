package com.quillpost.blogpostservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quillpost.blogpostservice.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}
