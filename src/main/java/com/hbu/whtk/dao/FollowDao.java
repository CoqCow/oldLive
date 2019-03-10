package com.hbu.whtk.dao;

import com.hbu.whtk.base.Follow;

import java.util.List;

public interface FollowDao {
    public int Delete(String[] colums,Object[] params);
    public int Update(String[] colums,Object[] params,int updateOffset);
    public int Insert(String[] colums,Object[] params,int insertOffset,boolean key);
    public  List<Follow>  getAnchorFlowers(int userId);
}
