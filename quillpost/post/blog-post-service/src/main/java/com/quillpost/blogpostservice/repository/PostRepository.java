package com.quillpost.blogpostservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.models.PostItem;

@Repository
public interface PostRepository extends JpaRepository<PostItem, Long> {
	
	Page<PostItem> findByUsername(String username, Pageable pageableObj);
	Page<PostItem> findByCategory(Category category, Pageable pageableObj);
	List<PostItem> findByTitleContaining(String title);
}
