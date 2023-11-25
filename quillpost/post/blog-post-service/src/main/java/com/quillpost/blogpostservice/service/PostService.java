package com.quillpost.blogpostservice.service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.quillpost.blogpostservice.exceptions.NotFoundException;
import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.payloads.PostResponse;
import com.quillpost.blogpostservice.repository.CategoryRepository;
import com.quillpost.blogpostservice.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir){

		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pageableObj = PageRequest.of(pageNumber, pageSize, sort);

		Page<PostItem> pagePostItem = this.postRepository.findAll(pageableObj);

		List<PostItem> posts = pagePostItem.getContent();

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(posts);
		postResponse.setPageNumber(pagePostItem.getNumber());
		postResponse.setPageSize(pagePostItem.getSize());
		postResponse.setTotalElements(pagePostItem.getTotalElements());
		postResponse.setTotalPages(pagePostItem.getTotalPages());
		postResponse.setLastPage(pagePostItem.isLast());
		return postResponse;
	}
	
	public PostItem getPostById(Long postId){
		PostItem post = this.postRepository.findById(postId)
			.orElseThrow(()-> new NotFoundException("Post", "post id", postId));
		return post;
	}

	public PostResponse getAllPostByUsername(String username, Integer pageNumber, Integer pageSize, String sortBy, String sortDir){

		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageableObj = PageRequest.of(pageNumber, pageSize, sort);
		Page<PostItem> pagePostItem= this.postRepository.findByUsername(username, pageableObj);
		List<PostItem> posts = pagePostItem.getContent();
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(posts);
		postResponse.setPageNumber(pagePostItem.getNumber());
		postResponse.setPageSize(pagePostItem.getSize());
		postResponse.setTotalElements(pagePostItem.getTotalElements());
		postResponse.setTotalPages(pagePostItem.getTotalPages());
		postResponse.setLastPage(pagePostItem.isLast());
		return postResponse;
	}

	public PostResponse getAllPostByCategory(Integer categoryID, Integer pageNumber, Integer pageSize, String sortBy, String sortDir){
		
		Category cat = categoryRepository.findById(categoryID).orElseThrow(()-> new NotFoundException("Category","category id", categoryID));
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageableObj = PageRequest.of(pageNumber, pageSize, sort);
		Page<PostItem> pagePostItem=this.postRepository.findByCategory(cat, pageableObj);
		List<PostItem> posts = pagePostItem.getContent();
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(posts);
		postResponse.setPageNumber(pagePostItem.getNumber());
		postResponse.setPageSize(pagePostItem.getSize());
		postResponse.setTotalElements(pagePostItem.getTotalElements());
		postResponse.setTotalPages(pagePostItem.getTotalPages());
		postResponse.setLastPage(pagePostItem.isLast());
		return postResponse;
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
	
	public List<PostItem> searchPosts(String keyword){
		List<PostItem> posts = this.postRepository.findByTitleContaining(keyword);
		return posts;
	}

}
