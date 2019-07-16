package com.cheer.blogtest.vo;

import com.cheer.blogtest.model.Blog;
import lombok.Data;

@Data
public class BlogVo extends Blog {
    private String avatar;
    private int floor;
}
