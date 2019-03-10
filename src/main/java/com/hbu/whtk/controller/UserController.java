package com.hbu.whtk.controller;

import com.hbu.whtk.goback.BackUserAndAnchor;
import com.hbu.whtk.req.RegRequestParams;
import com.hbu.whtk.req.ChangePswRequestParams;
import com.hbu.whtk.req.UpdateUserParams;
import com.hbu.whtk.service.UserAndAnchorService;
import com.hbu.whtk.service.UserService;
import com.hbu.whtk.util.ObjecttoArray;
import com.hbu.whtk.util.ParamsWired;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by whtk210 on 2017-07-19.
 */
@Controller
@RequestMapping("/json")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/reg",method= RequestMethod.POST )
    @ResponseBody

    public Map<String,Object> reg(  RegRequestParams params )
    {
        BackUserAndAnchor backUserAndAnchor=null;
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        Map<String,Object> map=null;
       if(params.getWhetherAnchor()==null) params.setWhetherAnchor(0);
       if(params.getFollowNumber()==null) params.setFollowNumber(0);
        if(params.getRegistrationTime()==null) params.setRegistrationTime(new Date());


        map = userService.reg(ParamsWired.conv((Object) params),
                ObjecttoArray.getFieldValues(params),0);
        return map;

    }
    //更新用户个人信息
    @RequestMapping(value="/updateAll",method= RequestMethod.POST )
    @ResponseBody

    public Map<String,Object> updateAll(UpdateUserParams params )
    {
        BackUserAndAnchor backUserAndAnchor=null;
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        Map<String,Object> map=new HashMap<String,Object>();
        String[] colums= ParamsWired.conv((Object) params);
        /*  for(int i=0;i<colums.length;i++){
            System.out.print("==="+colums[i]);
        }*/
        int i=-1;
        i= userService.update(ParamsWired.conv((Object) params),
                ObjecttoArray.getFieldValues(params),6);
        if(i==1){
            map.put("status", 1);
            map.put("msg", "成功修改用户信息");
            map.put("num", 0);
            l.add(backUserAndAnchor);
            map.put("content", l);
        }else{
            map.put("status", -1);
            map.put("msg", "修改用户信息失败");
            map.put("num",0);
            map.put("content",l);
        }
        System.out.print("==="+i);
        return map;
    }
    //修改手机号
    @RequestMapping(value="/bindPhone" )
    @ResponseBody
    public Map<String,Object> bindPhone(String userId,String phoneNumber ){
        Map<String,Object> map=new HashMap<String,Object>();;
        System.out.println("phoneNumber="+phoneNumber);
        map=userService.bindPhone(new Object[]{phoneNumber,userId});
        return map;
    }

    @Test
    public void testInsert()
    {
        Map<String,Object> map=null;
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/context.xml");
        UserService userService=   ac.getBean(UserService.class);
        RegRequestParams params=new RegRequestParams();
        params.setNumber(11113);
        params.setName("李四");

        params.setRegistrationTime(new Date());

        map= userService.reg(ParamsWired.conv((Object) params),
                ObjecttoArray.getFieldValues(params),0);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value="/changepsw")
    @ResponseBody
    public Map<String,Object> reg( ChangePswRequestParams params )
    {
        String psw=params.getPassword();
        String uid=params.getUserId();
        BackUserAndAnchor backUserAndAnchor=null;
       // List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
         Map<String,Object> map=null;

          map= userService.changePassword(new String[]{"password", "userId"}, new Object[]{psw, uid}, 1);

        return map;

    }



}
