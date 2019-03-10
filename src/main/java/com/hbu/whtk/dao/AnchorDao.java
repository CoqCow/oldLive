package com.hbu.whtk.dao;

import com.hbu.whtk.base.Anchor;
import com.hbu.whtk.base.User;

import java.util.List;

/**
 * Created by whtk210 on 2017-07-18.
 */
public interface AnchorDao {
    public Anchor FindOneByAnchorId(Object[] params);
    public List<Anchor> FindALL();
    public List<Anchor> FindLIstByAnchorId(Object[] params);
    public int Delete(String[] colums,Object[] params);
    public int Update(String[] colums,Object[] params,int updateOffset);
    public int Insert(String[] colums,Object[] params,int insertOffset,boolean key);

}
