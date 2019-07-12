package com.nchu.blogmx.controller.admin;

import com.nchu.blogmx.bean.Notice;
import com.nchu.blogmx.service.NoticeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@Api(tags = "后台公告管理")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice")
    public String notice(Model model){
        model.addAttribute("notices" , noticeService.listNotice() );
        return "admin/notice";
    }

    @GetMapping("/notice/input")
    public String input(Model model){

        model.addAttribute("notice",new Notice());
        return "admin/notice-input";
    }

    @PostMapping("/notice/input")
    public String input(Notice notice, RedirectAttributes attributes){

        Notice notice1 = noticeService.saveNotice(notice);
        if(notice1!=null){

            attributes.addFlashAttribute("message","增加成功");
        }else{
            attributes.addFlashAttribute("message","增加失败");
        }
        return "redirect:/admin/notice";

    }

    @GetMapping("/notice/{id}/input")
    public String editNotice(@PathVariable Long id,Model model){
        model.addAttribute("notice", noticeService.getNotice(id));
        return "admin/notice-input";
    }

    @PostMapping("/notice/{id}/input")
    public String notice(@PathVariable Long id,Notice notice,RedirectAttributes redirectAttributes){

        notice.setId(id);
        Notice notice1=noticeService.updateNotice(id, notice);

        if (notice1!=null){
            redirectAttributes.addFlashAttribute("message","公告修改成功");
        }else {
            redirectAttributes.addFlashAttribute("message","公告修改失败");
        }
        return "redirect:/admin/notice";
    }

    @GetMapping("/notice/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        noticeService.deleteNotice(id);

        attributes.addFlashAttribute("message","删除成功");

        return "redirect:/admin/notice";

    }
}
