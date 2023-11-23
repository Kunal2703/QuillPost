package com.quillpost.blogpostservice.models;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryID;

	@Column(unique=true, nullable = false)
	private String categoryName;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<PostItem> posts = new ArrayList<>();
}
