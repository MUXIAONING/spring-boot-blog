package com.nchu.blogmx.controller.admin;

import com.nchu.blogmx.bean.Type;
import com.nchu.blogmx.service.TypeService;
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
@Api(tags = "后台博客分类管理")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 6,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page", typeService.listType(pageable));

        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes redirectAttributes){

        Type type1=typeService.getTypeByName(type.getName());

        if(type1!=null){
            redirectAttributes.addFlashAttribute("message","错误:已存在同名的分类");
        }else{

            typeService.saveType(type);
            redirectAttributes.addFlashAttribute("message","添加成功.");
        }

        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String input(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id) );
        return "admin/types-input";
    }


    @PostMapping("/types/{id}")
    public String editpost(Type type,RedirectAttributes redirectAttributes){
        Type type1 = typeService.getTypeByName(type.getName());

    if (type1 != null){
        redirectAttributes.addFlashAttribute("message","错误:已存在同名的分类");
    }else {
        typeService.updataType(type.getId(), type);
        redirectAttributes.addFlashAttribute("message","更新成功" );
    }
        return "redirect:/admin/types";
    }


    @GetMapping("/type/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
