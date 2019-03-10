package com.hbu.whtk.req;

import java.util.Date;

public class LiveStartRequestParams {
    public Integer userId;
    public String liveName;
    public String liveDescription;
    public String liveType;
    public String livePwd;
    public Date liveStartTime;
    public Date liveEndTime;
    public Integer audienceNum;
    public Integer upvoteNum;
    public Integer giftNum;
    public String screenShot;
    public  String liveUrl;

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public String getLiveDescription() {
        return liveDescription;
    }

    public void setLiveDescription(String liveDescription) {
        this.liveDescription = liveDescription;
    }

    public String getLiveType() {
        return liveType;
    }

    public void setLiveType(String liveType) {
        this.liveType = liveType;
    }

    public String getLivePwd() {
        return livePwd;
    }

    public void setLivePwd(String livePwd) {
        this.livePwd = livePwd;
    }

    public Date getLiveStartTime() {
        return liveStartTime;
    }

    public void setLiveStartTime(Date liveStartTime) {
        this.liveStartTime = liveStartTime;
    }

    public Date getLiveEndTime() {
        return liveEndTime;
    }

    public void setLiveEndTime(Date liveEndTime) {
        this.liveEndTime = liveEndTime;
    }

    public Integer getAudienceNum() {
        return audienceNum;
    }

    public void setAudienceNum(Integer audienceNum) {
        this.audienceNum = audienceNum;
    }

    public Integer getUpvoteNum() {
        return upvoteNum;
    }

    public void setUpvoteNum(Integer upvoteNum) {
        this.upvoteNum = upvoteNum;
    }

    public Integer getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(Integer giftNum) {
        this.giftNum = giftNum;
    }

    public String getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(String screenShot) {
        this.screenShot = screenShot;
    }

    @Override
    public String toString() {
        return "LiveStartRequestParams{" +
                "userId=" + userId +
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
                ", liveUrl='" + liveUrl + '\'' +
                '}';
    }
}
