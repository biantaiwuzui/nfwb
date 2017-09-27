package com.my.util;

import com.my.beans.PostUser;
import com.my.email.MD5Utils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by JayJay on 2017/4/13.
 */
public class Upload {

    private static final Logger log = Logger.getLogger(Upload.class);

    /**
     * 分类保存上传的图片
     * 根据传递标识分别为：0 → 用户头像 1 → 企业图片  2 → 文章图片
     *  3 → 大招会图片 4 → 广告图片 5 → 实名认证身份证正反面
     * @param typeCode
     * @param session
     * @return 返回文件夹名称
     */
    private static String dirName(int typeCode,HttpSession session){
        String dirName = "";
        switch (typeCode){
            case 0:
                dirName =  "userImg"+System.getProperty("file.separator")+((PostUser)session.getAttribute("user")).getEmail();
                break;
            case 1:
                dirName = "enterpriseImg";
                break;
            case 2:
                dirName = "articleImg";
                break;
            case 3:
                dirName = "theAttractionImg";
                break;
            case 4:
                dirName = "advertisementImg";
                break;
            case 5:
                dirName = "verificationImg";
                break;
            default:
                dirName = "";

        }
        return dirName;
    }


    /**
     *
     * @param file  页面传上来的io流
     * @param request   request请求，用于获得项目的相对路径
     * @param typeCode 根据传递标识分别为：0 → 用户头像 1 → 企业图片  2 → 文章图片 3 → 大招会图片 4 → 广告图片 5 → 实名认证身份证正反面
     * @return  返回相对路径
     * @throws IOException
     */
    public static String upload(MultipartFile file, HttpServletRequest request,int typeCode) throws IOException {
       String url = "";
        if (file!=null) {// 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            String dirName = dirName(typeCode,request.getSession());    //获取分类目录名称
            /*System.out.println("上传的文件原名称:"+fileName);*/
            log.info("上传的文件原名称:"+fileName);
            // 判断文件类型
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath=request.getSession().getServletContext().getRealPath("/assets/img");
                    // 自定义的文件名称
                   /* String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;*/
                    String trueFileName = MD5Utils.encode2hex(String.valueOf(System.currentTimeMillis())+fileName)+"."+type;
                    File filepath = new File(realPath+System.getProperty("file.separator")+ dirName);
                   //判断该目录是否存在，不存在创建
                    if(!filepath.exists()){
                        filepath.mkdirs();
                    }
                    // 设置存放图片文件的路径
                    path=filepath+System.getProperty("file.separator")+trueFileName;
                    /*System.out.println("存放图片文件的路径:"+path);*/
                    log.info("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径

                    file.transferTo(new File(path));
                    /*System.out.println("文件成功上传到指定目录下");*/
                    log.info("文件成功上传到指定目录下");
                    log.info("request.getContextPath()："+request.getContextPath());
                    url=request.getContextPath()+"/img/"+dirName+"/"+trueFileName;

                }else {
                    log.info("不是我们想要的文件类型,请按要求重新上传");
                }
            }else {
                log.info("文件类型为空");
            }
        }else {
            log.info("没有找到相对应的文件");
        }

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return url;
    }

}
