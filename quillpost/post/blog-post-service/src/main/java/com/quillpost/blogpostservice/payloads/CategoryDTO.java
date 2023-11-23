package com.quillpost.blogpostservice.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    
    private Long categoryID;
    private String categoryName;
}
