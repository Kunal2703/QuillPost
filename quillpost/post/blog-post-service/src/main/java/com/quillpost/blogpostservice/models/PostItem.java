package com.quillpost.blogpostservice.models;

import java.util.Date;

import jakarta.persistence.*;


@Entity
public class PostItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postID;
	
	@Column(nullable = false)
	private String username;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Lob
	@Column(length = 10000, nullable = false)
	private String content;
	
	@Column
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;

	
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
		return postID;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getCategoryName(){
		return this.category.getCategoryName();
	}
	public Integer getCategoryId(){
		return this.category.getCategoryID();
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return this.date;
	}
	
}
