package site.jaymw.JStore.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import site.jaymw.JStore.config.EmailConfig;
import site.jaymw.JStore.domain.Email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class MailUtils {

    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    /**
     * 邮件验证码模版
     */
    private static String codeMailTemplat = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
            "  <title></title>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <p>尊敬的用户：</p>\n" +
            "  <p>你的【%s】验证码为： <span style=\"font-size: 20px;\">%s</span> ，有效期30分钟，请勿将此验证码泄漏给他人。</p>\n" +
            "  <p style=\"text-indent: 29em;\">--%s系统</p>\n" +
            "  <p style=\"text-indent: 29em;\">%s</p>\n" +
            "</body>\n" +
            "</html>";


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    @Autowired
    private EmailConfig emailConfig;

    /**
     * html 网页发送
     * 该方法为同步方法
     **/
    public void sendMail(final Email emailModel, String mailType, String code) {
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            //设置发件人
            mimeMessageHelper.setFrom(emailConfig.getPosterAdress());
            //设置接收人
            mimeMessageHelper.setTo(emailModel.getRecieverEmailAddress());
            //设置发送主题
            mimeMessageHelper.setSubject(emailModel.getSubject());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatter.format(new Date());
            String mailStr = String.format(codeMailTemplat,mailType, code, emailConfig.getPosterName(), time);
            //设置发送内容
            mimeMessageHelper.setText(mailStr, true);
            System.out.println();
            javaMailSender.send(mimeMessage);
            logger.info("邮箱已返送至[{}]邮箱！", emailModel.getRecieverName());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MailException e) {
            logger.error("邮箱异常：{}", e);
        }
    }

}
