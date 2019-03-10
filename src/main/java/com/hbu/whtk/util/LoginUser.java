package com.hbu.whtk.util;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Lenovo on 2018/3/6.
 */
@Component
@Lazy(false)
@EnableScheduling
public class LoginUser {
    private static HashMap<Integer,Date> hashMap=new HashMap<>();
    private final int TIEM=2;//2分钟
    //用户是否登录
    public boolean isLogin(int id){
        if(hashMap.get(id)==null){
            return false;
        }else {
            return true;
        }
    }
    //得到用户最后登录的时间
    public Date getUserLoginTime(int id){
        return  hashMap.get(id);
    }
    //添加新登录用户
    public void refreshLoginUser(int id){
        hashMap.put(id,new Date());
    }
    //清除一个登录的用户（用户下线）
    public void clearOneLoginUser(int id){
        if(hashMap.get(id)!=null){
            hashMap.remove(id);
        }
    }
    //刷新用户登录的时间
    public boolean refreshLoginTime(int id){
        //登录超期
        if(this.isOutTime(id)){
            return false;
        }else {
            //正常刷新
            hashMap.put(id,new Date());
            return true;
        }

    }
    public boolean isOutTime(int id){
        //没有该用户 返回true 算作超时
        if(hashMap.get(id)==null)
            return  true;

        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //得到该用户最后登录时间
        Date lastLoginTime=this.getUserLoginTime(id);
        //现在的时间
        Date now=new Date();
        System.out.println(lastLoginTime);
        System.out.println(now);
        try{
            String fromDate = simpleFormat.format(lastLoginTime);
            String toDate = simpleFormat.format(now);
            long from = simpleFormat.parse(fromDate).getTime();
            long to = simpleFormat.parse(toDate).getTime();
            long minute = ((to - from)/(1000*60)); //间隔多少分钟
            //System.out.println("已经登录了：");
            //long seconds=((to-from)/1000);
            System.out.println("to-from="+(to-from)+"minute="+minute);
            if(minute>=TIEM){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            //应该执行不到这里吧
            System.out.println("应该执行不到这里吧");
            e.printStackTrace();
            return true;
        }
    }
    @Scheduled(cron = "0 39 12 * * ? ")
    public void clearOutTimeUser(){
        System.out.println("日期="+new Date());
        Iterator iterator=hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Integer id = (Integer)entry.getKey();
            if(this.isOutTime(id)){
                System.out.println("删除了超时"+hashMap.get(id));
                hashMap.remove(id);
            }
            }
    }
}
