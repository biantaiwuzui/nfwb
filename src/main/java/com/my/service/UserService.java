package com.my.service;

import com.my.beans.PostUser;
import com.my.dao.UserDao;
import com.my.email.MD5Utils;
import com.my.email.SendEmail;
import org.hibernate.Hibernate;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
@Service
@Transactional
public class UserService {
    @Resource
    UserDao userDao;
  /* @Resource
    IDao iDao;*/

    /*注册*/
    public boolean regist(PostUser postUser){
        long userid = userDao.save(postUser);
        return userid!=0 ? true:false;
    }
    /*检查帐号是否存在*/
    public PostUser exits(String email, String password){
        PostUser postUser = (PostUser) userDao.get("From PostUser where email=? and password=?", email, password);

        Hibernate.initialize(postUser.getPosts());
        Hibernate.initialize(postUser.getFans());
        Hibernate.initialize(postUser.getParticipant());
        Hibernate.initialize(postUser.getComments());
        return postUser;
    }

    /*查询所有激活的用户*/
    public List<PostUser> userList(int userid){
        List<PostUser> list = userDao.listAll("From PostUser where cdkeystate=1 and userid not in (?)",userid);

        return list;
    }
    /*查询某个用户*/
    public PostUser getUser(int userid){
        PostUser postUser = (PostUser) userDao.get("From PostUser where userid=?",userid);
        Hibernate.initialize(postUser.getPosts());
        Hibernate.initialize(postUser.getFans());
        Hibernate.initialize(postUser.getParticipant());
        Hibernate.initialize(postUser.getComments());
        return postUser;
    }


    /**
     * 处理注册
     */

    public void processregister(PostUser postUser){
       postUser.setCdkeystate( 0);
       postUser.setCdkeytime(new Timestamp(new Date().getTime()));
       postUser.setSex( 0);
       postUser.setAge( 11);
       postUser.setFace("/img/1.jpg");
        ///如果处于安全，可以将激活码处理的更复杂点，这里我稍做简单处理
        //user.setValidateCode(MD5Tool.MD5Encrypt(email));
       postUser.setCdkey(MD5Utils.encode2hex(postUser.getEmail()));
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip=addr.getHostAddress().toString();//获得本机IP


        ///邮件的内容
        StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://"+ip+":8080/user/activate?email=");
        sb.append(postUser.getEmail());
        sb.append("&validateCode=");
        sb.append(postUser.getCdkey());
        sb.append("\">http://"+ip+":8080/user/activate?email=");
        sb.append(postUser.getEmail());
        sb.append("&validateCode=");
        sb.append(postUser.getCdkey());
        sb.append("</a>");

        //发送邮件
        SendEmail.send(postUser.getEmail(), sb.toString());
        System.out.println("发送邮件");
        long save = userDao.save(postUser);//保存注册信息

    }

    /**
     * 处理激活
     * @throws ParseException
     */
    ///传递激活码和email过来
    public void processActivate(String email , String validateCode)throws ServiceException, ParseException {
        //数据访问层，通过email获取用户信息
        PostUser postUser = (PostUser) userDao.get("From PostUser where email=?", email);
        //验证用户是否存在
        if(postUser !=null) {
            //验证用户激活状态
                ///没激活
                Timestamp currentTime =new Timestamp(new Date().getTime());//获取当前时间
                //验证链接是否过期
            /*    currentTime.before(postUser.getCdkeytime());*/
                System.out.println(currentTime.getTime());
                System.out.println(postUser.getCdkeytime().getTime());
                System.out.println((currentTime.getTime()- postUser.getCdkeytime().getTime())/(1000*60*60)<48);
                if(postUser.getCdkeystate()==0){
                if((currentTime.getTime()- postUser.getCdkeytime().getTime())/(1000*60*60)<48) {
                    //验证激活码是否正确
                    if(validateCode.equals(postUser.getCdkey())) {
                        //激活成功， //并更新用户的激活状态，为已激活
                        System.out.println("==sq==="+ postUser.getCdkeystate());
                        postUser.setCdkeystate(1);//把状态改为激活
                        System.out.println("==sh==="+ postUser.getCdkeystate());
                        userDao.update(postUser);
                    } else {
                        throw new ServiceException("激活码不正确");
                    }
                } else { throw new ServiceException("激活码已过期！");
                }
            } else {
                throw new ServiceException("邮箱已激活，请登录！");
            }

        } else {
            throw new ServiceException("该邮箱未注册（邮箱地址不存在）！");
        }

    }



}
