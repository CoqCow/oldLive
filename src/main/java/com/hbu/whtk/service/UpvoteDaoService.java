package com.hbu.whtk.service;

import com.hbu.whtk.dao.UpvoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Lenovo on 2017/7/19.
 */
@Service
public class UpvoteDaoService {
    @Autowired
    UpvoteDao upvoteDao;
    public Map<String, Object> doUpvote(Object[] liveParams,Object[] viewParams){
        return  upvoteDao.doUpvote(liveParams,viewParams);
    }
    public Map<String, Object> getUpvote(Object[] liveParams){
        return  upvoteDao.getUpvote(liveParams);
    }
}

