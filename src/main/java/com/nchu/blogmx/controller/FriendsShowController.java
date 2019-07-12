package com.nchu.blogmx.controller;

import com.nchu.blogmx.service.BlogService;
import com.nchu.blogmx.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags = "前台友情链接")
public class FriendsShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private FriendService friendService;

    @GetMapping("/friends")
    @ApiOperation(value = "前台友情链接展示")
    public String friends(Model model){
        model.addAttribute("friends",friendService.listFriend() );
        model.addAttribute("recommends",blogService.listRecommendBlogTop(3) );
        return "friends";
    }
}
