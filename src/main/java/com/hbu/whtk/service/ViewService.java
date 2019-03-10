package com.hbu.whtk.service;

import com.hbu.whtk.dao.ViewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2018/2/24.
 */
@Service
public class ViewService {
    @Autowired
    ViewDao viewDao;

    public Map<String,Object> addViewer(String liveId, String userId){
        Map<String,Object> map=new HashMap<String,Object>();
        int result=viewDao.insertOneViewer(liveId,userId);
        if(result==1){
            map.put("status",1);
            map.put("msg","成功添加观看者");
        }else if(result==0){
            map.put("status",0);
            map.put("msg","直播id或者用户id不合法");
        }else if(result==-1){
            map.put("status",-1);
            map.put("msg","发生异常");
        }else  if(result==2){
            map.put("status",2);
            map.put("msg","观看表中已存在该记录");
        }
        return  map;
    }

    public Map<String,Object> deleteViewer(String liveId, String userId){
        Map<String,Object> map=new HashMap<String,Object>();
        int result=viewDao.deleteOneViewer(liveId,userId);
        if(result==1){
            map.put("status",1);
            map.put("msg","成功删除观看者");
        }else if(result==-1){
            map.put("status",-1);
            map.put("msg","发生异常");
        }else  if(result==0){
            map.put("status",0);
            map.put("msg","观看表中不已存在该记录");
        }
        return  map;
    }

}
