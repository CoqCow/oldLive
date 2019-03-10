package com.hbu.whtk.controller;

import com.demo.domain.Anchor;
import com.demo.domain.Json;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserWeb {

    private UserService userService;
    @Autowired
    @Qualifier("fanzengbaoUserService")
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    //通用检测是否修改成功
    private Json Check(int status){
        Json json=new Json();
        switch (status){
            case 0:
                json.setStatus(status);
                json.setNum(0);
                json.setMsg("修改失败");
                break;
            case 1:
                json.setStatus(status);
                json.setNum(1);
                json.setMsg("修改成功");
                break;
            case 2:
                json.setStatus(status);
                json.setNum(0);
                json.setMsg("该用户不存在");
                break;
        }
        return json;
    }
    //得到喜欢的主播的信息
    @RequestMapping(value = "/json/getmylikes")
    @ResponseBody
    public Json<Anchor> getAllMyLikes(String userId) {
        int i=Integer.valueOf(userId).intValue();
        Json json=new Json();
        if(userService.checkUserById(i)>0) {
            List<Anchor> list=userService.getAllMyLikes(i);
            if(list!=null){
              json.setStatus(1);
              json.setNum(list.size());
              json.setMsg("成功返回json");
              json.setContent(list);
            }else {
                json.setStatus(0);
                json.setNum(0);
                json.setMsg("该用户尚未关注任何主播");
                json.setContent(null);
            }
        }else {
            json.setStatus(2);
            json.setNum(0);
            json.setMsg("该用户不存在");
            json.setContent(null);
        }
        return json;
    }
    //修改头像
    @RequestMapping(value = "/json/updateavatar")
    @ResponseBody
    public Json setAvatar(String userId,String avatar) {
        int i=Integer.valueOf(userId).intValue();
        int status= userService.setAvatar(avatar,i);
        return Check(status);
    }
    //修改昵称
    @RequestMapping(value = "/json/updatenickName")
    @ResponseBody
    public Json changeNickName(String userId,String nickName){
        int i=Integer.valueOf(userId).intValue();
        int status= userService.changeNickName(nickName,i);
        return Check(status);
    }
    //修改密码
    @RequestMapping(value = "/json/updatePWD")
    @ResponseBody
    public Json changePWD(String userId,String PWD){
        int i=Integer.valueOf(userId).intValue();
        int status= userService.changePWD(PWD,i);
        return Check(status);
    }
    //修改个性签名
    @RequestMapping(value = "/json/updateDescription")
    @ResponseBody
    public Json changeDescription(String userId,String Description){
        int i=Integer.valueOf(userId).intValue();
        int status= userService.changeDescription(Description,i);
        return Check(status);
    }

}
