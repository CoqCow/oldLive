package com.hbu.whtk.dao;

import com.hbu.whtk.base.View;
import com.hbu.whtk.core.DaoImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Lenovo on 2018/2/23.
 */
@Repository
public class ViewDaoImpl extends DaoImpl<View> implements ViewDao {
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    LiveDaoImpl liveDao;

    @Override
    public int insertOneViewer(String liveId, String userId) {
        //0000判断直播和用户是否合法
        if(userDao.FindOneByUserId(new Object[]{userId})==null||liveDao.FindOneByLiveId(new Object[]{liveId})==null){
            return 0;
        }
        int flag=-1;
        if(this.selectViewer(liveId,userId)==null){
            //观看表中没有某用户和某直播
            String sql=
                    "insert into view(liveId,userId,viewDuration,userUpvoteNum,sendGiftNum,viewTime) values(?,?,0,0,0,?)";
            try {
               // this.jdbcTemplate.update(sql, new Object[]{liveId, userId,new Date()});
                flag = this.addOrUpdateOrDelete("insert into view(liveId,userId,viewDuration,userUpvoteNum,sendGiftNum,viewTime)values(?,?,0,0,0,now())", new Object[]{liveId, userId}, null);
                if(flag==1){
                    return 1;
                }else {
                    return -1;
                }
            } catch (Exception e){
                e.printStackTrace();
                return -1;
            }

        }else{
            //观看表中存在记录
            return 2;
        }


    }

    @Override
    public int deleteOneViewer(String liveId, String userId) {
        int flag=-1;
        //查找观看表中是否有记录
        if(this.selectViewer(liveId,userId)!=null){
            flag=this.addOrUpdateOrDelete("delete from view where liveId=? and userId=?",new Object[]{liveId,userId},View.class);
           System.out.println("flag="+flag);
            if(flag==1){
               return 1;
           }else {
               return -1;
           }
        }else {
            return 0;
        }

    }

    @Override
    //查找是否存在观看记录
    public View selectViewer(String liveId, String userId) {
        View view=null;
        Object[] objects=new Object[]{liveId,userId};
        String sql="select * from view where liveId=? and userId=?";
        try{

                view=this.findForObject(sql,objects,View.class);
        if(view!=null){
            return view;
        }else {
            return null;
        }
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){

            return null;
        }catch (Exception e){
            return null;
        }


    }
    @Test
    public void testSS()
    {
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/context.xml");
        ViewDaoImpl ViewDaoImpl=   ac.getBean(ViewDaoImpl.class);
        UserDaoImpl userDao=ac.getBean(UserDaoImpl.class);
        LiveDaoImpl liveDao=ac.getBean(LiveDaoImpl.class);
        //System.out.println(userDao.FindOneByUserId(new Object[]{"10"}).toString());
        if(userDao.FindOneByUserId(new Object[]{"10"})==null){
            System.out.println("空值");
        }else {
            System.out.println(userDao.FindOneByUserId(new Object[]{"10"}).toString());
        }
/*        if(liveDao.FindOneByLiveId(new Object[]{"1"})==null){
            System.out.println("空值");
        }else {
            System.out.println(liveDao.FindOneByLiveId(new Object[]{"1"}).toString());
        }*/
        //System.out.println(ViewDaoImpl.selectViewer("2","3").toString());
        //System.out.println(ViewDaoImpl.selectViewer("3","3")==null);
        //System.out.println(ViewDaoImpl.insertOneViewer("2","3"));
        //System.out.println(ViewDaoImpl.deleteOneViewer("2","3"));
    }
}
