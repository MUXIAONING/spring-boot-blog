package com.nchu.blogmx.controller;

import com.nchu.blogmx.bean.Message;
import com.nchu.blogmx.bean.TransWorld;
import com.nchu.blogmx.service.*;
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

import java.util.Date;

@Controller
@Api(tags = "前台首页")
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;;

    @Autowired
    private CommentService commentService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/")
    public String index(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("typesCount",typeService.listType().size());
        model.addAttribute("tagsCount",tagService.listTag().size());
        model.addAttribute("commentsCount",commentService.listComment().size());
        model.addAttribute("notices",noticeService.listNotice());
        model.addAttribute("messages",messageService.listMessages());
        model.addAttribute("recommends",blogService.listRecommendBlogTop(3));

        model.addAttribute("viewsCount",blogService.listView());
        return "index";
    }

    @PostMapping("/messages")
    @ApiOperation(value = "前台留言板")
    public String message(Message message, RedirectAttributes redirectAttributes){

        message.setCreateTime(new Date());
        Message message1=messageService.saveMessage(message);
        if(message1!=null){
            redirectAttributes.addFlashAttribute("message","谢谢你的声音，已成功提交，等待博主听到。");
        }else{
            redirectAttributes.addFlashAttribute("message","发生错误，博主未能听到，请稍后重试.");
        }
        return "redirect:/aboutweb";
    }
    @GetMapping("/blog")
    @ApiOperation(value = "博客详情")
    public String blog(){

        return "blog";
    }

    @GetMapping("/blog/{id}")
    @ApiOperation(value = "博客详情带id")
    public String blog(@PathVariable Long id, Model model) {

        if (id == 22) {
            return "aboutweb";
        } else if (id == 23) {
            return "aboutme";
        } else {

            model.addAttribute("blog", blogService.getAndConvert(id));
            TransWorld transWord = new TransWorld();
            transWord.setWord("");
            transWord.setResult("");
            model.addAttribute("transWord", transWord);
            return "blog";

        }
    }
}
