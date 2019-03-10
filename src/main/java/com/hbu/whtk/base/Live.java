package com.hbu.whtk.base;
import java.io.Serializable;
import java.util.Date;


   /**
    * live 实体类
    * Mon Jul 17 23:30:13 CST 2017 whtk
    */ 


public class Live implements Serializable {
	   public int liveId;
	   public int userId;
	   public String liveName;
	   public String liveDescription;
	   public String liveType;
	   public String livePwd;
	   public Date liveStartTime;
	   public Date liveEndTime;
	   public int audienceNum;
	   public int upvoteNum;
	   public int giftNum;
	   public String screenShot;
	   public String liveUrl;

	   public String getLiveUrl() {
		   return liveUrl;
	   }

	   public void setLiveUrl(String liveUrl) {
		   this.liveUrl = liveUrl;
	   }

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
	public void setLiveName(String liveName){
	this.liveName=liveName;
	}
	public String getLiveName(){
		return liveName;
	}
	public void setLiveDescription(String liveDescription){
	this.liveDescription=liveDescription;
	}
	public String getLiveDescription(){
		return liveDescription;
	}
	public void setLiveType(String liveType){
	this.liveType=liveType;
	}
	public String getLiveType(){
		return liveType;
	}
	public void setLivePwd(String livePwd){
	this.livePwd=livePwd;
	}
	public String getLivePwd(){
		return livePwd;
	}
	public void setLiveStartTime(Date liveStartTime){
	this.liveStartTime=liveStartTime;
	}
	public Date getLiveStartTime(){
		return liveStartTime;
	}
	public void setLiveEndTime(Date liveEndTime){
	this.liveEndTime=liveEndTime;
	}
	public Date getLiveEndTime(){
		return liveEndTime;
	}
	public void setAudienceNum(int audienceNum){
	this.audienceNum=audienceNum;
	}
	public int getAudienceNum(){
		return audienceNum;
	}
	public void setUpvoteNum(int upvoteNum){
	this.upvoteNum=upvoteNum;
	}
	public int getUpvoteNum(){
		return upvoteNum;
	}
	public void setGiftNum(int giftNum){
	this.giftNum=giftNum;
	}
	public int getGiftNum(){
		return giftNum;
	}
	public void setScreenShot(String screenShot){
	this.screenShot=screenShot;
	}
	public String getScreenShot(){
		return screenShot;
	}

	   @Override
	   public String toString() {
		   return "Live{" +
				   "liveId=" + liveId +
				   ", userId=" + userId +
				   ", liveName='" + liveName + '\'' +
				   ", liveDescription='" + liveDescription + '\'' +
				   ", liveType='" + liveType + '\'' +
				   ", livePwd='" + livePwd + '\'' +
				   ", liveStartTime=" + liveStartTime +
				   ", liveEndTime=" + liveEndTime +
				   ", audienceNum=" + audienceNum +
				   ", upvoteNum=" + upvoteNum +
				   ", giftNum=" + giftNum +
				   ", screenShot='" + screenShot + '\'' +
				   '}';
	   }
   }

