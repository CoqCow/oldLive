package com.hbu.whtk.dao;

import com.hbu.whtk.base.AudienceNum;

import java.util.Map;

/**
 * Created by Lenovo on 2017/7/14.
 */
public interface ChangeFollowDao {
    public Map<String,Object> changeFollow(Object[] userParams, Object[] anchorParams, String flag);
}
