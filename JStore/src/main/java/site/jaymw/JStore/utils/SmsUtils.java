package site.jaymw.JStore.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import site.jaymw.JStore.config.SmsConfig;

import java.io.IOException;
import java.util.Date;

public class SmsUtils {

    @Autowired
    private SmsConfig smsConfig;

    public static boolean sendSms(SmsConfig smsConfig,String phoneNumber) {
        try {
            String[] params = {"5678", "5"};
            SmsSingleSender ssender = new SmsSingleSender(smsConfig.getAppid(), smsConfig.getAppkey());
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    smsConfig.getRegisterId(), params, smsConfig.getSign(), "", "");
            System.out.println(result);

            return result.result == 0;
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return false;
    }

}
