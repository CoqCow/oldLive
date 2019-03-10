package com.hbu.whtk.dao;

import com.hbu.whtk.base.UpvoteNum;
import com.hbu.whtk.base.UserUpvoteNum;
import com.hbu.whtk.core.DaoImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/7/19.
 */
@Repository
public class BindPhoneDaoImpl extends DaoImpl<UpvoteNum> implements BindPhoneDao{

    @Override
    public int  bind(Object[] params){
        int flag=-1;

        flag=this.addOrUpdateOrDelete("update user set phoneNumber=? where userId=?",params,null);
        System.out.println("=="+flag);
        return flag;
    }


}
