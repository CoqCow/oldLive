package com.hbu.whtk.dao;

import com.hbu.whtk.base.Live;
import com.hbu.whtk.core.DaoImpl;
import com.hbu.whtk.util.ParamsWired;
;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LiveDaoImpl extends DaoImpl<Live> implements LiveDao{
   //删除一个直播
    @Override
    public int Delete(String[] colums, Object[] params) {
        return ParamsWired.Delete("live",colums,params,this);
    }

    //更新一个直播 updateOffset是 参数两个数组里 set 位置 和 where位置分届
    @Override
    public int Update(String[] colums, Object[] params, int updateOffset) {
        return ParamsWired.Update("live",colums,params,updateOffset,this);
    }

    //插入用户信息，
    // 如果返回插入的自增长主键，key=true，返回值是主键 >0，
    // 如果没有子增长主键 key=false，num>0 影响的记录个数
    //insertOffset暂时不管他
    // 原本设计的 insert live values（？，？）
    // ？个数 没测试，
    //随便填
    @Override
    public int Insert(String[] colums, Object[] params, int insertOffset,boolean key) {
        return ParamsWired.Insert("live",colums,params,insertOffset,this,key);
    }
    //得到所有直播的实现
    @Override
    public Map<String, Object> findForMap(Object[] params) {
        Map<String,Object> map=new HashMap<>();
        List<Live> list=new ArrayList<>();
        try {
            list = this.find("select * from live", params, Live.class);
            map.put("status", 1);
            map.put("msg", "成功返回json");
            map.put("content",list);
            map.put("num",list.size());
        }catch(EmptyResultDataAccessException emptyResultDataAccessException)
        {
            list =null;
            map.put("status", 0);
            map.put("msg", "可能当前没有直播");
        }catch (Exception e){
            map.put("status", -1);
            map.put("msg", "查找异常");
        }
        return map;
    }

    @Override
    public Live FindOneByLiveId(Object[] params) {
        Live live;
        try{
            live = this.findForObject("select  * from live where liveId=?", params, Live.class);
            return live;
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            return null;
        }catch (Exception e){
            return null;
        }


    }

    @Override
    public List<Live> findLivesByUserId(int userId) {
        List<Live> list=new ArrayList<>();
        try {
            list = this.find("select * from live where userId=?", new Object[]{userId}, Live.class);
        }catch(EmptyResultDataAccessException emptyResultDataAccessException)
        {
            list =null;

        }catch (Exception e){
            list =null;
        }
        return list;
    }

}
