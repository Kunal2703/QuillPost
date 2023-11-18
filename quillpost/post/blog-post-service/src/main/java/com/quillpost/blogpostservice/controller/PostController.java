package com.quillpost.blogpostservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public List<PostItem> getAllPosts(){
		return postService.getAllPost();
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostItem> getPostById(@PathVariable Long postId){
		return postService.getPostById(postId)
				.map(post -> new ResponseEntity<> (post,HttpStatus.OK))
				.orElseGet(()-> new ResponseEntity<> (HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/create")
	public ResponseEntity<PostItem> createPost(@RequestBody PostItem post){
		PostItem createdPost = postService.createPost(post);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Long postId){
		postService.deletePost(postId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
