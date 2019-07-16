package com.cheer.blogtest.service;

import com.cheer.blogtest.model.Blog;
import com.cheer.blogtest.vo.BlogVo;

import java.util.List;

public interface BlogService {

    BlogVo getBlogVo(Integer id);

    List<BlogVo> getList();

    int insertBlog(Blog blog);

    int bid();

    List<Blog> getNameList(String username);
}
