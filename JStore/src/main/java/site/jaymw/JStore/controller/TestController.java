package site.jaymw.JStore.controller;


import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.jaymw.JStore.config.SmsConfig;
import site.jaymw.JStore.domain.ApiResult;
import site.jaymw.JStore.domain.SliderCheck;
import site.jaymw.JStore.utils.CommonUtils;
import site.jaymw.JStore.utils.SliderCheckUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private SmsConfig smsConfig;

    @RequestMapping("/send")
    public Object send(@RequestParam(value = "phone", required = true) String phone) throws HTTPException, IOException {
        SmsSingleSender sender = new SmsSingleSender(smsConfig.getAppid(), smsConfig.getAppkey());
        String[] params = {CommonUtils.createRandom(true, 6), "5"};
        SmsSingleSenderResult result = sender.sendWithParam("86", phone,
                smsConfig.getRegisterId(), params, smsConfig.getSign(), "", "");
        System.out.println(result);
        return result;
    }

    @GetMapping("/code")
    public Object code(HttpServletRequest request) {
        SliderCheck build = SliderCheckUtils.build();
        request.getSession().setAttribute("slider-X-index", build.getPuzzleXAxis());
        build.setPuzzleXAxis("");
        build.setSourceImg("");
        return ApiResult.buildSuccess(build, "图片验证加载成功");
    }

    @GetMapping("/login")
    public Object login(
            HttpServletRequest request,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "x", required = true) String x
    ) {
        String index = (String) request.getSession().getAttribute("slider-X-index");
        return ApiResult.buildSuccess(index);
    }

    @GetMapping("/get-slider-img")
    public Object getSliderImg(HttpServletRequest request) {
        SliderCheck build = SliderCheckUtils.build();
        request.getSession().setAttribute("slider-X-index", build.getPuzzleXAxis());
        build.setPuzzleXAxis("");
        build.setSourceImg("");
        return ApiResult.buildSuccess(build, "图片验证加载成功");
    }

    /**
     * 滑块验证
     *
     * @return com.payment.platform.v2.common.bean.vo.ApiResult
     * @Author qnz
     * @Date 2019/8/27 10:49
     * @Param [request, distance]
     **/
    @PostMapping("/slider-check")
    public Object sliserCheck(HttpServletRequest request, Long distance) {
        String index = (String) request.getSession().getAttribute("slider-X-index");
        if (distance >= (Long.valueOf(index) - 5) && distance <= (Long.valueOf(index) + 5)) {
            return ApiResult.buildSuccess(null, "验证成功");
        }
        return ApiResult.buildError("验证失败");
    }

}
