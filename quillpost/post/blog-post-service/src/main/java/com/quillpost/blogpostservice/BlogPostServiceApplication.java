package com.quillpost.blogpostservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.quillpost.blogpostservice.controller.PostController;
import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.repository.PostRepository;
import com.quillpost.blogpostservice.service.PostService;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PostController.class, PostService.class, PostItem.class, PostRepository.class})
public class BlogPostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogPostServiceApplication.class, args);
	}

}
