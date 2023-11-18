package com.quillpost.blogpostservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"controller","service","model", "repository"})
public class BlogPostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogPostServiceApplication.class, args);
	}

}
