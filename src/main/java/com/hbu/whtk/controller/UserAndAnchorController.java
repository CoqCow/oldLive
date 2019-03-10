package com.hbu.whtk.controller;

import com.hbu.whtk.base.User;
import com.hbu.whtk.goback.BackUserAndAnchor;
import com.hbu.whtk.service.UserAndAnchorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by whtk210 on 2017-07-08.
 */
@Controller
@RequestMapping("/json")
public class UserAndAnchorController {
    @Autowired
    UserAndAnchorService userAndAnchorService;
    @RequestMapping(value="/login")
    @ResponseBody
    public Map<String,Object> getUserAndAnchorInfoByNumberAndPassword(HttpServletRequest request, int number, String password){
        BackUserAndAnchor backUserAndAnchor;
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        Map<String,Object> map=null;
        map= userAndAnchorService.getBackUserAndAnchorByNumberAndPassword(new Object[]{number,password});
        return map;
    }
    @RequestMapping(value="/onLine")
    @ResponseBody
    public Map<String,Object> refreshLoginStatus(HttpServletRequest request, int userId){
        Map<String,Object> map=new HashMap<>();
        if(userAndAnchorService.refreshLoginStatus(userId)){
            map.put("status",1);
            map.put("msg","用户在线状态刷新成功");
            map.put("num",0);
            map.put("content",null);
        }else {
            map.put("status",-1);
            map.put("msg","刷新失败，可能该用户已经掉线");
            map.put("num",0);
            map.put("content",null);
        }

        return map;
    }
    @RequestMapping(value="/logout")
    @ResponseBody
    public Map<String,Object> logout(HttpServletRequest request, int userId){
        Map<String,Object> map=new HashMap<>();
        if(userAndAnchorService.logout(userId)==true){
            map.put("status",1);
            map.put("msg","该用户成功登出");
            map.put("num",0);
            map.put("content",null);
        }else {
            map.put("status",-1);
            map.put("msg","登出失败，该用户还没有登录");
            map.put("num",0);
            map.put("content",null);
        }

        return map;
    }
    //整体更新user表
    @RequestMapping(value="/updateuser")
    @ResponseBody
    public ModelAndView getUser(HttpServletRequest request, int number, String password){
        User user;
        ModelAndView mv = new ModelAndView();
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        Map<String,Object> map=null;
        map= userAndAnchorService.getBackUserAndAnchorByNumberAndPassword(new Object[]{number,password});
        l= (List<BackUserAndAnchor>)map.get("content");
        user=l.get(0);
        System.out.println("userid="+user.getUserId());
        mv.addObject("user",user);
        mv.setViewName("update");
        return mv;
    }

    @RequestMapping(value="/allua")
    @ResponseBody
    public  Map<String,Object>  getAllUserList(){
        BackUserAndAnchor backUserAndAnchor;
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        Map<String,Object> map=null;
        map=  userAndAnchorService.getAllBackUserAndAnchorList();
        return map;
    }
    @RequestMapping(value="/uaByUserid")
    @ResponseBody
    public  Map<String,Object>  getUserAndAnchorByUserid(int userId){
        BackUserAndAnchor backUserAndAnchor=null;
            List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
            Map<String,Object> map=null;

        map=  userAndAnchorService.getBackUserAndAnchorByUserId(new Object[]{userId});
        return map;
    }

}
