package com.nchu.blogmx.dao;

import com.nchu.blogmx.bean.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query("select  function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    @Query("select b from Blog b where b.recommend = true and b.published = true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select sum(views) from Blog ")
    Integer listView();

    @Query("select b from Blog  b where published=1 ")
    Page<Blog> isPublish(Pageable pageable);

    @Query("select count(title) from Blog  b where published=1")
    Integer titleCount();

    @Query("select b from Blog b where b.published = true order by views desc ")
    List<Blog> findTopView(Pageable pageable);

    //@Query("select b,count(c.blogId) from Blog b,Comment c where b.published = true order by  desc ")
    //List<Blog> findTopComment(Pageable pageable);

    //@Query("select b from Blog  b where b.id=?1 and b.published=1 ")
    Page<Blog> findAll(Specification<Blog> blogSpecification, org.springframework.data.domain.Pageable pageable);
}
