package com.quillpost.blogpostservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quillpost.blogpostservice.models.PostItem;

@Repository
public interface PostRepository extends JpaRepository<PostItem, Long> {

}
