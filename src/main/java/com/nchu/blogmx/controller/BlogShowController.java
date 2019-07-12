package com.nchu.blogmx.controller;

import com.nchu.blogmx.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(tags = "前台的归档查询")
public class BlogShowController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/archives")
    @ApiOperation(value = "归档列表")
    public String archives(Pageable pageable, Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("recommends",blogService.listRecommendBlogTop(3) );
        model.addAttribute("page",blogService.listBlog(pageable));
        return "archives";
    }

}
