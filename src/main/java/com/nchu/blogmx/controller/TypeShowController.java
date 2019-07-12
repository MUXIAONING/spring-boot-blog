package com.nchu.blogmx.controller;

import com.nchu.blogmx.bean.BlogQuery;
import com.nchu.blogmx.bean.Type;
import com.nchu.blogmx.service.BlogService;
import com.nchu.blogmx.service.TypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Api(tags = "前台博客分类管理")
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, @PathVariable Long id, Model model){

        List<Type> types = typeService.listTypeTop(10000);

        if (id == -1){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery=new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery ) );
        model.addAttribute("activeTypeId", id);
        model.addAttribute("recommends", blogService.listRecommendBlogTop(3));
        return "types";

    }
}
