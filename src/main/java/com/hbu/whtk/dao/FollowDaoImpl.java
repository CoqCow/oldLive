package com.hbu.whtk.dao;

import com.hbu.whtk.base.Follow;
import com.hbu.whtk.core.DaoImpl;
import com.hbu.whtk.util.ParamsWired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FollowDaoImpl extends DaoImpl<Follow> implements FollowDao  {
    //删除
    @Override
    public int Delete(String[] colums, Object[] params) {
        return ParamsWired.Delete("follow",colums,params,this);
    }
   // updateOffset是 参数两个数组里 set 位置 和 where位置分届
    @Override
    public int Update(String[] colums, Object[] params, int updateOffset) {
        return ParamsWired.Update("follow",colums,params,updateOffset,this);
    }
    //插入用户信息，
    // 如果返回插入的自增长主键，key=true，返回值是主键 >0，
    // 如果没有子增长主键 key=false，num>0 影响的记录个数
    //insertOffset暂时不管他
    // 原本设计的 insert live values（？，？）
    // ？个数 没测试，
    //随便填
    @Override
    public int Insert(String[] colums, Object[] params, int insertOffset, boolean key) {
        return ParamsWired.Insert("follow",colums,params,insertOffset,this,false);
    }
//得到主播的用户，主播，关注时间列表
    @Override
    public  List<Follow>  getAnchorFlowers(int anchorId) {

        List<Follow> list=this.find("select  * from follow where anchorId=?",new Object[]{anchorId},Follow.class);
      //  List<Integer> valueList = new ArrayList<Integer>();
      //  for(Follow o :  list){valueList.add(o.getUserId());}
        return list;
    }
}
