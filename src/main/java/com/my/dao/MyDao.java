package com.my.dao;

import com.my.dao.BaseDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */
@Repository
public class MyDao<T> implements BaseDAO<T> {
    @Resource
    SessionFactory sessionFactory;

    public  Session getSession(){
        return sessionFactory.getCurrentSession(); }

    @Override
    public T getById(Class<T> clazz, long id) {
        T t = this.getSession().get(clazz, id);
        return t;
    }

    @Override
    public T get(String hql, Object...objs) {
        Query query =  this.getSession().createQuery(hql);
        if(objs != null && objs.length>0){
            for(int i=0;i <objs.length;i++){
                query.setParameter(i,objs[i]);

            }
        }
        T t  = (T) query.uniqueResult();
        return t;
    }
    @Override
    public List<T> listAll(String hql, Object... objs) {
        Query query =  this.getSession().createQuery(hql);
        if(objs != null && objs.length>0){
            for(int i=0;i <objs.length;i++){
                query.setParameter(i,objs[i]);

            }
        }
        List<T> list  = query.list();
        return list;
    }

    @Override
    public long save(T t) {
        long save_id = (long) this.getSession().save(t);
        return save_id;
    }

    @Override
    public void remove(T t) {

    }

    @Override
    public void update(T t) {
        Session session = this.getSession();
        session.update(t);
    }

    @Override
    public int count() {
        return 0;
    }




}
