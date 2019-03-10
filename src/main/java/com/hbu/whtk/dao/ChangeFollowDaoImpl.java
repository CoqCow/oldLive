package com.hbu.whtk.dao;
import com.hbu.whtk.base.Follow;
import com.hbu.whtk.core.DaoImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/7/16.
 */
@Repository
public class ChangeFollowDaoImpl extends DaoImpl<Follow> implements ChangeFollowDao {


    @Override
    public Map<String, Object> changeFollow(Object[] userParams, Object[] anchorParams, String flag) {
        Map<String,Object> map=new HashMap<>();
        Object[] followParams=new Object[]{userParams[0],anchorParams[0]};
        try {
            int flag1=0;
            int flag2=0;
            int flag3=0;
            //普通用户关注主播
            if(flag.equals("1")) {
                //follow表中没有关注记录（即该用户没有关注该主播）
                if(!this.checkFollow(followParams)) {
                    //follow表添加项
                    flag3 = this.addOrUpdateOrDelete("insert into follow(userId ,anchorId ,followTime )values(?,?,now())", followParams, null);
                    flag2 = this.addOrUpdateOrDelete("update anchor set followedNumber=followedNumber+1 where userId =?", anchorParams, null);
                    flag1 = this.addOrUpdateOrDelete("update user set followNumber =followNumber+1 where userId =?", userParams, null);
                    map.put("status", 1);
                    map.put("msg", "成功返回json");
                }else{
                    map.put("status", 0);
                    map.put("msg", "更新失败，可能该用户已经关注改主播");
                }
            }
            //普通用户取消关注主播
            if(flag.equals("0")){
                //follow表中有关注记录
                if(this.checkFollow(followParams)) {
                    //follow表中有关注记录(即该用户关注了该主播)
                    flag2 = this.addOrUpdateOrDelete("update anchor set followedNumber=followedNumber-1 where userId =?", anchorParams, null);
                    flag1 = this.addOrUpdateOrDelete("update user set followNumber =followNumber-1 where userId =?", userParams, null);
                    flag3 = this.addOrUpdateOrDelete("delete from follow where userId=? and anchorId=?", followParams, null);
                    map.put("status", 1);
                    map.put("msg", "成功返回json");
                }else{
                    map.put("status", 0);
                    map.put("msg", "更新失败，可能该用户没有关注改主播");
                }

            }

        }catch (Exception e){
            map.put("status", -1);
            map.put("msg", "更新异常");
        }
        return map;
    }
    //这是判断关注表有没有记录的函数
    public boolean checkFollow(Object[] followParams){
        boolean b=false;
        Follow follow=null;
        try {
            follow=this.findForObject("select * from follow where userId=? and anchorId=? ",followParams,Follow.class);
           if(follow!=null)
            b=true;
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            b=false;
            System.out.println("无结果");
        }catch (Exception e){
            b=false;
        }
        return b;
    }
}
