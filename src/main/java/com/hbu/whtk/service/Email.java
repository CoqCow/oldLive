package com.hbu.whtk.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class Email {
    public Long id;
    public String fromEmail;//发送方
    public String toEmail;//接受方
    public String template;//模板
    public Date sendDate;//发送时间
    public Integer readStatus=0;//0发送成功没有读取 >0表示读取的次数
}
