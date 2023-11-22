package com.quillpost.blogpostservice.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.models.PostItem;

@Repository
public interface PostRepository extends CrudRepository<PostItem, Long> {
	
	Iterable<PostItem> findByUsername(String username);
	Iterable<PostItem> findByCategory(Category category);
}
