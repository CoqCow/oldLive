package com.hbu.whtk.controller;

import com.hbu.whtk.service.EmailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
@RequestMapping("/test")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @ResponseBody
    @RequestMapping("/send")
    public String sendEmail() throws MessagingException, IOException, TemplateException {
        emailService.sendEamil();
        return "hello";
    }
}
