package com.hbu.whtk.controller;

import com.hbu.whtk.service.AudienceNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/7/15.
 */
//得到某直播的观众数量 liveId为直播id
//json/audiencenum?liveId=1
@Controller
@RequestMapping("/json")
public class AudienceNumController {
    @Autowired
    AudienceNumService audienceNumService;
    @RequestMapping("/audiencenum")
    @ResponseBody
    public Map<String, Object> getAudience(String liveId) {
        Map<String, Object> map = new HashMap<>();
        map=audienceNumService.findAudienceNumForMap(new Object[]{liveId});
        return map;
    }
}
