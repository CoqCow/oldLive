package com.hbu.whtk.dao;

import java.util.Map;

/**
 * Created by Lenovo on 2017/7/19.
 */
public interface UpvoteDao {
    public Map<String,Object> doUpvote(Object[] liveParams, Object[] viewParams);
    public Map<String,Object> getUpvote(Object[] liveParams);
}
