package com.hbu.whtk.req;

import java.util.Date;

/**
 * Created by Lenovo on 2017/9/13.
 * 这是整体更新User表的请求参数
 */
public class UpdateUserParams {
    //注册后，用户可以更改的信息有：组织名称 姓名 学院 昵称 头像 个性签名
    //更新根据userId来更新
    public String organizationName;
    public String name;
    public String college;
    public String nickName;
    public String avatar;
    public String signature;
    public  String userId;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
