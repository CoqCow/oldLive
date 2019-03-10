package com.hbu.whtk.service;

import com.hbu.whtk.base.Anchor;
import com.hbu.whtk.base.User;
import com.hbu.whtk.dao.AnchorDao;
import com.hbu.whtk.dao.UserDao;
import com.hbu.whtk.goback.BackUserAndAnchor;
import com.hbu.whtk.util.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by whtk210 on 2017-07-18.
 */
@Service
public class UserAndAnchorService {
    @Autowired
    UserDao userDao;
    @Autowired
    AnchorDao anchorDao;

    @Autowired
    LoginUser loginUser;
    //得到所有用户表主播表信息
    public   Map<String,Object> getAllBackUserAndAnchorList(){
        List<BackUserAndAnchor> l=new ArrayList<BackUserAndAnchor>();

        BackUserAndAnchor backUserAndAnchor=null;

       Map<String,Object> map=new HashMap<String,Object>();
        List<User> uList=null;
//如果没有参数
      try{
          uList=userDao.FindALL();
      }catch(EmptyResultDataAccessException emptyResultDataAccessException)
      {
        map.put("status",-1);
        map.put("msg","没有用户记录");
        map.put("num",0);
        map.put("content",l);
        return map;
    }catch (DataAccessException e)
    {
        map.put("status",-2);
        map.put("msg","数据访问异常");
        map.put("num",0);
        map.put("content",l);
        return map;
    }
    try {
        if (uList.size() != 0) {
            for (User user : uList) {

                backUserAndAnchor = BackUserAndAnchorWired(user);

                l.add(backUserAndAnchor);
            }//endfor
        }//endif
    }catch(EmptyResultDataAccessException emptyResultDataAccessException)
    {
        map.put("status",-3);
        map.put("msg","没有主播记录");
        map.put("num",0);
        map.put("content",l);
        return map;
    }catch (DataAccessException e)
    {
        map.put("status",-4);
        map.put("msg","数据访问异常");
        map.put("num",0);
        map.put("content",l);
        return map;
    }
    if(l.size()!=0) {
        map.put("status",1);
        map.put("msg","成功返回json");
        map.put("num",l.size());

        map.put("content",l);
    }
      return map;
    }

    //得到用户表主播表信息 by userid list
    public   Map<String,Object> getBackUserAndAnchorListByUserIdList(Object[] list){
        List<BackUserAndAnchor> l=new ArrayList<BackUserAndAnchor>();

        BackUserAndAnchor backUserAndAnchor=null;

        Map<String,Object> map=new HashMap<String,Object>();
        List<User> uList=null;
//如果没有参数
        try{
            uList=userDao.FindLIstByUserId(list);
        }catch(EmptyResultDataAccessException emptyResultDataAccessException)
        {
            map.put("status",-1);
            map.put("msg","没有用户记录");
            map.put("num",0);
            map.put("content",l);
            return map;
        }catch (DataAccessException e)
        {
            map.put("status",-2);
            map.put("msg","数据访问异常");
            map.put("num",0);
            map.put("content",l);
            return map;
        }
        try {
            if (uList.size() != 0) {
                for (User user : uList) {

                    backUserAndAnchor = BackUserAndAnchorWired(user);

                    l.add(backUserAndAnchor);
                }//endfor
            }//endif
        }catch(EmptyResultDataAccessException emptyResultDataAccessException)
        {
            map.put("status",-3);
            map.put("msg","没有主播记录");
            map.put("num",0);
            map.put("content",l);
            return map;
        }catch (DataAccessException e)
        {
            map.put("status",-4);
            map.put("msg","数据访问异常");
            map.put("num",0);
            map.put("content",l);
            return map;
        }
        if(l.size()!=0) {
            map.put("status",1);
            map.put("msg","成功返回json");
            map.put("num",l.size());

            map.put("content",l);
        }
        return map;
    }


