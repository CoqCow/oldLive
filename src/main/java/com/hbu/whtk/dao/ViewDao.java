package com.hbu.whtk.dao;

import com.hbu.whtk.base.View;

/**
 * Created by Lenovo on 2018/2/23.
 */
public interface ViewDao {
    int insertOneViewer(String liveId,String userId);
    int deleteOneViewer(String liveId,String userId);
    View selectViewer(String liveId, String userId);
}
