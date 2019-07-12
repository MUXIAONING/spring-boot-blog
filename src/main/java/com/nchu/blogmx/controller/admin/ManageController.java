package com.nchu.blogmx.controller.admin;

import com.nchu.blogmx.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Api(tags = "后台控制器管理")
public class ManageController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private TypeService typeService;;

    @Autowired
    private CommentService commentService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private MessageService messageService;
    @RequestMapping("/manage")
        public String Manage(Model model){
        model.addAttribute("page",blogService.titleCount());
        model.addAttribute("viewTop",blogService.listViewBlogTop(8));
       // model.addAttribute("commentTop",blogService.listCommentBlogTop(8));
        model.addAttribute("commentsCount",commentService.listComment().size());
        //model.addAttribute("notices",noticeService.listNotice());
        model.addAttribute("messages",messageService.listMessages().size());
        //model.addAttribute("recommends",blogService.listRecommendBlogTop(3));

        model.addAttribute("friendCount",friendService.listFriend().size());
        return "admin/manages";

    }
}
