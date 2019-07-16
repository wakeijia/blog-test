package com.cheer.blogtest.web.controller;

import com.cheer.blogtest.model.Blog;
import com.cheer.blogtest.model.Reply;
import com.cheer.blogtest.model.Users;
import com.cheer.blogtest.service.BlogService;
import com.cheer.blogtest.service.ReplyService;
import com.cheer.blogtest.service.UsersService;
import com.cheer.blogtest.util.IOUtils;
import com.cheer.blogtest.util.StringUtils;
import com.cheer.blogtest.vo.BlogVo;
import com.cheer.blogtest.vo.ReplyVo;
import javafx.application.Application;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ReplyService  replyService;

    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String username, @RequestParam String password, HttpSession session){
        log.trace("username={}, password={}", username, password);
        if (usersService.checkLogin(username,password)){
            Users users = this.usersService.find(username);
            session.setAttribute("users",users);
            return "redirect:/index";
        }else {
            return "redirect:/signin";
        }
    }

    @GetMapping("/signout")
    public String signout(HttpSession session){
        //销毁session 自杀
        session.invalidate();
        return "redirect:/signin";
    }

    @GetMapping("/index")
    public String index(Model model){
        //主题帖通过Vo加入头像列表
        List<BlogVo> blogVoList = this.blogService.getList();
        model.addAttribute("blogList", blogVoList);
        return "index";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password,
                         @RequestParam String email, @RequestParam String phoneNum,
                         MultipartFile file){
        Users users = new Users();
        users.setUsername(username);
        //密码加密
        users.setPassword(StringUtils.encrypt(password));
        users.setEmail(email);
        users.setPhoneNum(phoneNum);
        try {
            String fileName = file.getOriginalFilename();//获取上传文件名称
            String suffix = fileName.substring(fileName.lastIndexOf("."));//分解上传文件后缀名
            String avatar = users.getUsername();
            String path = System.getProperty("user.home")+"/avatar/"+avatar+suffix;//设置上传文件对应用户名
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));//写入到头像文件夹中
            users.setAvatar(avatar + suffix);
            log.trace(users);
            this.usersService.insert(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/signin";
    }

    @RequestMapping("avatar")
    @ResponseBody
    public Users avatar(HttpSession session){
        Users user = (Users) session.getAttribute("users");
        if (user.getAvatar() == null) {
            //如果头像为空，默认使用头像
            user.setAvatar("default.jpeg");
        } else {
            ApplicationHome home = new ApplicationHome(getClass());
            File sysFile = home.getSource();
            String filePath = sysFile.getPath();
            log.debug(filePath);
            String dest = filePath + "/static/avatar/" + user.getAvatar();
            System.out.println(dest);
            File avatar = new File(dest);
            if (!avatar.exists()) {
                String src = System.getProperty("user.home") + "/avatar/" + user.getAvatar();
                IOUtils.copy(src, dest);
            }
        }
        return user;
    }


    @RequestMapping(value = "check",method= RequestMethod.POST)
    @ResponseBody
    public Map<String ,String> check(String username){
        Map<String,String> checkMap = new HashMap<>();
        Users user = usersService.find(username);
        if(user!=null){
            checkMap.put("code","-1");
            checkMap.put("message","用户名已经存在");
        }
        return checkMap;
    }

    @GetMapping("/person/{username}")
    public String person(@PathVariable String username,Model model){
        Users users = this.usersService.find(username);
        model.addAttribute("users", users);
        //通过发帖人名字获得一个list
        List<Blog> blogList = this.blogService.getNameList(username);
        model.addAttribute("blogList",blogList);

        List<ReplyVo> replyList = this.replyService.getReplyList(username);
        model.addAttribute("replyList",replyList);
        return "person";
    }
}
