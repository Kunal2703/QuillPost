package com.quillpost.blogpostservice.payloads;

import java.util.ArrayList;
import java.util.List;

import com.quillpost.blogpostservice.models.PostItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostView {
    private PostItem postItem;
    private List<Comments> comments = new ArrayList<>();

}