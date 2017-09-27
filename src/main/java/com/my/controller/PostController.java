package com.my.controller;

import com.my.beans.Post;
import com.my.beans.PostUser;
import com.my.email.MD5Utils;
import com.my.service.UserService;
import com.my.service.WeiboService;
import com.my.util.Upload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/21.
 */
@Controller
@RequestMapping("/blog")
public class PostController {
    @Resource
    UserService userService;
    @Resource
    WeiboService weiboService;

    private static final Logger log = Logger.getLogger(PostController.class);
    @GetMapping
    public String post(HttpSession session){
        PostUser user = (PostUser) session.getAttribute("user");
        PostUser postUser = userService.exits(user.getEmail(), user.getPassword());

        session.setAttribute("user", postUser);
        return "posts/index";
    }

    /* @PostMapping("/posts")
     public String create(String content){
         System.out.println(content);
         return "posts/index";
     }*/
   /*发布微博*/
    @PostMapping("/posts")
    public String create(Post post, String content, MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
        /*if (file!=null) {// 判断上传的文件是否为空
            String path=null;// 文件路径
            String type=null;// 文件类型
            String fileName=file.getOriginalFilename();// 文件原名称
            *//*System.out.println("上传的文件原名称:"+fileName);*//*
            log.info("上传的文件原名称:"+fileName);
            // 判断文件类型
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath=request.getSession().getServletContext().getRealPath("/assets/img");
                    // 自定义的文件名称
                   *//* String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;*//*
                    String trueFileName = MD5Utils.encode2hex(String.valueOf(System.currentTimeMillis())+fileName)+"."+type;
                    // 设置存放图片文件的路径
                    path=realPath+System.getProperty("file.separator")+trueFileName;
                    *//*System.out.println("存放图片文件的路径:"+path);*//*
                    log.info("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    *//*System.out.println("文件成功上传到指定目录下");*//*
                    log.info("文件成功上传到指定目录下");
                    log.info("request.getContextPath()："+request.getContextPath());
                    post.setPicture(request.getContextPath()+"/img/"+trueFileName);


                }else {
                    log.info("不是我们想要的文件类型,请按要求重新上传");
                    return "posts/index";
                }
            }else {
                log.info("文件类型为空");
            }
        }else {
            log.info("没有找到相对应的文件");
        }*/
        PostUser user = (PostUser) session.getAttribute("user");
        String url =  Upload.upload(file,request,4);
        log.info("url:   "+url);
        post.setUser(user);
        post.setContent(content);
        post.setSendtime(new Timestamp(new Date().getTime()));
        post.setPicture(url);
        weiboService.createWeibo(post);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        return "redirect:/blog";
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public String del(@PathVariable long id,HttpServletRequest request){
        Post post = weiboService.getPost(id);
        post.setState((long) 1);
        boolean b = weiboService.updatePost(post);
        if(b){

            try {

                String realPath = request.getSession().getServletContext().getRealPath("/assets/img");
                realPath += "\\" + post.getPicture().substring(post.getPicture().lastIndexOf("/") + 1);

                log.info(realPath);
                File file = new File(realPath);

                if (file.exists() && file.isFile()) {
                    file.delete();

                }
                return "success";

            }catch (Exception e){
                return "success";

            }
        }
        return "flag";

    }
}
