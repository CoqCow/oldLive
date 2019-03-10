package com.hbu.whtk.core;

import java.util.List;
import java.util.Map;

/**
 * Created by whtk210 on 2017-07-08.
 */
public interface Dao<T> {
    //查找多个T组成的列表
    public <T> List<T> find(String sql, Object[] params, Class<T> tClass);

    //怎删改
    public <T> int addOrUpdateOrDelete(String sql, Object[] params, Class<T> tClass);
    //找一个T对象
    public <T> T findForObject(String sql, Object[] args, Class<T> classT);
    //找到一个T的Map形式
    public Map<String, Object> find(String sql, Object[] params);
    //找到一个T的Map的list
    public List<Map<String,Object>> queryList(String sql,Object[] params);

}
