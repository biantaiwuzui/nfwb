package com.my.dao;


import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
@Repository
public interface BaseDAO<T> {
    T getById(Class<T> clazz,long id);
    T get(String hql,Object...objs);
    long save(T t);
    void remove(T t);
    void update(T t);
    int count();
    List<T> listAll(String hql,Object...objs);
}
