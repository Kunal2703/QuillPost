package com.quillpost.blogpostservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.payloads.PostResponse;
import com.quillpost.blogpostservice.exceptions.NotFoundException;
import com.quillpost.blogpostservice.exceptions.UnauthorizedException;
import com.quillpost.blogpostservice.service.PostService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;

	//@CookieValue(name = "jwt", required = false) String jwtToken
	@PostMapping("/category/{categoryID}/posts")
	public ResponseEntity<PostItem> createPost(@RequestBody PostItem post, @PathVariable Integer categoryID, @RequestHeader(name="jwt", required = false) String jwtToken ) throws UnauthorizedException, JSONException, NotFoundException{
		RestTemplate restTemplate = new RestTemplate();
		if(jwtToken == null ) {
			throw new UnauthorizedException("Sorry ! Token not found");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "jwt" + "=" + jwtToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://authentication:8000/api/user", HttpMethod.GET, requestEntity, String.class);
		//ResponseEntity<String> response = restTemplate.exchange("http://localhost:8000/api/user", HttpMethod.GET, requestEntity, String.class);
		HttpStatusCode status = response.getStatusCode();
		if(status.is4xxClientError()) {
			throw new UnauthorizedException("Unauthenticated !");
		}
		JSONObject object = new JSONObject(response.getBody());
		PostItem createdPost = postService.createPost(post, object.getString("username"), categoryID);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
		
	}


	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
		@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
		@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
		@RequestParam(value = "sortBy", defaultValue = "postID", required = false) String sortBy,
		@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
	){
		PostResponse posts = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostItem> getPostById(@PathVariable Long postId){
		PostItem post=postService.getPostById(postId);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	

	// Delete the post using postId
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Long postId,@RequestHeader(name="jwt", required = false) String jwtToken ) throws UnauthorizedException, JSONException {
		RestTemplate restTemplate = new RestTemplate();
		if(jwtToken == null ) {
			throw new UnauthorizedException("Sorry ! Token not found");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "jwt" + "=" + jwtToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://authentication:8000/api/user", HttpMethod.GET, requestEntity, String.class);
		//ResponseEntity<String> response = restTemplate.exchange("http://localhost:8000/api/user", HttpMethod.GET, requestEntity, String.class);
		HttpStatusCode status = response.getStatusCode();
		if(status.is4xxClientError()) {
			throw new UnauthorizedException("Unauthenticated !");
		}

		JSONObject object = new JSONObject(response.getBody());
		PostItem delPost = postService.getPostById(postId);
		if(delPost.getUsername().equals(object.getString("username")) ) {
			postService.deletePost(postId);
			return new ResponseEntity<>("Post Successfully Deleted",HttpStatus.NO_CONTENT);
		}else {
			throw new UnauthorizedException("Invalid User, Permission Denied");
		}
		
	}
	// Retrieve the list of all the post by the username
	@GetMapping("/user/{username}/posts")
	public ResponseEntity<PostResponse> getAllPostByUsername(
		@PathVariable String username, 
		@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
		@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
		@RequestParam(value = "sortBy", defaultValue = "postID", required = false) String sortBy,
		@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
		){
		
		PostResponse posts = postService.getAllPostByUsername(username, pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	// Retrieve all the post by the categories
	@GetMapping("/category/{categoryID}/posts")
	public ResponseEntity<PostResponse> getAllPostByCategory(
		@PathVariable Integer categoryID,
		@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
		@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
		@RequestParam(value = "sortBy", defaultValue = "postID", required = false) String sortBy,
		@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) throws NotFoundException{

		PostResponse posts = postService.getAllPostByCategory(categoryID, pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
}
