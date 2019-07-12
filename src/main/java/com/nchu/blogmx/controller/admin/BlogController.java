package com.nchu.blogmx.controller.admin;


import com.nchu.blogmx.bean.Account;
import com.nchu.blogmx.bean.Blog;
import com.nchu.blogmx.bean.BlogQuery;
import com.nchu.blogmx.service.BlogService;
import com.nchu.blogmx.service.TagService;
import com.nchu.blogmx.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@Api(tags = "后台的博客内容管理")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    @ApiOperation(value = "进入博客列表界面")
    public String blogs(@PageableDefault(size = 6,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blogQuery, Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable, blogQuery) );

        return "admin/blogs";
    }

    @PostMapping("/blogs")
    @ApiOperation(value = "博客新增返回界面")
    public String post(Blog blog, RedirectAttributes redirectAttributes, HttpSession httpSession){
        blog.setAccount((Account) httpSession.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b =blogService.saveBlog(blog);
        if (b==null){
            redirectAttributes.addFlashAttribute("message","操作失败");
        }else {
            redirectAttributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/search")
    @ApiOperation(value = "博文的检索引擎")
    public String search(@PageableDefault(size = 6,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blogQuery,
                         Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));

        return "admin/blogs :: blogList";//局部刷新

    }
    @GetMapping("/blogs/input")
    @ApiOperation(value = "博客新增界面")
    public String input(Model model){
        model.addAttribute("types",typeService.listType() );
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog",new Blog() );
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")
    @ApiOperation(value = "博客修改界面")
    public String editInput(@PathVariable Long id,Model model){

        model.addAttribute("types",typeService.listType() );
        model.addAttribute("tags", tagService.listTag());
        Blog blog=blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog );
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/delete")
    @ApiOperation(value = "删除博客")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message", "删除成功啦");
        return "redirect:/admin/blogs";
    }

}
