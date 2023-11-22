package com.quillpost.blogpostservice.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.quillpost.blogpostservice.models.Category;
import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.service.CategoryService;
import com.quillpost.blogpostservice.service.NotFoundException;
import com.quillpost.blogpostservice.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;
	
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
	
	@PostMapping
	public ResponseEntity<PostItem> createPost(@RequestBody PostItem post, @CookieValue(name = "jwt", required = false) String jwtToken ) throws UnauthorizedException, JSONException, NotFoundException{
		RestTemplate restTemplate = new RestTemplate();
		if(jwtToken == null ) {
			throw new UnauthorizedException("Sorry ! Token not found");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "jwt" + "=" + jwtToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8000/api/user", HttpMethod.GET, requestEntity, String.class);
		HttpStatusCode status = response.getStatusCode();
		if(status.is4xxClientError()) {
			throw new UnauthorizedException("Unauthenticated !");
		}
		JSONObject object = new JSONObject(response.getBody());
		PostItem createdPost = postService.createPost(post, object.getString("username"));
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
		
	}
	// Delete the post using postId
	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Long postId,@CookieValue(name = "jwt", required = false) String jwtToken ) throws UnauthorizedException, JSONException {
		RestTemplate restTemplate = new RestTemplate();
		if(jwtToken == null ) {
			throw new UnauthorizedException("Sorry ! Token not found");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "jwt" + "=" + jwtToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8000/api/user", HttpMethod.GET, requestEntity, String.class);
		HttpStatusCode status = response.getStatusCode();
		if(status.is4xxClientError()) {
			throw new UnauthorizedException("Unauthenticated !");
		}
		JSONObject object = new JSONObject(response.getBody());
		Optional<PostItem> checkPost = postService.getPostById(postId);
		if(checkPost == null) {
			return new ResponseEntity<>("Post Not Found",HttpStatus.NOT_FOUND);
		}else if(checkPost.get().getUsername().equals(object.getString("username")) ) {
			postService.deletePost(postId);
			return new ResponseEntity<>("Post Successfully Deleted",HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>("Invalid User, Permission Denied",HttpStatus.FORBIDDEN);
		}
		
	}
	// Retrieve the list of all the post by the username
	@GetMapping("/user/{username}")
	public List<PostItem> getAllPostByUsername(@PathVariable String username ){
		return postService.getAllPostByUsername(username);
	}
	// Retrieve all the post by the categories
	@GetMapping("/category/{categoryName}")
	public List<PostItem> getAllPostByCategory(@PathVariable String categoryName) throws NotFoundException{
		Category cat = categoryService.getCategoryByName(categoryName);
		List<PostItem> list = postService.getAllPostByCategory(cat);
		return list;
		
	}
}
