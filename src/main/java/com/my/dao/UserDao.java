package com.my.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/2/16.
 */
@Repository
public class UserDao extends MyDao {
    @Override
    public long save(Object o) {
        try {
            this.getSession().save(o);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
