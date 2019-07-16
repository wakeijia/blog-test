package com.cheer.blogtest.web.controller;

import com.cheer.blogtest.model.Blog;
import com.cheer.blogtest.model.Reply;
import com.cheer.blogtest.model.Users;
import com.cheer.blogtest.service.BlogService;
import com.cheer.blogtest.service.ReplyService;
import com.cheer.blogtest.service.UsersService;
import com.cheer.blogtest.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/blog/{id}")
    public String reply(@PathVariable Integer id, Model model){
        //通过id找到对应的主贴放入model
        model.addAttribute("blog",this.blogService.getBlogVo(id));
        //回复列表通过Vo加入头像一列，放入model方便遍历
        List<ReplyVo> getRepList = this.replyService.getReplyVoList(id);
        model.addAttribute("replyList",getRepList);
        System.out.println(getRepList);
        //获得最大楼层数
        int floor = this.replyService.selectFloor(id);
        model.addAttribute("floor",floor);
        return "blog";
    }

    @PostMapping("/blog/{id}")
    public String reply(@PathVariable Integer id, @RequestParam String reply, HttpSession session){
        //获得登录的对象
        Users users = (Users)session.getAttribute("users");
        Blog blog = this.blogService.getBlogVo(id);
        /*//获得前端评论的回复内容
        String reply = request.getParameter("reply");*/
        //楼层+1
        int floor = this.replyService.selectFloor(id)+1;
        //创建一个新的回复对象插入数据库
        Reply reply1 = new Reply(id,floor,users.getUsername(),blog.getBTheme(),reply);
        this.replyService.insertReply(reply1);
        return "redirect:/blog/"+id;
    }

    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest request){
        //获得主贴主题及正文
        String theme = request.getParameter("theme");
        String message = request.getParameter("message");
        Users users = (Users)request.getSession().getAttribute("users");
        //创建一个新的主贴对象插入数据库
        Blog blog = new Blog(users.getUsername(),theme,message);
        this.blogService.insertBlog(blog);
        //每创建一个主贴就在回复贴中创建一行楼层为0的回复贴
        int id = this.blogService.bid();
        Reply reply = new Reply(id,0,users.getUsername(),blog.getBTheme(),null);
        this.replyService.insertReply(reply);
        return "redirect:/blog/"+id;
    }


}
