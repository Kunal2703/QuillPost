package com.quillpost.blogpostservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<PostItem> getAllPost(){
		return postRepository.findAll();
	}
	
	public Optional<PostItem> getPostById(Long postId){
		return postRepository.findById(postId);
	}
	
	public PostItem createPost(PostItem post) {
//		post.setDate(new Date());// set the create of the post date
		return postRepository.save(post);
	}
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}
}
