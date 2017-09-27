package com.my.controller;

import com.my.beans.Post;
import com.my.beans.PostUser;
import com.my.beans.Relations;
import com.my.service.RelationService;
import com.my.service.UserService;
import com.my.service.WeiboService;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);
    @Resource
    UserService userService;
    @Resource
    WeiboService weiboService;
    @Resource
    RelationService relationService;

    /*主页*/
    @GetMapping
    public String index(){
        return "index";
    }
    /*注册*/
    @GetMapping("/regist")
    public String registPager(PostUser postUser){
        return "regist";
    }

    /*注册提交*/
    @RequestMapping(value = "/{action}",method = {RequestMethod.GET,RequestMethod.POST})
    public String regist(@Valid PostUser postUser, Errors errors,String repassword, @PathVariable String action, HttpServletRequest request, Model model){
       /*发送邮件，存库*/
       if(errors.hasErrors()){
           if(repassword.isEmpty()){
               model.addAttribute("remsg","确认密码不能为空");
           }
           return "regist";
       }else {


           if ("register".equals(action)) {
               //注册
            /*String email = request.getParameter("email");
            service.processregister(email);//发邮箱激活
            mav.addObject("text","注册成功");
            mav.setViewName("register/register_success");*/
               userService.processregister(postUser);
               model.addAttribute("remsg", "请查看邮件并激活账号");
               return "index";
           }
        /*激活，修改数据库*/
           else if ("activate".equals(action)) {
               //激活
               String email = request.getParameter("email");//获取email
               String validateCode = request.getParameter("validateCode");//激活码

               try {
                   userService.processActivate(email, validateCode);//调用激活方法
                   return "login";
               } catch (ServiceException e) {
                   request.setAttribute("message", e.getMessage());
                   return "regist";
               } catch (ParseException e) {
                   e.printStackTrace();
               }

           }
       }
        return null;
    }
    /*登录页面*/
    @GetMapping("/login")
    public String loginPager(){
        return "login";
    }
    /*登录提交*/
    @GetMapping("/loging")
    public String login(Post post, String email, String password, HttpSession session,Model model){
        String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
        String email_msg = "";
        String pwd_msg = "";
        if(email.isEmpty()||password.isEmpty()){
            if(email.isEmpty())
               email_msg = "邮箱不能为空";
            if(password.isEmpty())
                pwd_msg ="密码不能为空";
            model.addAttribute("email_msg",email_msg);
            model.addAttribute("pwd_msg",pwd_msg);
            return "login";
        }else if(password.length()<3||password.length()>30){
            if(!email.matches(regex)){
                email_msg = "邮箱格式不正确";
            }
            if(password.length()<6||password.length()>30){
                pwd_msg = "密码长度必须在6-30之间";
            }
            model.addAttribute("email_msg",email_msg);
            model.addAttribute("pwd_msg",pwd_msg);
            return "login";

        }else {
            PostUser postUser = userService.exits(email, password);
            if (postUser != null) {
                session.setAttribute("user", postUser);
                return "redirect:/blog";
            } else {
                return "login";
            }
        }
    }
    /*查找用户页面*/
    @GetMapping("/findUser")
    public String findUser(Model model,HttpSession session){
        PostUser user = (PostUser) session.getAttribute("user");
        List<PostUser> postUsers = userService.userList(user.getUserid());
        model.addAttribute("userList",postUsers);
        return "user/findUser";
    }

    /*查看用户详情*/
    @GetMapping("/{userid}/findUser")
    public String userInfo(@PathVariable int userid,HttpSession session,Model model){
        PostUser postUser = (PostUser) session.getAttribute("user");
        PostUser user = userService.getUser(userid);
        log.info(postUser);
        log.info(user);
        log.info(postUser.getUserid());
        model.addAttribute("userInfo",user);
        Relations relations = relationService.getRelations(postUser.getUserid(), userid);
        model.addAttribute("relations",relations);
        return "posts/post";
    }

    /*显示我关注的人*/
    @GetMapping("/{userid}/following")
    public String following(@PathVariable int userid,Model model){
        PostUser user = userService.getUser(userid);
        model.addAttribute("userInfo",user);
        return "relationship/following";
    }
    /*显示我的粉丝*/
    @GetMapping("/{userid}/followers")
    public String followers(@PathVariable int userid,Model model){
        PostUser user = userService.getUser(userid);
        model.addAttribute("userInfo",user);
        return "relationship/followers";
    }

    /*修改用户资料*/
    @GetMapping("/edit")
    public String editUser(){
        return "user/editUser";
    }

    @PutMapping("/{id}")
    public String editUserCommit(){
        return "redirect:/blog";
    }


    /*注销*/
    @GetMapping("/delogin")
    public String delogin(HttpSession session){
        session.invalidate();
        return "redirect:/user";
    }


}
