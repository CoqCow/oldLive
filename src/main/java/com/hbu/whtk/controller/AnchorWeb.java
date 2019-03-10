package com.hbu.whtk.controller;

import com.demo.domain.Json;
import com.demo.service.AnchorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AnchorWeb {
    private AnchorService anchorService;
    
    @Autowired
    @Qualifier("fanzengbaoAnchorService")
    public void setAnchorService(AnchorService anchorService){
        this.anchorService=anchorService;
    }
    //得到主播的followNumber
    @RequestMapping(value = "/json/getfollowedNum")
    @ResponseBody
    public Json getAnchorFollowedNumById(String userId){
        Json json=new Json();
        List<HashMap<String,Integer>> list=new LinkedList<HashMap<String, Integer>>();
        HashMap<String,Integer> map=new HashMap<String, Integer>();
        int i;
        int num=Integer.valueOf(userId).intValue();
        try {
            i = anchorService.getAnchorFollowedNumberByAnchorId(num);
            map.put("followedNumber",i);
            list.add(map);
            if(i==-1){
                json.setStatus(0);
                json.setMsg("没有结果");
            }else {
                json.setStatus(1);
                json.setNum(list.size());
                json.setMsg("成功返回json");
                json.setContent(list);
            }
        }catch (Exception e){
            json.setStatus(2);
            json.setMsg(e.toString());
            json.setNum(0);
        }

        return json;
    }
}
