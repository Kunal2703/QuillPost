package com.quillpost.blogpostservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CategoryService categoryService;
	
	public List<PostItem> getAllPost(){
		List<PostItem> posts = new ArrayList<>();
		postRepository.findAll().forEach(posts::add);
		return posts;
	}
	
	public 	Optional<PostItem> getPostById(Long postId){
		return postRepository.findById(postId);
	}
	public List<PostItem> getAllPostByUsername(String username){
		List<PostItem> posts = new ArrayList<>();
		postRepository.findByUsername(username).forEach(posts::add);
		return posts;
	}
	public List<PostItem> getAllPostByCategory(Category category){
		List<PostItem> posts =  new ArrayList<>();
		postRepository.findByCategory(category).forEach(posts::add);
		return posts;
	}
	
	public PostItem createPost(PostItem post, String user_name) throws NotFoundException {
//		post.setDate(new Date());// set the create of the post date
//		Category category = categoryService.getCategoryByName(category_Name);
		post.setUsername(user_name);
//		post.setCategory(category);
		return postRepository.save(post);
	}
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}
	
}
