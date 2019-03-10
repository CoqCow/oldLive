package com.hbu.whtk.service;

import com.hbu.whtk.base.Anchor;
import com.hbu.whtk.base.User;
import com.hbu.whtk.dao.AnchorDao;
import com.hbu.whtk.dao.UserDao;
import com.hbu.whtk.util.ParamsWired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by whtk210 on 2017-07-18.
 */
@Service
public class AnchorService {

    @Autowired
    AnchorDao anchorDao;
    @Autowired
    UserDao userDao;
    //查找主播 byuserid（没写返回的json结构）
    public Anchor findOneByUserId(Object[] params)
    {
        Anchor anchor;

        anchor=anchorDao.FindOneByAnchorId(params);

        return anchor;

    }
    //查找主播列表 byuserid列表（没写返回的json结构）
    public List<Anchor> findListByUserId(Object[] params)
    {
        List<Anchor> l=anchorDao.FindLIstByAnchorId(params);
        return l;
    }
    //查找所有主播列表（没写返回的json结构）
    public List<Anchor>  findAll()
    {
        List<Anchor> l=anchorDao.FindALL();
        return l;
    }
    public Map<String, Object> applyAnchor(int userId,String[] colums,Object[] params,int insertOffset) {
        int num2=-1;
        int num=-1;
///没有判断 这个userid已经是主播了
        //更新用户表部分
        num = userDao.Update(new String[]{"whetherAnchor", "userId"}, new Object[]{1, userId}, 1);
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> l = new ArrayList<User>();
        if (num > 0) {


            try {
                //插入主播表
                num2 = anchorDao.Insert(colums, params, insertOffset, false);
            }catch (Exception e)
            {
                map.put("status", -1);
                map.put("msg", "异常可能已经是主播了");
                map.put("num", 0);
                map.put("content", l);
                return map;
            }
            if (num2 > 0) {
                map.put("status", 1);
                map.put("msg", "成功");
                map.put("num", 0);
                map.put("content", l);
                return map;
            } else {

                map.put("status", -2);
                map.put("msg", "插入主播表失败（也许是主播了）");
                map.put("num", 0);
                map.put("content", l);
                return map;
            }
        } else {
            map.put("status", -1);
            map.put("msg", "更新whetherAnchor失败");
            map.put("num", 0);
            map.put("content", l);
            return map;
        }

    }

}
