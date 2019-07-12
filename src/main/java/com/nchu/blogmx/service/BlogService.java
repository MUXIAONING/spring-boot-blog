package com.nchu.blogmx.service;

import com.nchu.blogmx.bean.Blog;
import com.nchu.blogmx.bean.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog saveBlog (Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog (Long id);

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Blog getAndConvert (Long id);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Map<String, List<Blog>> archiveBlog();

    Integer listView();

    List<Blog> listRecommendBlogTop(Integer size);

    //文章数量
    Integer titleCount();

    //按照观看次数的排行

    List<Blog> listViewBlogTop(Integer size);

    //按照评论次数

//    List<Blog> listCommentBlogTop(Integer size);





}
