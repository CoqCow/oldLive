package com.hbu.whtk.controller;

import com.hbu.whtk.req.AnchorRequestParams;
import com.hbu.whtk.service.AnchorService;
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
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/json")
public class AnchorController {
    @Autowired
    AnchorService anchorService;
    @RequestMapping(value="/applyAnchor" ,method= RequestMethod.POST )  //得到主播的粉丝
    @ResponseBody
    public Map<String,Object> applyAnchor(AnchorRequestParams params)
    {
        Integer userId=params.getUserId();
        Integer playTime = params.getPlayTime();
        Integer totalUpvoteNum =params.getTotalUpvoteNum();
        Integer followedNumer =params.getFollowedNumber();
        Integer totalGiftsNumber=params.getTotalGiftsNumber();
        //if(userId==null) params.setUserId(0);
        if(playTime==null) params.setPlayTime(0);
        if(totalUpvoteNum==null) params.setTotalUpvoteNum(0);
        if(followedNumer==null) params.setFollowedNumber(0);
        if(totalGiftsNumber==null) params.setTotalUpvoteNum(0);

        Map<String,Object> map=anchorService.applyAnchor(userId,ParamsWired.conv(params),
                ObjecttoArray.getFieldValues(params),0);

       // return map;
        return map;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
