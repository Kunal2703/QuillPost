package com.quillpost.blogpostservice.repository;

import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.models.PostItem;

@Repository
public interface PostRepository extends CrudRepository<PostItem, Long> {
	
	List<PostItem> findByUsername(String username);
	List<PostItem> findByCategory(Category category);
}
