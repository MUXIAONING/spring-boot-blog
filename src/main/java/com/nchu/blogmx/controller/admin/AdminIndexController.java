package com.nchu.blogmx.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Api(tags = "管理员登陆")
public class AdminIndexController {

    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }

}
