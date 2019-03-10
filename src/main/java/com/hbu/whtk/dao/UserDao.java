package com.hbu.whtk.dao;

import com.hbu.whtk.base.User;

import java.util.List;

/**
 * Created by whtk210 on 2017-07-08.
 */
public interface UserDao {

    public User FindOneByUserId(Object[] params);
    public List<User> FindALL();
    public List<User> FindLIstByUserId(Object[] params);
    public User FindOneByNumberAndPassword(Object[] params);
    public User FindByNumber(Integer number);
    public int Delete(String[] colums,Object[] params);
    public int Update(String[] colums,Object[] params,int updateOffset);
    public int Insert(String[] colums,Object[] params,int insertOffset,boolean key);

}
