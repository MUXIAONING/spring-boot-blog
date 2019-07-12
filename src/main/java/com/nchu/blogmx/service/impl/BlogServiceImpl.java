package com.nchu.blogmx.service.impl;

import com.nchu.blogmx.bean.Blog;
import com.nchu.blogmx.bean.BlogQuery;
import com.nchu.blogmx.bean.Type;
import com.nchu.blogmx.dao.BlogRepository;
import com.nchu.blogmx.error.NotFoundException;
import com.nchu.blogmx.service.BlogService;
import com.nchu.blogmx.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }
        else {
            blog.setUpdateTime(new Date());
            blog.setViews(blogRepository.getOne(blog.getId()).getViews());
            blog.setCreateTime(blogRepository.getOne(blog.getId()).getCreateTime());
        }
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog blog1=blogRepository.getOne(id);
        if (blog1==null){
            throw new NotFoundException("blog不存在");
        }
        BeanUtils.copyProperties(blog, blog1);
        return blogRepository.save(blog1);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                if(!"".equals(blog.getTitle())&&blog.getTitle()!=null){

                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                if(blog.getTypeId()!=null){

                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blog.getTypeId()));

                }

                if(blog.isRecommend()){

                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.isRecommend()));

                }

                query.where(predicates.toArray(new Predicate[predicates.size()]));

                return null;
            }
        },pageable);


    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
/*        if(blogRepository.isPublish()) {

        }else {

        }
        return blogRepository.findAll(pageable);*/
        return blogRepository.isPublish(pageable);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getOne(id);
        blog.setViews(blog.getViews()+1);
        blog.setCreateTime(blog.getCreateTime());
        blogRepository.save(blog);
        if(blog==null){
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return b;
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String,List<Blog>> map = new HashMap<>();

        for(String year : years){
            map.put(year,blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0, size, sort);
        return blogRepository.findTop( pageable);
    }

    @Override
    public Integer listView() {
        return blogRepository.listView();
    }

    @Override
    public Integer titleCount() {
        return blogRepository.titleCount();
    }

    @Override
    public List<Blog> listViewBlogTop(Integer size) {
        Sort sort1 = new Sort(Sort.Direction.DESC,"views");
        Pageable pageable = new PageRequest(0, size, sort1);
        return blogRepository.findTopView(pageable);
    }

//    @Override
//    public List<Blog> listCommentBlogTop(Integer size) {
//        Sort sort1 = new Sort(Sort.Direction.DESC,"views");
//        Pageable pageable = new PageRequest(0, size, sort1);
//        return blogRepository.findTopView(pageable);
//    }

}



