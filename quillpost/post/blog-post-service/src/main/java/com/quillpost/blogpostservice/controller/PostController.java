package com.quillpost.blogpostservice.controller;


import java.util.List;


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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.quillpost.blogpostservice.models.PostItem;
import com.quillpost.blogpostservice.exceptions.NotFoundException;
import com.quillpost.blogpostservice.exceptions.UnauthorizedException;
import com.quillpost.blogpostservice.service.PostService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;

	
	@PostMapping("/category/{categoryID}/posts")
	public ResponseEntity<PostItem> createPost(@RequestBody PostItem post, @PathVariable Integer categoryID, @CookieValue(name = "jwt", required = false) String jwtToken ) throws UnauthorizedException, JSONException, NotFoundException{
		RestTemplate restTemplate = new RestTemplate();
		if(jwtToken == null ) {
			throw new UnauthorizedException("Sorry ! Token not found");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "jwt" + "=" + jwtToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://authentication:8000/api/user", HttpMethod.GET, requestEntity, String.class);
		
		HttpStatusCode status = response.getStatusCode();
		if(status.is4xxClientError()) {
			throw new UnauthorizedException("Unauthenticated !");
		}
		JSONObject object = new JSONObject(response.getBody());
		PostItem createdPost = postService.createPost(post, object.getString("username"), categoryID);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
		
	}


	@GetMapping("/posts")
	public List<PostItem> getAllPosts(){
		return postService.getAllPost();
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostItem> getPostById(@PathVariable Long postId){
		PostItem post=postService.getPostById(postId);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	


	// Delete the post using postId
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Long postId,@CookieValue(name = "jwt", required = false) String jwtToken ) throws UnauthorizedException, JSONException {
		RestTemplate restTemplate = new RestTemplate();
		if(jwtToken == null ) {
			throw new UnauthorizedException("Sorry ! Token not found");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "jwt" + "=" + jwtToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://authentication:8000/api/user", HttpMethod.GET, requestEntity, String.class);
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
	public List<PostItem> getAllPostByUsername(@PathVariable String username ){
		return postService.getAllPostByUsername(username);
	}
	
	// Retrieve all the post by the categories
	@GetMapping("/category/{categoryID}/posts")
	public List<PostItem> getAllPostByCategory(@PathVariable Integer categoryID) throws NotFoundException{
		List<PostItem> list = postService.getAllPostByCategory(categoryID);
		return list;
		
	}
}
