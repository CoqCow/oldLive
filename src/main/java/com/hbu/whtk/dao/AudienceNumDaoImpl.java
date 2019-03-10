package com.hbu.whtk.dao;

import com.hbu.whtk.base.AudienceNum;
import com.hbu.whtk.core.DaoImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/7/14.
 */
@Repository
public class AudienceNumDaoImpl extends DaoImpl<AudienceNum> implements AudienceNumDao {

    @Override
    public AudienceNum FindOne(Object[] params) {
        AudienceNum audienceNum;
        try {
            audienceNum = this.findForObject("select audienceNum from live where userId=?", params, AudienceNum.class);
        }catch(EmptyResultDataAccessException emptyResultDataAccessException)
        {
            audienceNum =null;
        }catch (Exception e){
            audienceNum =null;
        }
        return audienceNum;
    }

    @Override
    public Map<String, Object> findAudienceNumByLiveId(Object[] params) {
        Map<String, Object> map=new HashMap<>();
        AudienceNum audienceNum;
        try {
            audienceNum = this.findForObject("select audienceNum from live where liveId=?", params, AudienceNum.class);
            map.put("status", 1);
            map.put("msg", "成功返回json");
            ArrayList<AudienceNum> list=new ArrayList<AudienceNum>();
            list.add(audienceNum);
            map.put("content",list);
            map.put("num",list.size());
        }catch(EmptyResultDataAccessException emptyResultDataAccessException)
        {
            audienceNum =null;
            map.put("status", 0);
            map.put("msg", "没有结果");
           // System.out.println("-------------------测试3----------------------------");
        }catch (Exception e){
            audienceNum =null;
            map.put("status", -1);
            map.put("msg", "查找异常");
           // System.out.println("-------------------测试4----------------------------");
        }
        return map;
    }
}
