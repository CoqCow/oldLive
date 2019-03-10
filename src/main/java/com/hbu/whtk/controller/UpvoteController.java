package com.hbu.whtk.controller;

import com.hbu.whtk.service.UpvoteDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/7/19.
 */
//
@Controller
@RequestMapping("/json")
public class UpvoteController {
    @Autowired
    UpvoteDaoService upvoteDaoService;
    //用户给直播点赞 liveId为直播id userId为用户id
    //json/upvote?liveId=1&userId=2
    @RequestMapping("/upvote")
    @ResponseBody
    public Map<String, Object> doUpvote(String liveId, String userId) {
        Map<String, Object> map = new HashMap<>();
        map=upvoteDaoService.doUpvote(new Object[]{liveId},new Object[]{liveId,userId});
        return map;
    }
    //得到某直播中用户的点赞数量 liveId为直播id
    //json/getupvote?liveId=1
    @RequestMapping("/getupvote")
    @ResponseBody
    public Map<String, Object> getUpvote(String liveId) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("liveid="+liveId);
        map=upvoteDaoService.getUpvote(new Object[]{liveId});
        return map;
    }
}
