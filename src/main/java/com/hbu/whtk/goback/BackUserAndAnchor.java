package com.hbu.whtk.goback;

import com.hbu.whtk.base.Anchor;
import com.hbu.whtk.base.User;

import java.util.Date;

/**
 * Created by whtk210 on 2017-07-18.
 */
public class BackUserAndAnchor extends User {
    public String getAnchorCategory() {
        return anchorCategory;
    }

    public void setAnchorCategory(String anchorCategory) {
        this.anchorCategory = anchorCategory;
    }

    public String anchorCategory;

    public Integer getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Integer playTime) {
        this.playTime = playTime;
    }

    public Integer getTotalUpvoteNum() {
        return totalUpvoteNum;
    }

    public void setTotalUpvoteNum(int totalUpvoteNum) {
        this.totalUpvoteNum = totalUpvoteNum;
    }

    public Integer getFollowedNumber() {
        return followedNumber;
    }

    public void setFollowedNumber(Integer followedNumber) {
        this.followedNumber = followedNumber;
    }

    public Date getApplyAnchorTime() {
        return applyAnchorTime;
    }

    public void setApplyAnchorTime(Date applyAnchorTime) {
        this.applyAnchorTime = applyAnchorTime;
    }

    public Integer getTotalGiftsNumber() {
        return totalGiftsNumber;
    }

    public void setTotalGiftsNumber(Integer totalGiftsNumber) {
        this.totalGiftsNumber = totalGiftsNumber;
    }

    public Integer playTime;
    public Integer totalUpvoteNum;
    public Integer followedNumber;
    public Date applyAnchorTime;
    public Integer totalGiftsNumber;
}
