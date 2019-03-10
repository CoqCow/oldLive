package com.hbu.whtk.service;

import lombok.ToString;

import java.util.HashMap;

@ToString
public class SendEmailReq {
    public String from;//发送方
    public String to;//接受方
    public String title;//标题
    public String template;//模板
    //模板对应的参数 模板不同，参数不同
    public HashMap<String, Object> kvMap;// 自定义参数，用于填充模板
}
