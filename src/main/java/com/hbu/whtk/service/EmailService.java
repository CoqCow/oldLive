package com.hbu.whtk.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.*;

@Service
public class EmailService {

    private FreeMarkerConfigurer freeMarkerConfigurer=new FreeMarkerConfigurer();
    @Value("${email-listening-url-base}")
    public String LISTENING_BASE;

    public void sendEamil() throws MessagingException, IOException, TemplateException {
        SendEmailReq sendEmailReq = new SendEmailReq();
        sendEmailReq.template = "approval_notification";
        sendEmailReq.from = "1004337524@qq.com";
        sendEmailReq.to = "1004337524@qq.com";
        sendEmailReq.kvMap=new HashMap<>();
        JavaMailSenderImpl javaMailSender = getSender();
        MimeMessage mimeMessage = loadTemplate(sendEmailReq, javaMailSender);
        javaMailSender.send(mimeMessage);
    }

    public MimeMessage loadTemplate(SendEmailReq sendEmailReq, JavaMailSenderImpl javaMailSender) throws MessagingException, IOException, TemplateException {
        switch (sendEmailReq.template) {
            case "approval_notification":
                sendEmailReq.title = "Wecash Crédito: Sua solicitação foi pré-aprovada";
                break;
            case "refuse_notification":
                sendEmailReq.title = "Wecash Crédito: Seu empréstimo não foi aprovado";
                break;
            case "pending_notification":
                sendEmailReq.title = "Wecash Crédito: Pendência";
                break;
            case "password_recovery":
                sendEmailReq.title = "Wecash Crédito: recuperação de senha";
                break;
            default:
                throw new IllegalArgumentException("Mail template does not exist");
        }
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(sendEmailReq.from);
        helper.setTo(sendEmailReq.to);
        helper.setSubject(sendEmailReq.title);

        //保存改模板需要的参数
        Map<String, Object> model = new HashMap();
        Iterator iter = sendEmailReq.kvMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            if (key.equals("fields")) {
                StringBuilder fields = new StringBuilder();
                int i = 0;
                for (String field : (ArrayList<String>) sendEmailReq.kvMap.get("fields")) {
                    fields.append(++i + "、" + field + "<br>");
                }
                model.put("fields", fields);
                continue;
            }
            Object val = entry.getValue();
            model.put(key, val);
        }

        //model.put("listening", toBase + saveLog(sendEmailReq));
        //model.put("listening", "<img src='" + LISTENING_BASE + saveLog(sendEmailReq) + "'width=4px height=4px type='image'/>");
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(sendEmailReq.template + ".html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        //Open listening
        int index = html.indexOf("</body>");
        Long emailId = 1L; //saveLog(sendEmailReq);
        String newHtml = html.substring(0, index) + "<img src='" + "" + emailId + "'width=4px height=4px type='image'/><br>" + html.substring(index);
        System.out.println(newHtml);
        helper.setText(newHtml, true);
        return message;
    }

    //先保存邮件log，模板中需要知道邮件的id，发生异常后回滚事务
    public Long saveLog(SendEmailReq sendEmailReq) {
        Email email = new Email();
        email.setFromEmail(sendEmailReq.from);
        email.setToEmail(sendEmailReq.to);
        email.setSendDate(new Date());
        email.setTemplate(sendEmailReq.template);
        //emailRepository.save(email);
        return email.getId();
    }

    public JavaMailSenderImpl getSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.exmail.qq.com");
        javaMailSender.setUsername("1004337524@qq.com");
        javaMailSender.setPassword("");
        javaMailSender.setPort(587);
        javaMailSender.setDefaultEncoding("UTF-8");
        Properties props = new Properties();
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}
