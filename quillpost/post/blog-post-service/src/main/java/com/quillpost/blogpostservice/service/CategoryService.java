package com.quillpost.blogpostservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Iterable<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	public Category getCategoryByName(String categoryName) throws NotFoundException {
		return categoryRepository.findById(categoryName).orElseThrow(()-> new NotFoundException("Category not found !"));
	}
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}
	
}
