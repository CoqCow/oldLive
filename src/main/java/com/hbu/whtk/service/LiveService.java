package com.hbu.whtk.service;

import com.hbu.whtk.base.Live;
import com.hbu.whtk.dao.LiveDao;
import com.hbu.whtk.dao.LiveDaoImpl;
import com.hbu.whtk.goback.BackUserAndAnchor;
import com.hbu.whtk.req.LiveStartRequestParams;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LiveService {
    @Autowired
    LiveDao liveDao;
    //删除记录
    public Map<String, Object> deleteLive(String liveId,String userId){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            int result=liveDao.Delete(new String[]{"liveId","userId"},new Object[]{liveId,userId});
            if(result==1){
                map.put("status",1);
                map.put("msg","成功删除直播");
            }else {
                map.put("status",0);
                map.put("msg","删除直播失败");
            }
        }
        catch (Exception e){
            map.put("status",-1);
            map.put("msg","发生异常");
        }


        return  map;
    }
    //插入直播表一条记录
    public Map<String, Object> liveStart(String[] colums, Object[] params, int insertOffset,LiveStartRequestParams requestParams) {
        List<Integer> l = new ArrayList<Integer>();

        Map<String, Object> map = new HashMap<String, Object>();
        int num = -1;
        Live live = null;

        //判断是否超过30分钟  没有超过返回liveId
        int liveId=this.isOutTime(requestParams.getUserId());

        if(liveId>0){
            //没有超过 更新直播开始时间 返回map
           // liveDao.Update();
            try {
                //更新一下 包括开始时间
                liveDao.Update
                        (new String[]{"liveName","liveDescription","liveUrl","liveStartTime","liveId"},
                                new Object[]{requestParams.getLiveName(),requestParams.getLiveDescription(),requestParams.getLiveUrl(),new Date(),liveId},4);
                System.out.println("更新了直播开始时间");
                map.put("status", 2);
                map.put("msg", "直播更新成功");
                map.put("num", liveId);//bug在这
                // l.add(new Integer(num));//=======
                map.put("content", l);
                return map;
            }catch (Exception e){
                System.out.println("更新直播开始时间失败");
                map.put("status", -3);
                map.put("msg", "直播更新失败");
                map.put("num", 0);
                map.put("content", l);
                return map;
            }
        }


        //少判断 主播表里有没有这个userId，然后再插入

        try {
            num = liveDao.Insert(colums, params, insertOffset,true);
        } catch (DataAccessException e) {
            map.put("status", -1);
            map.put("msg", e.getMessage());
            map.put("num", 0);
            map.put("content", l);
            e.printStackTrace();
            return map;
        }

        if (num > 0) {
            map.put("status", 1);
            map.put("msg", "直播插入数据库成功");
            map.put("num", num);
           // l.add(new Integer(num));//=======
            map.put("content", l);
        }
        else{
            map.put("status", -2);
            map.put("msg", "直播插入数据库失败（外键，空值，唯一）");
            map.put("num", 0);
            map.put("content", l);
        }
        return map;
    }
    //得到某用户的最后一次直播 并判断是否超过30分钟 没有超过返回liveId
    public int isOutTime(int userId){
        //得到该用户所有直播
        ArrayList<Live> lives=(ArrayList<Live>)liveDao.findLivesByUserId(userId);
        if(lives.size()>0){
            //取最后一个
            Live live=lives.get(lives.size()-1);
            //得到开始直播时间
            Date liveStartTime=live.getLiveStartTime();
            //现在的时间
            Date now=new Date();
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try{
                String fromDate = simpleFormat.format(liveStartTime);
                String toDate = simpleFormat.format(now);
                long from = simpleFormat.parse(fromDate).getTime();
                long to = simpleFormat.parse(toDate).getTime();
                long minute = ((to - from)/(1000*60)); //间隔多少分钟
                //System.out.println("已经登录了：");
                //long seconds=((to-from)/1000);
                System.out.println("to-from="+(to-from)+"minute="+minute);
                if(minute>=30){
                    System.out.println("已经超时");
                    return 0;
                }else {
                    System.out.println("没有超时");
                    return live.getLiveId();
                }
            }catch (Exception e){
                //应该执行不到这里吧
                System.out.println("应该执行不到这里吧");
                e.printStackTrace();
                return -1;
            }
        }else {
            System.out.println("没找到");
            return -1;
        }

    }
    //得到所有直播
    public Map<String, Object> findMap(Object[] params){

        return liveDao.findForMap(params);
    }

    @Test
    public void testSS()
    {
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/context.xml");
        LiveDaoImpl liveDao=   ac.getBean(LiveDaoImpl.class);
        //得到该用户所有直播
        ArrayList<Live> lives=(ArrayList<Live>)liveDao.findLivesByUserId(1);
        if(lives!=null){
            //取最后一个
            Live live=lives.get(lives.size()-1);
            //得到开始直播时间
            Date liveStartTime=live.getLiveStartTime();
            //现在的时间
            Date now=new Date();
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try{
                String fromDate = simpleFormat.format(liveStartTime);
                String toDate = simpleFormat.format(now);
                long from = simpleFormat.parse(fromDate).getTime();
                long to = simpleFormat.parse(toDate).getTime();
                long minute = ((to - from)/(1000*60)); //间隔多少分钟
                //System.out.println("已经登录了：");
                //long seconds=((to-from)/1000);
                System.out.println("to-from="+(to-from)+"minute="+minute);
                if(minute>=30){
                    System.out.println("已经超时");
                    //return 1;
                }else {
                    System.out.println("没有超时");
                    try {
                        liveDao.Update
                                (new String[]{"liveStartTime","liveId"},
                                        new Object[]{new Date(),live.getLiveId()},1);
                        System.out.println("更新了直播开始时间");
                    }catch (Exception e){
                        System.out.println("更新直播开始时间失败");
                    }

                   // return 0;
                }
            }catch (Exception e){
                //应该执行不到这里吧
                System.out.println("应该执行不到这里吧");
                e.printStackTrace();
               // return -1;
            }
        }else {
            System.out.println("没找到");
          //  return -1;
        }

    }
}
