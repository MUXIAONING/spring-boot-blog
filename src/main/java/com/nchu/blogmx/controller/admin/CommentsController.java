package com.nchu.blogmx.controller.admin;

import com.nchu.blogmx.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@Api(tags = "后台评论管理")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/comments")

    public String comment(@PageableDefault(size = 6,sort={"createTime"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",commentService.listComment(pageable));
        return "admin/comments";
    }

    @RequestMapping("comments/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        commentService.deleteComment(id);
        redirectAttributes.addFlashAttribute("message", "评论删除成功");
        return "redirect:/admin/comments";
    }




    /*@RequestMapping("/comments/ceshi")
    @ResponseBody
    public String ceshi(){
        return loadJson("https://free-api.heweather" +
                ".net/s6/weather/now?location=auto_ip&key=db86a5196f304e52a4369818c5182e60");
    }

    public static String loadJson (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }*/

}
