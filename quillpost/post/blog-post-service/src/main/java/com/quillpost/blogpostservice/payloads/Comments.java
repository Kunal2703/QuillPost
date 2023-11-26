package com.quillpost.blogpostservice.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Comments {
    private Integer comment_id;
    private long post_id;
    private String username;
    private String content;
    private Date timestamp;
}
