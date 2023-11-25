package com.quillpost.blogpostservice.payloads;

import java.util.List;

import com.quillpost.blogpostservice.models.PostItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostItem> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

}
