package com.hbu.whtk.controller;

import com.hbu.whtk.service.FollowService;
import com.hbu.whtk.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("/json")
public class FollowController {
    @Autowired
    FollowService followService;
    @RequestMapping(value="/getFollowed" )  //得到主播的粉丝
    @ResponseBody
    public Map<String,Object> getFollowed(Integer anchorId)
    {
        Map<String,Object> map=followService.getAnchorFlowers(anchorId);

        return map;

    }


}
