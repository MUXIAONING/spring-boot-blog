package com.nchu.blogmx.controller.admin;

import com.nchu.blogmx.bean.MessageQuery;
import com.nchu.blogmx.service.MessageService;
import io.swagger.annotations.Api;
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

@Controller
@RequestMapping("/admin")
@Api(tags = "留言板的后台操作")
public class MessageController {


    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public String messages(@PageableDefault(size = 6,sort={"createTime"},direction = Sort.Direction.DESC) Pageable pageable, Model model){

        model.addAttribute("page",messageService.listMessages(pageable));
        return "admin/messages";

    }

    @GetMapping("/messages/{id}/get")
    public String getMessages(@PathVariable Long id, Model model){

        model.addAttribute("messages",messageService.getMessage(id));
        return "admin/messages-get";



    }
    @RequestMapping("/messages/{id}/delete")
    public String deleteMessage(@PathVariable Long id, RedirectAttributes attributes){
        messageService.deleteMessage(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/messages";
    }

    @PostMapping("/messages/search")
    public String search(@PageableDefault(size = 6,sort = {"createTime"},direction = Sort.Direction.DESC)
                                 Pageable pageable, MessageQuery MessageQuery, Model model) {


        model.addAttribute("page", messageService.listBlog(pageable, MessageQuery));

        return "admin/messages :: messagesList";//局部刷新
    }


}
