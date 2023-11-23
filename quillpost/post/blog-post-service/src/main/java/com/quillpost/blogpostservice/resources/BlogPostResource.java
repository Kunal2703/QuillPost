//package com.quillpost.blogpostservice.resources;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.WebUtils;
//
//import com.quillpost.blogpostservice.models.PostItem;
//import com.quillpost.blogpostservice.models.PostItemDTO;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/post")
//public class BlogPostResource {
//	
////	@RequestMapping("/{user_name}")
////	public String getPost(@PathVariable("user_name") String user_name){
////		RestTemplate restTemplate = new RestTemplate();
////		//HttpHeaders headers = new HttpHeaders();
////		//headers.set("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZXhwIjoxNjk4MzQyNDgxLCJpYXQiOjE2OTgzMzg4ODF9.e3ninF3DJR6dedrVbOk8c5Tf4JiHZuh-x_iW70B0CQ4");
////		//HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
////		//ResponseEntity<String> response = restTemplate.exchange("http://localhost:8000/api/user", HttpMethod.GET, requestEntity, String.class);
////		//String obj = response.getBody();
////		//String obj = restTemplate.getForObject("http://localhost:8000/api/user", String.class);
////		return auth;
////	}
//	@GetMapping("/test")
//	public String testPage(HttpServletRequest request) throws JSONException {
////		List<String> list = new ArrayList<>();
//		RestTemplate restTemplate = new RestTemplate();
//		Cookie[] cookies = request.getCookies();
//		Cookie token = null;
//		if(cookies!=null) {
//			for(Cookie cookie : cookies) {
//				if(cookie.getName().equals("jwt")) {
//					token=cookie;
//					break;
//				}
//			}
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Cookie", token.getName()+ "=" + token.getValue());
//			HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
//			ResponseEntity<String> response = restTemplate.exchange("http://localhost:8000/api/user", HttpMethod.GET, requestEntity, String.class);
//			JSONObject object = new JSONObject(response.getBody());
//			return (String) object.get("username");
//		}
//		return "Cookie Not found";
//		
//	}
//	@PostMapping("/create")
//	public ResponseEntity<PostItem> createBlogPost(@RequestBody PostItemDTO postDTO){
//		RestTemplate restTemplate = new RestTemplate();
//		Cookie[] cookies = request.getCookies();
//		Cookie token = null;
//		if(cookies!=null) {
//			for(Cookie cookie : cookies) {
//				if(cookie.getName().equals("jwt")) {
//					token=cookie;
//					break;
//				}
//			}
//		}	
//	}		
//}
