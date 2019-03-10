package com.hbu.whtk.controller;

import com.hbu.whtk.base.Anchor;
import com.hbu.whtk.base.Live;
import com.hbu.whtk.base.User;
import com.hbu.whtk.dao.LiveDao;
import com.hbu.whtk.goback.BackLivesAndAnchors;
import com.hbu.whtk.req.LiveStartRequestParams;
import com.hbu.whtk.req.RegRequestParams;
import com.hbu.whtk.service.AnchorService;
import com.hbu.whtk.service.LiveService;
import com.hbu.whtk.service.UserService;
import com.hbu.whtk.util.ObjecttoArray;
import com.hbu.whtk.util.ParamsWired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/json")
public class LiveController {
    @Autowired
    LiveService liveService;
    @Autowired
    AnchorService anchorService;
    @Autowired
    UserService userService;
    @RequestMapping(value="/livestart",method= RequestMethod.POST )
    @ResponseBody
    public Map<String,Object> livestartInsert(LiveStartRequestParams params )
    {
        Map<String,Object> map=null;
        Integer userId=params.getUserId();
        Integer audienceNum=params.getAudienceNum();
        Integer upvoteNum=params.getUpvoteNum();
        Integer giftNum=params.getGiftNum();
        params.setLiveStartTime(new Date());
        params.setLiveEndTime(null);
        //params.setLiveUrl();
        if(userId==null) params.setUserId(0);
        if(audienceNum==null) params.setAudienceNum(0);
        if(upvoteNum==null)params.setUpvoteNum(0);
        if(giftNum==null)params.setGiftNum(0);
        //加了个参数LiveStartRequestParams 传过去后面使用
        map=liveService.liveStart(ParamsWired.conv(params),
                ObjecttoArray.getFieldValues(params),0,params);


        return map;
    }
    //根据userId 删除某直播
    @RequestMapping(value="/endlive",method= RequestMethod.POST )
    @ResponseBody
    public Map<String,Object> deleteLive(String liveId,String userId)
    {
        Map<String,Object> map=null;
        //liveService.
        map=liveService.deleteLive(liveId,userId);

        return map;
    }
    //得到所有直播 没有参数
    // json/getlives
    @RequestMapping("/getlives")
    @ResponseBody
    public Map<String, Object> getAllLive() {
        Map<String, Object> map = new HashMap<>();
        map=liveService.findMap(new Object[]{});
        return map;
    }
    //得到所有直播和主播 没有参数
    // json/getliveAndanchor1
    @RequestMapping("/getliveAndanchor")
    @ResponseBody
    public Map<String, Object> getAllLiveAndAnchor() {
        ArrayList<BackLivesAndAnchors> backLivesAndAnchorses=new ArrayList<BackLivesAndAnchors>();
        Map<String, Object> rerurnmap = new HashMap<>();
        //得到直播列表
        Map<String, Object> map = new HashMap<>();
    try{
        map=liveService.findMap(new Object[]{});

        if(map.get("status")==1){
            ArrayList<Live> lives= (ArrayList<Live>)map.get("content");
            for(Live live:lives){
                // System.out.println(live.toString());
                Anchor anchor=anchorService.findOneByUserId(new Object[]{live.getUserId()});
                User user=userService.findOneByUserId(new Object[]{live.getUserId()});
                BackLivesAndAnchors one=new BackLivesAndAnchors();
                one.setLive(live);
                one.setAnchor(anchor);
                one.setUser(user);
                backLivesAndAnchorses.add(one);

            }
            rerurnmap.put("status", 1);
            rerurnmap.put("msg", "成功返回json");
            rerurnmap.put("content",backLivesAndAnchorses);
            rerurnmap.put("num",backLivesAndAnchorses.size());
            return rerurnmap;
        }else{
            return map;
        }
    }catch (Exception e){
        rerurnmap.clear();
        rerurnmap.put("status", -1);
        rerurnmap.put("msg", "查找异常");
        return  rerurnmap;
    }



    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
