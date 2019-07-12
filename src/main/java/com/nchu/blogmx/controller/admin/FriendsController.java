package com.nchu.blogmx.controller.admin;

import com.nchu.blogmx.bean.Friend;
import com.nchu.blogmx.service.FriendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@Api(tags = "后台友情链接管理")
public class FriendsController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/friends")
    public String friends(Model model){
        model.addAttribute("friends",friendService.listFriend() );
        return "admin/friends";
    }

    @RequestMapping("/friends/input/{flag}")
    public String input(@PathVariable String flag,Model model ){
        Friend friend=new Friend();
        if (flag.equals("朋友")){
            friend.setFlag("朋友");
            friend.setDescription("请填写描述");
            friend.setAvatar("请增加url头像");
        }else {
            friend.setFlag(flag);
        }
        model.addAttribute("friend", friend);
        return "admin/friends-input";
    }

    @RequestMapping("friends/input")
    public String input(Friend friend, RedirectAttributes redirectAttributes){
        Friend friend1=friendService.saveFriend(friend);
        if (friend1!=null){
            redirectAttributes.addFlashAttribute("message","友链增加成功");
        }else {
            redirectAttributes.addFlashAttribute("message","友链增加失败");
        }
        return "redirect:/admin/friends";
    }

    @RequestMapping("/friends/{id}/input")
    public String input(@PathVariable Long id,Friend friend, RedirectAttributes redirectAttributes){

        friend.setId(id);
        Friend friend1=friendService.updateFriend(id, friend);
        if (friend1!=null){
            redirectAttributes.addFlashAttribute("message","友链修改成功");
        }else {
            redirectAttributes.addFlashAttribute("message","友链修改失败");
        }
        return "redirect:/admin/friends";
    }

    @GetMapping("/friends/{id}/input")
    public String editBulletin(@PathVariable Long id, Model model){

        model.addAttribute("friend",friendService.getFriend(id));

        return "admin/friends-input";


    }

    @GetMapping("/friends/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        friendService.deleteFriend(id);

        attributes.addFlashAttribute("message","删除成功");

        return "redirect:/admin/friends";

    }
}

