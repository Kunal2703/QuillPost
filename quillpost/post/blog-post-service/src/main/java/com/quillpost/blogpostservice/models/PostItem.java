package com.quillpost.blogpostservice.models;

import java.util.Date;

import jakarta.persistence.*;


@Entity
public class PostItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long post_id;
	private String username;
	@Column(nullable = false)
	private String title;
	@Lob
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private int likes;
	private Date date;
	
	public PostItem(String title, String content) {
		this.title = title;
		this.content = content;
		this.likes = 0;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	@PrePersist
	protected void onCreate() {
	    this.date = new Date();
	}
	
}
