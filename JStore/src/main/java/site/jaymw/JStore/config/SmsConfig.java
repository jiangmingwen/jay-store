package site.jaymw.JStore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")

public class SmsConfig {
    /**
     * 短信应用 SDK APPID
     */
    @Value("${sms.appid}")
    private int appid;

    /**
     * 短信应用SDK appKey
     */
    @Value("${sms.appkey}")
    private String appkey;

    /**
     * 短信应用签名内容
     */
    @Value("${sms.sign}")
    private String sign;

    private String[] phoneNumbers;

    @Value("${sms.register_id}")
    private int registerId;

    @Value("${sms.resetpsw_id}")
    private int resetPasswordId;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    public Integer getResetPasswordId() {
        return resetPasswordId;
    }

    public void setResetPasswordId(Integer resetPasswordId) {
        this.resetPasswordId = resetPasswordId;
    }
}
