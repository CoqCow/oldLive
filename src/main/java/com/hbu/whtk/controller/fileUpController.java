package com.hbu.whtk.controller;

import com.hbu.whtk.goback.BackUserAndAnchor;
import com.hbu.whtk.req.AnchorRequestParams;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/json")
public class fileUpController {

    @RequestMapping(value="/avatarUpload" ,method= RequestMethod.POST )  //得到主播的粉丝
    @ResponseBody
    public Map<String,Object>  fileUpload2(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) throws IOException {
        Map<String,Object> map=new HashMap<String ,Object>();
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());

        String filePath = request.getSession().getServletContext()
                .getRealPath("/") + "/static/uploadAvatar/" + file.getOriginalFilename();
     //   String path="/"+new Date().getTime()+file.getOriginalFilename();

        File newFile=new File(filePath);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        System.out.println(newFile.getAbsolutePath());
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        map.put("status",1);
        map.put("msg","up成功");
        map.put("num",0);
        map.put("content",l);
        return map;
    }
    @RequestMapping(value="/uploadShot" ,method= RequestMethod.POST )  //上传截图
    @ResponseBody
    public Map<String,Object>  uploadShot(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) throws IOException {
        Map<String,Object> map=new HashMap<String ,Object>();
        List<BackUserAndAnchor> l  =new ArrayList<BackUserAndAnchor>();
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());

        String filePath = request.getSession().getServletContext()
                .getRealPath("/") + "/static/" + file.getOriginalFilename();
        //   String path="/"+new Date().getTime()+file.getOriginalFilename();

        File newFile=new File(filePath);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        System.out.println(newFile.getAbsolutePath());
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        map.put("status",1);
        map.put("msg","up成功");
        map.put("num",0);
        map.put("content",l);
        return map;
    }
}
