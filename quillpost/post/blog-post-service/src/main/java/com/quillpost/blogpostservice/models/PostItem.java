package com.quillpost.blogpostservice.models;

import java.util.Date;

import jakarta.persistence.*;


@Entity
public class PostItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long post_id;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String title;
	@Lob
	@Column(nullable = false)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "categoryName")
	private Category category;
	@Column
	private Date date;
	
	
	public PostItem() {
		
	}
	
	public PostItem(String username,String title, String content, Category category ) {
		this.username = username;
		this.title = title;
		this.content = content;
		this.category = category;
		
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public Long getPostId() {
		return post_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@PrePersist
	protected void onCreate() {
	    this.date = new Date();
	}
	
}
