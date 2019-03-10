package com.hbu.whtk.dao;

import com.hbu.whtk.base.UpvoteNum;
import com.hbu.whtk.base.UserUpvoteNum;
import com.hbu.whtk.core.DaoImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/7/19.
 */
@Repository
public class UpvoteDaoImpl extends DaoImpl<UpvoteNum> implements UpvoteDao{

    @Override
    //这是用户给某直播点赞，并得到该用户的点赞总数
    public Map<String, Object> doUpvote(Object[] liveParams, Object[] viewParams){
        Map<String,Object> map=new HashMap<>();
        UserUpvoteNum userUpvoteNum;
        int flag1=0,flag2=0;

        //更新观看表用户点赞总数
        flag2=this.addOrUpdateOrDelete("update view set userUpvoteNum=userUpvoteNum+1 where liveId =? and userId=?",viewParams,null);
        //在用户观看直播的情况下才能点赞
        if(flag2==1){
            //更新直播表获赞总数
           flag1=this.addOrUpdateOrDelete("update live set upvoteNum=upvoteNum+1 where liveId =?",liveParams,null);
            //
            if(flag1==1){
                try {
                    userUpvoteNum=this.findForObject("select userUpvoteNum from view where liveId=? and userId=?", viewParams, UserUpvoteNum.class);
                    map.put("status", 1);
                    map.put("msg", "成功返回json");
                    ArrayList<UserUpvoteNum> list=new ArrayList<UserUpvoteNum>();
                    list.add(userUpvoteNum);
                    map.put("content",list);
                    map.put("num",list.size());
                }catch(EmptyResultDataAccessException emptyResultDataAccessException){
                    map.put("status", 0);
                    map.put("msg", "更新异常");
                }
            }
        }else{
            map.put("status", -1);
            map.put("msg", "更新失败,可能此用户没有观看此直播");
        }

        return map;
    }
    //这是得到某直播获得的点赞个数
    @Override
    public Map<String, Object> getUpvote(Object[] liveParams) {
        Map<String,Object> map=new HashMap<>();
        UpvoteNum upvoteNum;
            try {
                upvoteNum=this.findForObject("select upvoteNum from live where liveId=?", liveParams, UpvoteNum.class);
                map.put("status", 1);
                map.put("msg", "成功返回json");
                ArrayList<UpvoteNum> list=new ArrayList<UpvoteNum>();
                list.add(upvoteNum);
                map.put("content",list);
                map.put("num",list.size());
            }catch(EmptyResultDataAccessException emptyResultDataAccessException){
                map.put("status", 0);
                map.put("msg", "没有结果");
            }catch (Exception e){
                map.put("status", -1);
                map.put("msg", "查找异常");
            }
        return map;
    }
}
