package com.hbu.whtk.service;

import com.hbu.whtk.base.AudienceNum;
import com.hbu.whtk.dao.AudienceNumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Lenovo on 2017/7/15.
 */
@Service
public class AudienceNumService {
    @Autowired
    AudienceNumDao audienceNumDao;
    public AudienceNum find(Object[] params)
    {
        return audienceNumDao.FindOne(params);

    }
    public Map<String, Object> findAudienceNumForMap(Object[] params){

        return audienceNumDao.findAudienceNumByLiveId(params);
    }
}
