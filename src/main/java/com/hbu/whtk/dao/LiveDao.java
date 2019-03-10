package com.hbu.whtk.dao;

import com.hbu.whtk.base.Live;

import java.util.List;
import java.util.Map;

public interface LiveDao {

    public int Delete(String[] colums,Object[] params);
    public int Update(String[] colums,Object[] params,int updateOffset);
    public int Insert(String[] colums,Object[] params,int insertOffset,boolean key);

    //得到所有直播列表的方法
    public Map<String, Object> findForMap(Object[] params);

    //根据liveId查找某直播
    public Live FindOneByLiveId(Object[] params);

    //根据userId查找直播
    public List<Live> findLivesByUserId(int userId);
}
