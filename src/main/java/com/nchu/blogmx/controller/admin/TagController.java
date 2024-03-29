package com.nchu.blogmx.controller.admin;

import com.nchu.blogmx.bean.Tag;
import com.nchu.blogmx.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@Api(tags = "后台博客标签管理")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 6,sort ={"id"},direction = Sort.Direction.DESC)Pageable pageable,Model model){
        //List<Tag> tags = tagService.listTagTop(1000);
        model.addAttribute("page",tagService.listTag(pageable) );
        //model.addAttribute("tags", tags);
        return "admin/tags";
    }

    @GetMapping(value = "/tags/input")
    public  String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String editpost(Tag tag, RedirectAttributes redirectAttributes){
        Tag tag1=tagService.getTagByName(tag.getName());
        if (tag1!=null){
            redirectAttributes.addFlashAttribute("message", "当前标签已经存在!");

        }else {
            tagService.saveTag(tag);
            redirectAttributes.addFlashAttribute("message","恭喜你,增加成功!");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String post(Tag tag,RedirectAttributes attributes){

        Tag tag1 = tagService.getTagByName(tag.getName());

        if(tag1!=null){
            attributes.addFlashAttribute("message","当前标签已经存在!");
        }else{
            tagService.updateTag(tag.getId(),tag);
            attributes.addFlashAttribute("message","恭喜你,修改成功!");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Long id, RedirectAttributes attributes){

        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";

    }

    @RequestMapping(value ="/tags/{id}/input" ,method = RequestMethod.GET)

    public String input(@PathVariable Long id,Model model){

        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";

    }

}
