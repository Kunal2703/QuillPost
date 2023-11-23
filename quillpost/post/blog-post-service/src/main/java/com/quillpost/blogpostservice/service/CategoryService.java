package com.quillpost.blogpostservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.payloads.CategoryDTO;
import com.quillpost.blogpostservice.repository.CategoryRepository;
import com.quillpost.blogpostservice.exceptions.NotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	// public Iterable<Category> getAllCategories(){
	// 	return categoryRepository.findAll();
	// }
	// public Category getCategoryByName(String categoryName) throws NotFoundException {
	// 	return categoryRepository.findByCategoryName(categoryName);
	// }
	// public Category createCategory(Category categoryName) {
	// 	Category cat = new Category(categoryName.getCategory());
	// 	return categoryRepository.save(cat);
	// }
	public CategoryDTO createCategory(CategoryDTO categoryDTO){
		Category cat = this.modelMapper.map(categoryDTO, Category.class);
		Category added = this.categoryRepository.save(cat);
		return this.modelMapper.map(added, CategoryDTO.class);
	}
	
	public void deleteCategory(Integer categoryID){
		Category cat = this.categoryRepository.findById(categoryID)
			.orElseThrow(()-> new NotFoundException("Category", "CategoryID", categoryID));
		this.categoryRepository.delete(cat);

	}
	
	public CategoryDTO getCategoryDTO(Integer categoryID){
		Category cat = this.categoryRepository.findById(categoryID)
		.orElseThrow(()-> new NotFoundException("Category", "CategoryID", categoryID));

		return this.modelMapper.map(cat, CategoryDTO.class);

	}

	public List<CategoryDTO> getCategories(){
		List<Category> categories=this.categoryRepository.findAll();
		List<CategoryDTO> catDTO = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return catDTO;
	}
}
