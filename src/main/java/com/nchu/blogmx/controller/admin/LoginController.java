package com.nchu.blogmx.controller.admin;

import com.nchu.blogmx.bean.Account;
import com.nchu.blogmx.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@Api(tags = "后台博客系统登陆")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession httpSession,
                        RedirectAttributes redirectAttributes){
        Account account = accountService.checkAccount(username,password);
        if (account != null){

            account.setPassword(null);

            httpSession.setAttribute("user",account );

            httpSession.setMaxInactiveInterval(0);

            return "admin/index";
        }else{
            redirectAttributes.addFlashAttribute("message","用户名或者密码输入错误");
            return "redirect:/admin";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession ){
        httpSession.removeAttribute("user");
        return "admin/login";
    }
}
