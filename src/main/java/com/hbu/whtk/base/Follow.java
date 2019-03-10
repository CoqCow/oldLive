package com.hbu.whtk.base;
import java.util.Date;


   /**
    * follow 实体类
    * Mon Jul 17 23:34:55 CST 2017 whtk
    */ 


public class Follow{
	   public int userId;
	   public int anchorId;
	   public Date followTime;
	public void setUserId(int userId){
	this.userId=userId;
	}
	public int getUserId(){
		return userId;
	}
	public void setAnchorId(int anchorId){
	this.anchorId=anchorId;
	}
	public int getAnchorId(){
		return anchorId;
	}
	public void setFollowTime(Date followTime){
	this.followTime=followTime;
	}
	public Date getFollowTime(){
		return followTime;
	}
}

