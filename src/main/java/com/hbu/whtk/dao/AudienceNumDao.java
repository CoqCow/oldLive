package com.hbu.whtk.dao;

import com.hbu.whtk.base.AudienceNum;
import com.hbu.whtk.base.User;

import java.util.Map;

/**
 * Created by Lenovo on 2017/7/14.
 */
public interface AudienceNumDao {
    public AudienceNum FindOne(Object[] params);
    public Map<String, Object> findAudienceNumByLiveId(Object[] params);
}
