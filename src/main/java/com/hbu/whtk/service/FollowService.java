package com.hbu.whtk.service;

import com.hbu.whtk.base.Anchor;
import com.hbu.whtk.base.Follow;
import com.hbu.whtk.base.User;
import com.hbu.whtk.dao.AnchorDao;
import com.hbu.whtk.dao.FollowDao;
import com.hbu.whtk.dao.UserDao;
import com.hbu.whtk.goback.BackUserAndAnchor;
import com.hbu.whtk.goback.Backfollowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FollowService {

    @Autowired
    FollowDao followDao;
    @Autowired
    UserDao userDao;
    @Autowired
    AnchorDao anchorDao;
    //删除关注（没写返回的json结构）
    public int delete(String[] colums,Object[] params)
    {
      //
        // 删除一个记录，修改两个位置 关注数量和被关注数量 分别在用户表和主播表
        //没写返回的json结构
        int  num=followDao.Delete(colums,params);

        return num;

    }
    //更新关注（没写返回的json结构） updateOffset 见followDao.Update
    public int update(String[] colums,Object[] params,int updateOffset)
    {
        //没写返回的json结构
        int  num=followDao.Update(colums,params,updateOffset);
        return num;
    }
    //插入一个条关注 （没写返回的json结构）  insertOffset 见followDao.Insert
    public int insert(String[] colums,Object[] params,int insertOffset ,boolean key)
    {//插入一个记录，修改两个位置 关注数量和被关注数量 分别在用户表和主播表
        int  num=followDao.Insert(colums,params,insertOffset,key);
        //还缺两个update
        return num;
    }

    public  Map<String,Object>  getAnchorFlowers(int anchorid)
    {
        Map<String,Object> map=new HashMap<String,Object>();
        List<Follow> l=null;
        List<Backfollowed> list=new ArrayList<Backfollowed>();
        try {
           l = followDao.getAnchorFlowers(anchorid);
        }catch(EmptyResultDataAccessException e) {

            map.put("status",-10);
            map.put("msg","没有粉丝或者没查到记录");
            map.put("num",0);
            map.put("content",list);
            return map;
        }catch(DataAccessException e) {
            map.put("status",-11);
            map.put("msg","没有粉丝或者没查到记录");
            map.put("num",0);
            map.put("content",list);
            return map;
        }
        if(l.size()==0)
        {
            map.put("status",-12);
            map.put("msg","没有粉丝");
            map.put("num",0);
            map.put("content",list);
            return map;
        }
       Map<String,Object> map1= getBackfollowedListByUserIdList(l);
        return map1;
    }

    public   Map<String,Object> getBackfollowedListByUserIdList(List<Follow> list){
        List<Backfollowed> l=new ArrayList<Backfollowed>();

        Backfollowed backfollowed=null;
        User  user=null;
        Map<String,Object> map=new HashMap<String,Object>();
        List<User> uList=new ArrayList<User>();
//如果没有参数
        try{
            List<Integer> valueList = new ArrayList<Integer>();
             for(Follow o :  list){valueList.add(o.getUserId());}

            for(Integer o :  valueList)
            {
              user=userDao.FindOneByUserId(new Object[]{o});

              uList.add(user);
            }

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
              /*  for (User user : uList , Follow f: list) {

                    backfollowed = BackfollowedWired(user);

                    l.add(backfollowed);
                }//endfor*/

              for(int i=0;i<uList.size();i++)
              {
                  backfollowed = BackfollowedWired(uList.get(i));
                  backfollowed.setFollowTime(list.get(i).getFollowTime());
                  l.add(backfollowed);
              }
            }//endif
            /*if(list.size()!=0)
            {
                for (Follow f : list) {
                    backfollowed = BackfollowedWired(backfollowed);
                }
            }*/
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


    public Backfollowed BackfollowedWired(User user)
    {
        Anchor anchor;
        Backfollowed backfollowed=new Backfollowed();
        int userId = user.getUserId();
        backfollowed.setUserId(userId);
        backfollowed.setOrganizationName(user.getOrganizationName());
        int number = user.getNumber();
        backfollowed.setNumber(number);
        backfollowed.setPassword(user.getPassword());
        backfollowed.setName(user.getName());
        backfollowed.setCollege(user.getCollege());
        backfollowed.setNickName(user.getNickName());
        backfollowed.setAvatar(user.getAvatar());
        backfollowed.setSignature(user.getSignature());
        backfollowed.setPhoneNumber(user.getPhoneNumber());
        int wa=user.getWhetherAnchor();
        backfollowed.setWhetherAnchor(wa);
        backfollowed.setFollowNumber(user.getFollowNumber());
        backfollowed.setRegistrationTime(user.getRegistrationTime());

        //如果是主播
        if (wa == 1) {

            anchor=  anchorDao.FindOneByAnchorId(new Object[]{userId});
            backfollowed.setAnchorCategory(anchor.getAnchorCategory());
            backfollowed.setPlayTime(anchor.getPlayTime());
            backfollowed.setTotalUpvoteNum(anchor.getTotalUpvoteNum());
            backfollowed.setFollowedNumber(anchor.getFollowedNumber());
            backfollowed.setApplyAnchorTime(anchor.getApplyAnchorTime());
            backfollowed.setTotalGiftsNumber(anchor.getTotalGiftsNumber());
        }
        return backfollowed;

    }

}
