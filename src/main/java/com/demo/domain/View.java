package com.demo.domain;

import java.util.Date;

public class View {
    private int liveId;
    private int userId;
    private int viewDuration;
    private int userUpvoteNum;
    private int sendGiftNum;
    private Date viewTime;

    public int getLiveId() {
        return liveId;
    }

    public void setLiveId(int liveId) {
        this.liveId = liveId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getViewDuration() {
        return viewDuration;
    }

    public void setViewDuration(int viewDuration) {
        this.viewDuration = viewDuration;
    }

    public int getUserUpvoteNum() {
        return userUpvoteNum;
    }

    public void setUserUpvoteNum(int userUpvoteNum) {
        this.userUpvoteNum = userUpvoteNum;
    }

    public int getSendGiftNum() {
        return sendGiftNum;
    }

    public void setSendGiftNum(int sendGiftNum) {
        this.sendGiftNum = sendGiftNum;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }
}
