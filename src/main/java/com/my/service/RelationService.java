package com.my.service;

import com.my.beans.Post;
import com.my.beans.PostUser;
import com.my.beans.Relations;
import com.my.dao.RelationDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.management.relation.Relation;

/**
 * Created by Administrator on 2017/2/22.
 */
@Service
@Transactional
public class RelationService {
    @Resource
    RelationDao relationDao;

    public void del(Relations relations){
        relationDao.update(relations);
    }

    public boolean addFriends(int user_id,int by_user_id){
        PostUser user = (PostUser) relationDao.get("From PostUser where userid=?", user_id);
        PostUser by_user = (PostUser) relationDao.get("From PostUser where userid=?",by_user_id);
        Relations relations = new Relations();
        relations.setUser(user);
        relations.setBy_user(by_user);
        long save_id = relationDao.save(relations);
        return save_id==0?false:true;

    }

    /*查询是否存在*/
    public Relations getRelations(int user_id,int user_id2){
        Relations relations = (Relations) relationDao.get("From Relations where user="+user_id+" and by_user="+user_id2);
        return relations;

    }
}
