package com.hbu.whtk.dao;

import com.hbu.whtk.base.User;
import com.hbu.whtk.core.DaoImpl;
import com.hbu.whtk.util.ParamsWired;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by whtk210 on 2017-07-08.
 */
@Repository
public class UserDaoImpl extends DaoImpl<User> implements UserDao{

    @Test
    public void testSS()
    {
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/context.xml");
        UserDaoImpl userDao=   ac.getBean(UserDaoImpl.class);
        System.out.println(userDao.FindOneByUserId(new Object[]{1}).getName());
    }
    //查找用户信息用userid
    public User FindOneByUserId(Object[] params) {
        User user;
        try{
            user = this.findForObject("select  * from user where userId=?", params, User.class);
            return user;
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            return null;
        }catch (Exception e){
            return null;
        }
    }
    //查找用户信息列表
    public List<User> FindLIstByUserId(Object[] params) {
       List<User> l= this.find("select  * from user where userId=?",params,User.class);

        return l;
    }
//查找所有用户列表
    public List<User> FindALL()
    {
        List<User> l= this.find("select  * from user",null,User.class);

        return l;
    }
//查找用户 利用编号和密码（登陆）
    @Override
    public User FindOneByNumberAndPassword(Object[] params) {
        User user;

        user = this.findForObject("select  * from user where number=? and password=?", params, User.class);

        return user;
    }
//查找用户信息 bynumber
    @Override
    public User FindByNumber(Integer number) {

        User user = this.findForObject("select  * from user where number=? ", new Object[]{number}, User.class);
        return user;
    }
//删除一个用户
    @Override
    public int Delete(String[] colums,Object[] params) {


       return ParamsWired.Delete("user",colums,params,this);
    }


    //更新用户信息   updateOffset是 参数两个数组里 set 位置 和 where位置分届
    @Override
    public int Update(String[] colums,Object[] params,int updateOffset) {


        return ParamsWired.Update("user",colums,params,updateOffset,this);
    }

    //插入用户信息，
    // 如果返回插入的自增长主键，key=true，返回值是主键 >0，
    // 如果没有子增长主键 key=false，num>0 影响的记录个数
    //insertOffset暂时不管他
    // 原本设计的 insert user values（？，？）
    // ？个数 没测试，
    //随便填
    @Override
    public int Insert(String[] colums,Object[] params,int insertOffset,boolean key) {

            return ParamsWired.Insert("user",colums,params,insertOffset,this,key);

    }



}
