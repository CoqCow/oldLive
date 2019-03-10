package com.hbu.whtk.base;
import java.io.Serializable;
import java.util.Date;


   /**
    * anchor 实体类
    * Mon Jul 17 23:27:11 CST 2017 whtk
    */ 


public class Anchor implements Serializable {
	public int userId;
	   public String anchorCategory;
	   public Integer playTime;
	   public Integer totalUpvoteNum;
	   public Integer followedNumber;
	   public Date applyAnchorTime;
	   public Integer totalGiftsNumber;
	public void setUserId(Integer userId){
	this.userId=userId;
	}
	public Integer getUserId(){
		return userId;
	}
	public void setAnchorCategory(String anchorCategory){
	this.anchorCategory=anchorCategory;
	}
	public String getAnchorCategory(){
		return anchorCategory;
	}
	public void setPlayTime(Integer playTime){
	this.playTime=playTime;
	}
	public Integer getPlayTime(){
		return playTime;
	}
	public void setTotalUpvoteNum(Integer totalUpvoteNum){
	this.totalUpvoteNum=totalUpvoteNum;
	}
	public Integer getTotalUpvoteNum(){
		return totalUpvoteNum;
	}
	public void setFollowedNumber(Integer followedNumber){
	this.followedNumber=followedNumber;
	}
	public Integer getFollowedNumber(){
		return followedNumber;
	}
	public void setApplyAnchorTime(Date applyAnchorTime){
	this.applyAnchorTime=applyAnchorTime;
	}
	public Date getApplyAnchorTime(){
		return applyAnchorTime;
	}
	public void setTotalGiftsNumber(Integer totalGiftsNumber){
	this.totalGiftsNumber=totalGiftsNumber;
	}
	public Integer getTotalGiftsNumber(){
		return totalGiftsNumber;
	}
}

