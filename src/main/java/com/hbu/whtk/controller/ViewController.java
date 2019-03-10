package com.hbu.whtk.controller;

import com.hbu.whtk.base.View;
import com.hbu.whtk.dao.ViewDao;
import com.hbu.whtk.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Lenovo on 2018/2/23.
 */
@Controller
@RequestMapping("/json")
public class ViewController {
    @Autowired
    ViewService viewService;

    @RequestMapping(value="/watchAnchor" ,method= RequestMethod.POST )  //
    @ResponseBody
    public Map<String,Object> addView(String liveId,String userId){
        return viewService.addViewer(liveId,userId);
    }

    @RequestMapping(value="/diswatchAnchor" ,method= RequestMethod.POST )  //
    @ResponseBody
    public Map<String,Object> deleteView(String liveId,String userId){

        return viewService.deleteViewer(liveId,userId);
    }
}
