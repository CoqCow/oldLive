package com.hbu.whtk.controller;

import com.hbu.whtk.base.User;
import com.hbu.whtk.service.ChangeFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by whtk210 on 2017-07-08.
 */
//关注和取消关注 userId为用户id anchorId为主播id flag=1关注 flag=0取消关注
//json/changeFollow?userId=1&anchorId=1&flag=0
@Controller
@RequestMapping("/json")
public class ChangeFollowController {
    @Autowired
    ChangeFollowService changeFollowService;
    @RequestMapping("/changeFollow")
    @ResponseBody
    //这是关注和取消关注的请求处理，flag=1关注请求，flag=0取消关注请求
    public Map<String, Object> testMap(String userId,String anchorId,String flag) {
        Map<String, Object> map = new HashMap<>();
        map=changeFollowService.follow(new Object[]{userId},new Object[]{anchorId},flag);
        return map;
    }
}