        // /得到所有用户表主播表信息 by userid
    public Map<String,Object> getBackUserAndAnchorByUserId(Object[] params){
        BackUserAndAnchor backUserAndAnchor=null;

        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        Map<String,Object> map=new HashMap<String,Object>();
        User user=null;
        try{
            user =userDao.FindOneByUserId(params);
        }
        catch(EmptyResultDataAccessException emptyResultDataAccessException)
        {
            map.put("status",-1);
            map.put("msg","没有用户记录");
            map.put("num",0);
            map.put("content",l);
            return map;
        }catch (DataAccessException e)
        {
            map.put("status",-2);
            map.put("msg","数据访问异常");
            map.put("num",0);
            map.put("content",l);
            return map;
        }


     try {
         backUserAndAnchor = BackUserAndAnchorWired(user);
     }catch(EmptyResultDataAccessException emptyResultDataAccessException)
     {
         map.put("status",-3);
         map.put("msg","没有主播记录");
         map.put("num",0);
         map.put("content",l);
         return map;
     }catch (DataAccessException e)
     {
         map.put("status",-4);
         map.put("msg","数据访问异常");
         map.put("num",0);
         map.put("content",l);
         return map;
     }

        if(backUserAndAnchor!=null)
        {

            map.put("status",1);
            map.put("msg","成功返回json");
            map.put("num",1);

            l.add(backUserAndAnchor);
            map.put("content",l);

        }
        return map;
    }
    //得到所有用户表主播表信息 by number
    public Map<String,Object> getBackUserAndAnchorByNumberAndPassword(Object[] params){
        BackUserAndAnchor backUserAndAnchor=null;
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        User user=null;
        try{
            user=userDao.FindOneByNumberAndPassword(params);

        }catch(EmptyResultDataAccessException emptyResultDataAccessException)
        {
            map.put("status",-1);
            map.put("msg","没有用户记录");
            map.put("num",0);
            map.put("content",l);
            return map;
        }catch (DataAccessException e)
        {
            map.put("status",-2);
            map.put("msg","数据访问异常");
            map.put("num",0);
            map.put("content",l);
            return map;
        }
        try{
            backUserAndAnchor=   BackUserAndAnchorWired(user);
    //  request.getSession().setAttribute("user1",user);
        }catch(EmptyResultDataAccessException emptyResultDataAccessException)
        {
            map.put("status",-3);
            map.put("msg","没有主播记录");
            map.put("num",0);
            map.put("content",l);
            return map;
        }catch (DataAccessException e)
        {
            map.put("status",-4);
            map.put("msg","数据访问异常");
            map.put("num",0);
            map.put("content",l);
            return map;
        }
        if(backUserAndAnchor!=null)
        {
        //此时验证账号密码成功  需要验证该用户是否已经登录
            if(checkUserLoginStatus(backUserAndAnchor.getUserId())==false){
                map.put("status",-5);
                map.put("msg","用户已在其他设备登录");
                map.put("num",0);
                map.put("content",l);

            }else {
                map.put("status",1);
                map.put("msg","成功返回json");
                map.put("num",1);

                l.add(backUserAndAnchor);
                map.put("content",l);
            }

        }
        return map;
    }
    //检查该用户是否可以登录（单点登录） 可以登录返回true  不可以返回false
    public boolean checkUserLoginStatus(int id)  {

        if(loginUser.isLogin(id)==false){
            //该用户没有登录
            loginUser.refreshLoginUser(id);
            return true;
        }else {
            //该用户登录过 判断是否登录是否过期
            if(loginUser.isOutTime(id)){
                loginUser.clearOneLoginUser(id);
                loginUser.refreshLoginUser(id);
                return true;
            }else {
                return false;
            }


/*
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //得到该用户最后登录时间
            Date date=loginUser.getUserLoginTime(id);

            //超过某个时间后 之前登录失效
            Date now=new Date();
            System.out.println(date);
            System.out.println(now);
            String fromDate = simpleFormat.format(date);
            String toDate = simpleFormat.format(now);
            try{
                long from = simpleFormat.parse(fromDate).getTime();
                long to = simpleFormat.parse(toDate).getTime();
                long hours = ((to - from)/(1000 *60*60)); //1000 *60*60 = 一个小时
                //System.out.println("已经登录了：");
                long seconds=((to-from)/1000);
                System.out.println("to-from="+(to-from)+"hours="+hours+"seconds="+seconds);
                if(seconds>10){
                    //loginUser.
                    loginUser.clearOneLoginUser(id);
                    loginUser.refreshLoginUser(id);
                    return true;
                }else {
                    return false;
                }
            }catch (Exception e){
                //应该执行不到这里吧
                System.out.println("应该执行不到这里吧");
                e.printStackTrace();
                return true;
            }*/

        }
    }
    //用户退出登录
    public boolean logout(int id){
        if(loginUser.isLogin(id)==false){
            return false;
        }else {
            loginUser.clearOneLoginUser(id);
            return true;
        }

    }
    //更新用户在线状态
    public boolean refreshLoginStatus(int id){
        return loginUser.refreshLoginTime(id);
    }
    //将用户 包裹成用户和主播一起的信息
    public BackUserAndAnchor BackUserAndAnchorWired(User user)
    {
        Anchor anchor;
        BackUserAndAnchor backUserAndAnchor=new BackUserAndAnchor();
        int userId = user.getUserId();
        backUserAndAnchor.setUserId(userId);
        backUserAndAnchor.setOrganizationName(user.getOrganizationName());
        int number = user.getNumber();
        backUserAndAnchor.setNumber(number);
        backUserAndAnchor.setPassword(user.getPassword());
        backUserAndAnchor.setName(user.getName());
        backUserAndAnchor.setCollege(user.getCollege());
        backUserAndAnchor.setNickName(user.getNickName());
        backUserAndAnchor.setAvatar(user.getAvatar());
        backUserAndAnchor.setSignature(user.getSignature());
        backUserAndAnchor.setPhoneNumber(user.getPhoneNumber());
        int wa=user.getWhetherAnchor();
        backUserAndAnchor.setWhetherAnchor(wa);
        backUserAndAnchor.setFollowNumber(user.getFollowNumber());
        backUserAndAnchor.setRegistrationTime(user.getRegistrationTime());

        //如果是主播
        if (wa == 1) {

            anchor=  anchorDao.FindOneByAnchorId(new Object[]{userId});
            backUserAndAnchor.setAnchorCategory(anchor.getAnchorCategory());
            backUserAndAnchor.setPlayTime(anchor.getPlayTime());
            backUserAndAnchor.setTotalUpvoteNum(anchor.getTotalUpvoteNum());
            backUserAndAnchor.setFollowedNumber(anchor.getFollowedNumber());
            backUserAndAnchor.setApplyAnchorTime(anchor.getApplyAnchorTime());
            backUserAndAnchor.setTotalGiftsNumber(anchor.getTotalGiftsNumber());
        }
        return backUserAndAnchor;

    }

}
