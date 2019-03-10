package com.hbu.whtk.service;

import com.hbu.whtk.base.User;
import com.hbu.whtk.dao.ChangeFollowDao;
import com.hbu.whtk.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by whtk210 on 2017-07-08.
 */


@Service
public class ChangeFollowService {
    @Autowired
    ChangeFollowDao changeFollowDao;
    public Map<String, Object> follow(Object[] userParams,Object[] anchorParams,String flag){
        return  changeFollowDao.changeFollow(userParams,anchorParams,flag);
    }
}
