
package com.hbu.whtk.base;
import java.io.Serializable;
import java.util.Date;


   /**
    * user 实体类
    * Mon Jul 17 23:29:48 CST 2017 whtk
    */ 


public class User implements Serializable {
	   public int userId;
	   public String organizationName;
	   public int number;
	   public String password;
	   public String name;
	   public String college;
	   public String nickName;
	   public String avatar;
	   public String signature;
	   public String phoneNumber;
	   public int whetherAnchor;
	   public int followNumber;
	   public Date registrationTime;
	public void setUserId(int userId){
	this.userId=userId;
	}
	public int getUserId(){
		return userId;
	}
	public void setOrganizationName(String organizationName){
	this.organizationName=organizationName;
	}
	public String getOrganizationName(){
		return organizationName;
	}
	public void setNumber(int number){
	this.number=number;
	}
	public int getNumber(){
		return number;
	}
	public void setPassword(String password){
	this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setCollege(String college){
	this.college=college;
	}
	public String getCollege(){
		return college;
	}
	public void setNickName(String nickName){
	this.nickName=nickName;
	}
	public String getNickName(){
		return nickName;
	}
	public void setAvatar(String avatar){
	this.avatar=avatar;
	}
	public String getAvatar(){
		return avatar;
	}
	public void setSignature(String signature){
	this.signature=signature;
	}
	public String getSignature(){
		return signature;
	}
	public void setPhoneNumber(String phoneNumber){
	this.phoneNumber=phoneNumber;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public void setWhetherAnchor(int whetherAnchor){
	this.whetherAnchor=whetherAnchor;
	}
	public int getWhetherAnchor(){
		return whetherAnchor;
	}
	public void setFollowNumber(int followNumber){
	this.followNumber=followNumber;
	}
	public int getFollowNumber(){
		return followNumber;
	}
	public void setRegistrationTime(Date registrationTime){
	this.registrationTime=registrationTime;
	}
	public Date getRegistrationTime(){
		return registrationTime;
	}
}

