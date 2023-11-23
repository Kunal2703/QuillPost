package com.quillpost.blogpostservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.quillpost.blogpostservice.payloads.CategoryDTO;
import com.quillpost.blogpostservice.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO createdCategory = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
		return new ResponseEntity<>("Category deleted", HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer catId){
        CategoryDTO cat = this.categoryService.getCategoryDTO(catId);
		return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> categories = this.categoryService.getCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    
}
