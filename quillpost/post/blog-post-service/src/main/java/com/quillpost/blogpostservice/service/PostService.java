package com.quillpost.blogpostservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quillpost.blogpostservice.exceptions.NotFoundException;
import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.repository.CategoryRepository;
import com.quillpost.blogpostservice.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	
	public List<PostItem> getAllPost(){
		List<PostItem> posts = new ArrayList<>();
		postRepository.findAll().forEach(posts::add);
		return posts;
	}
	
	public PostItem getPostById(Long postId){
		PostItem post = this.postRepository.findById(postId)
			.orElseThrow(()-> new NotFoundException("Post", "post id", postId));
		return post;
	}

	public List<PostItem> getAllPostByUsername(String username){
		return postRepository.findByUsername(username);
	}

	public List<PostItem> getAllPostByCategory(Integer categoryID){
		
		Category cat = categoryRepository.findById(categoryID).orElseThrow(()-> new NotFoundException("Category","category id", categoryID));
		return postRepository.findByCategory(cat);
	}
	
	public PostItem createPost(PostItem post, String user_name, Integer categoryID){

		Category category = this.categoryRepository.findById(categoryID)
			.orElseThrow(()-> new NotFoundException("Category", "category ID", categoryID));
		post.setCategory(category);
		post.setUsername(user_name);
		post.setDate(new Date());
		return postRepository.save(post);
	}
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}
	
}
