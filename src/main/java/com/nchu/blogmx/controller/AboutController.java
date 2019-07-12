package com.nchu.blogmx.controller;

import com.nchu.blogmx.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags = "前台与我相关信息管理")
public class AboutController {

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


    @GetMapping("/aboutme")
    @ApiOperation(value = "关于我")
    public String aboutme(Model model){
        model.addAttribute("page",blogService.titleCount());
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("typesCount",typeService.listType().size());
        model.addAttribute("tagsCount",tagService.listTag().size());
        model.addAttribute("commentsCount",commentService.listComment().size());
        model.addAttribute("notices",noticeService.listNotice());
        model.addAttribute("messages",messageService.listMessages());
        model.addAttribute("recommends",blogService.listRecommendBlogTop(3));

        model.addAttribute("viewsCount",blogService.listView());
        return "aboutme";
    }
    @GetMapping("/aboutweb")
    @ApiOperation(value = "关于本站")
    public String aboutweb(Model model){
        model.addAttribute("page",blogService.titleCount());
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("typesCount",typeService.listType().size());
        model.addAttribute("tagsCount",tagService.listTag().size());
        model.addAttribute("commentsCount",commentService.listComment().size());
        model.addAttribute("notices",noticeService.listNotice());
        model.addAttribute("messages",messageService.listMessages());
        model.addAttribute("recommends",blogService.listRecommendBlogTop(3));

        model.addAttribute("viewsCount",blogService.listView());
        return "aboutweb";
    }
}
