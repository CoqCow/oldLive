package com.hbu.whtk.goback;

import com.hbu.whtk.base.Anchor;
import com.hbu.whtk.base.Live;
import com.hbu.whtk.base.User;

/**
 * Created by Lenovo on 2018/2/10.
 */
public class BackLivesAndAnchors {
    private Anchor anchor;
    private Live live;
    private User user;
    public Anchor getAnchor() {
        return anchor;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Live getLive() {
        return live;
    }

    public void setLive(Live live) {
        this.live = live;
    }
}
