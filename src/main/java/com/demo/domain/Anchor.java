package com.demo.domain;

import java.util.Date;

public class Anchor {
    private int userId;
    private String anchorCategory;
    private int playTime;
    private int totalUpvoteNum;
    private int followedNumber;
    private Date applyAnchorTime;
    private int totalGiftsNumber;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAnchorCategory() {
        return anchorCategory;
    }

    public void setAnchorCategory(String anchorGategory) {
        this.anchorCategory = anchorGategory;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getTotalUpvoteNum() {
        return totalUpvoteNum;
    }

    public void setTotalUpvoteNum(int totalUpvoteNum) {
        this.totalUpvoteNum = totalUpvoteNum;
    }

    public int getFollowedNumber() {
        return followedNumber;
    }

    public void setFollowedNumber(int followedNumber) {
        this.followedNumber = followedNumber;
    }

    public Date getApplyAnchorTime() {
        return applyAnchorTime;
    }

    public void setApplyAnchorTime(Date applyAnchorTime) {
        this.applyAnchorTime = applyAnchorTime;
    }

    public int getTotalGiftsNumber() {
        return totalGiftsNumber;
    }

    public void setTotalGiftsNumber(int totalGiftsNumber) {
        this.totalGiftsNumber = totalGiftsNumber;
    }
}
