package site.jaymw.JStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.jaymw.JStore.config.EmailConfig;
import site.jaymw.JStore.domain.Email;
import site.jaymw.JStore.utils.CommonUtils;
import site.jaymw.JStore.utils.MailUtils;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    JavaMailSender jms;

    @Autowired
    private MailUtils mailUtils;


    @RequestMapping("/send")
    public String send(@RequestParam(value = "mail", required = true) String mail) {
        Email email = new Email();

        email.setEmailContent("主体内容");
        email.setSubject("主题内容");
        email.setRecieverEmailAddress(mail);
        email.setRecieverName("小傻瓜");
        String code = CommonUtils.createRandom(true, 6);
        mailUtils.sendMail(email, "测试", code);
        return "发送成功!";
    }
}
