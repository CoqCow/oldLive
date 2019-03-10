package com.hbu.whtk.service;

import com.hbu.whtk.base.User;
import com.hbu.whtk.dao.BindPhoneDao;
import com.hbu.whtk.dao.UserDao;
import com.hbu.whtk.goback.BackUserAndAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by whtk210 on 2017-07-08.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    BindPhoneDao bindPhoneDao;
    @Autowired
    UserAndAnchorService userAndAnchorService;
    //查询用户信息靠userid（没写返回的json结构）
    public User findOneByUserId(Object[] params)
    {
        User user;

       user=userDao.FindOneByUserId(params);

        return user;

    }
    //查询用户列表信息靠userid列表（没写返回的json结构）
    public List<User>  findListByUserId(Object[] params)
    {
        List<User> l=userDao.FindLIstByUserId(params);
        return l;
    }

    //查询所有用户（没写返回的json结构）
    public List<User>  findAll()
    {
        List<User> l=userDao.FindALL();
        return l;
    }
//删除一个用户（没写返回的json结构）
    public int delete(String[] colums,Object[] params)
    {
        int  num=userDao.Delete(colums,params);
        return num;
    }
    //更新用户信息（没写返回的json结构）
    public int update(String[] colums,Object[] params,int updateOffset)
    {
        int  num=userDao.Update(colums,params,updateOffset);

        return num;
    }
    //检查是否有重复的number（没写返回的json结构）
    public User findChongfu(Integer number)
    {


         User user=userDao.FindByNumber(number);

        return user;
    }
    //查找用户bynumber（没写返回的json结构）
    public int findUseridByNumber(Integer number)
    {
        User user=null;
        user=userDao.FindByNumber(number);

        if(user!=null) return user.getUserId();
        return -1;
    }
    //用户注册，相当于插入用户表一条记录
    public Map<String,Object> reg(String[] colums,Object[] params,int insertOffset)
    {
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        Map<String,Object> map=new HashMap<String,Object>();
        int  num=-1;
        User user=null;
        BackUserAndAnchor backUserAndAnchor=null;
        if(params[1]==null)
        {
            map.put("status",-1);
            map.put("msg","编号为空");
            map.put("num",0);
            map.put("content",l);

        }
        try {
            findChongfu((Integer) params[1]);
            map.put("status",-2);
            map.put("msg","有此编号的用户");
            map.put("num",0);
            map.put("content",l);

            return map;
        }catch(EmptyResultDataAccessException e) {


        }catch(DataAccessException e)
        {
            map.put("status", -3);
            map.put("msg", e.getMessage());
            map.put("num",0);
            map.put("content",l);

            e.printStackTrace();
            return map;
        }
        try {///插入
            num = userDao.Insert(colums, params, insertOffset,true);
        } catch (DataAccessException e) {
            map.put("status", -4);
            map.put("msg", e.getMessage());
            map.put("num",0);
            map.put("content",l);

            e.printStackTrace();
            return map;
        }

        if(num>0) {//num就是主键
            try {

              //  int uid = findUseridByNumber((Integer) params[1]);
                Map<String,Object> m= userAndAnchorService.getBackUserAndAnchorByUserId(new Object[]{num});
                List<BackUserAndAnchor> list=(List<BackUserAndAnchor> )m.get("content");
                backUserAndAnchor=list.get(0);
            } catch (EmptyResultDataAccessException e) {
                map.put("status", -5);
                map.put("msg", "没有此编号的用户");
                map.put("num",0);
                map.put("content",l);

                return map;
            } catch (DataAccessException e) {
                map.put("status", -6);
                map.put("msg", e.getMessage());
                e.printStackTrace();
                map.put("num",0);
                map.put("content",l);

                return map;
            }

            if (backUserAndAnchor != null) {
                map.put("status", 1);
                map.put("msg", "成功返回用户信息");
                map.put("num", 1);

                l.add(backUserAndAnchor);
                map.put("content", l);

            }

        }
        else{
            map.put("status", -7);
            map.put("msg", "插入数据库失败（外键，空值，唯一）");
            map.put("num", 0);
            map.put("content", l);
        }
        return map;
    }

    //绑定手机业务
    public Map<String,Object> bindPhone(Object[] params){

        BackUserAndAnchor backUserAndAnchor=null;
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        Map<String,Object> map=new HashMap<String,Object>();

        int flag=-1;
        flag=bindPhoneDao.bind(params);
        if(flag==1){
            map.put("status", 1);
            map.put("msg", "成功绑定手机");
            map.put("num", 0);
            l.add(backUserAndAnchor);
            map.put("content", l);
        }else {
            map.put("status", -1);
            map.put("msg", "绑定手机失败");
            map.put("num",0);
            map.put("content",l);
        }
        return map;
    }


    public Map<String,Object> changePassword(String[] colums, Object[] params, int updateOffset)
    {
        String psw=(String)params[0];
        String uid=(String)params[0];
        HashMap<String,Object> map=new HashMap<String,Object>();
        int num=-1;

        try {
            if (psw != null && uid != null) {
                 User user=findOneByUserId(new Object[]{ params[1]});
                 num=userDao.Update(colums,params,updateOffset);
                if (num > 0) {
                      map.put("status", 1);
                      map.put("msg", "成功修改密码");
                }else{
                    map.put("status", 0);
                    map.put("msg", "修改失败");
                }
            }else {
                map.put("status", 0);
                map.put("msg", "密码或者用户id为空");
            }
        }catch(EmptyResultDataAccessException emptyResultDataAccessException) {
                map.put("status", 0);
                map.put("msg", "无此用户");
            emptyResultDataAccessException.printStackTrace();
        }catch(DataAccessException e)
        {
            map.put("status", 0);
            map.put("msg", e.getMessage());
            e.printStackTrace();
        }
       return map;
    }
}
