package com.hbu.whtk.goback;

import java.util.Date;

public class Backfollowed extends BackUserAndAnchor {
    public Date followTime;

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }
}
