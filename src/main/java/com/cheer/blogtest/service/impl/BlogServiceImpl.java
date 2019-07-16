package com.cheer.blogtest.service.impl;

import com.cheer.blogtest.dao.BlogMapper;
import com.cheer.blogtest.model.Blog;
import com.cheer.blogtest.service.BlogService;
import com.cheer.blogtest.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public BlogVo getBlogVo(Integer id) {
        return this.blogMapper.getBlogVo(id);
    }

    @Override
    public List<BlogVo> getList() {
        List<BlogVo> blogVoList = this.blogMapper.getList();
        return blogVoList;
    }

    @Override
    public int insertBlog(Blog blog) {
        int i = this.blogMapper.insertBlog(blog);
        if(i>0){
            System.out.println("插入成功！");
            return i;
        }else {
            return i;
        }
    }

    @Override
    public int bid() {
        int bid = this.blogMapper.bid();
        return bid;
    }

    @Override
    public List<Blog> getNameList(String username) {
        List<Blog> blogList = this.blogMapper.getNameList(username);
        return blogList;
    }
}
