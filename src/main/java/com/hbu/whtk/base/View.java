package com.hbu.whtk.base;
import java.util.Date;


   /**
    * view 实体类
    * Tue Jul 18 08:50:15 CST 2017 whtk
    */ 


public class View{
	   public int liveId;
	   public int userId;
	   public int viewDuration;
	   public int userUpvoteNum;
	   public int sendGiftNum;
	   public Date viewTime;
	public void setLiveId(int liveId){
	this.liveId=liveId;
	}
	public int getLiveId(){
		return liveId;
	}
	public void setUserId(int userId){
	this.userId=userId;
	}
	public int getUserId(){
		return userId;
	}
	public void setViewDuration(int viewDuration){
	this.viewDuration=viewDuration;
	}
	public int getViewDuration(){
		return viewDuration;
	}
	public void setUserUpvoteNum(int userUpvoteNum){
	this.userUpvoteNum=userUpvoteNum;
	}
	public int getUserUpvoteNum(){
		return userUpvoteNum;
	}
	public void setSendGiftNum(int sendGiftNum){
	this.sendGiftNum=sendGiftNum;
	}
	public int getSendGiftNum(){
		return sendGiftNum;
	}
	public void setViewTime(Date viewTime){
	this.viewTime=viewTime;
	}
	public Date getViewTime(){
		return viewTime;
	}

	   @Override
	   public String toString() {
		   return "View{" +
				   "liveId=" + liveId +
				   ", userId=" + userId +
				   ", viewDuration=" + viewDuration +
				   ", userUpvoteNum=" + userUpvoteNum +
				   ", sendGiftNum=" + sendGiftNum +
				   ", viewTime=" + viewTime +
				   '}';
	   }
   }

