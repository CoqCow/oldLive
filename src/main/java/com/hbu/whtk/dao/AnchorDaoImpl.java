package com.hbu.whtk.dao;


import com.hbu.whtk.base.Anchor;
import com.hbu.whtk.base.User;
import com.hbu.whtk.core.DaoImpl;
import com.hbu.whtk.util.ParamsWired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by whtk210 on 2017-07-18.
 */
@Repository
public class AnchorDaoImpl extends DaoImpl<Anchor> implements AnchorDao {
    //通过主播id查找一个主播
    @Override
    public Anchor FindOneByAnchorId(Object[] params) {
        Anchor anchor;

        anchor = this.findForObject("select  * from anchor where userId=?", params, Anchor.class);

        return anchor;
    }
    //通过查找所有主播
    @Override
    public List<Anchor> FindALL() {
        List<Anchor> l= this.find("select  * from anchor",null,Anchor.class);

        return l;
    }
    //通过主播id列表查找主播列表
    @Override
    public List<Anchor> FindLIstByAnchorId(Object[] params) {
        List<Anchor> l= this.find("select  * from anchor where userId=?",params,Anchor.class);

        return l;
    }

    //删除
    @Override
    public int Delete(String[] colums, Object[] params) {
        return ParamsWired.Delete("anchor",colums,params,this);
    }
  //  updateOffset是 参数两个数组里 set 位置 和 where位置分届
    @Override
    public int Update(String[] colums, Object[] params, int updateOffset) {
        return ParamsWired.Update("anchor",colums,params,updateOffset,this);
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
        return ParamsWired.Insert("anchor",colums,params,insertOffset,this,false);
    }
}
