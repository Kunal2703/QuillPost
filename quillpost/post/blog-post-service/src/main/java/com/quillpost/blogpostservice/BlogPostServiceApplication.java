package com.quillpost.blogpostservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.quillpost.blogpostservice.controller.CategoryController;
import com.quillpost.blogpostservice.controller.PostController;
import com.quillpost.blogpostservice.exceptions.GlobalExceptionHandler;
import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.repository.CategoryRepository;
import com.quillpost.blogpostservice.repository.PostRepository;
import com.quillpost.blogpostservice.service.CategoryService;
import com.quillpost.blogpostservice.service.PostService;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PostController.class, PostService.class, PostItem.class, PostRepository.class, 
	CategoryController.class, CategoryService.class, Category.class, CategoryRepository.class, GlobalExceptionHandler.class})
public class BlogPostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogPostServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
