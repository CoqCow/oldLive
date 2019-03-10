package com.demo.service;


import com.demo.dao.AnchorDao;
import com.demo.dao.UserDao;
import com.demo.domain.Anchor;
import com.demo.domain.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fanzengbaoUserService")
public class UserService {

    private UserDao userDao;
    private AnchorDao anchorDao;
    @Autowired
    @Qualifier("fanzengbaoUserDao")
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }
    @Autowired
    @Qualifier("fanzengbaoAnchorDao")
    public void setAnchorDao(AnchorDao anchorDao) {
        this.anchorDao = anchorDao;
    }

    public int checkUserById(int userId){
       return userDao.checkUserByUserId(userId);
    }
    public List<Anchor> getAllMyLikes(int Id){
        List<Follow> list= userDao.getAllMyLikes(Id,Follow.class);
        int count=list.size();
        List<Anchor> anchorList=new ArrayList<Anchor>();
        for (int i=0;i<count;i++)
        {
            anchorList.add(anchorDao.findAnchorById(list.get(i).getAnchorId()));
        }
        if(anchorList.size()==0)
        {
            anchorList=null;
        }
        return anchorList;
    }
    public int setAvatar(String avatar,int userId){
        return userDao.setAvatar(avatar,userId);
    }
    public int changeNickName(String nickName,int userId){
        return userDao.changeNickName(nickName,userId);
    }
    public int changePWD(String pass,int userId){
        return userDao.changePWD(pass,userId);
    }
    public int changeDescription(String des,int userId){
        return userDao.changeDescription(des,userId);
    }

}
