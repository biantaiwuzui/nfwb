package com.my.service;

import com.my.beans.Post;
import com.my.dao.WeiboDao;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */
@Service
@Transactional
public class WeiboService {
    @Resource
    private WeiboDao weiboDao;

    /*添加一条微博*/
    public boolean createWeibo(Post post){
        long save_id = weiboDao.save(post);
        return save_id!=0?true:false;
    }
    /*查询所有*/
    public List<Post> weiboLists(int userid){
        List<Post> list = weiboDao.listAll("From Weibo where user=?", userid);
        return list;
    }
    public Post getPost(long id){
        Post post = (Post) weiboDao.get("From Post where pid=?",id);

        return post;
    }
    public boolean updatePost(Post post){
        try {
         weiboDao.update(post);
         return true;

        }catch (Exception e){
            return false;
        }

    }

}
