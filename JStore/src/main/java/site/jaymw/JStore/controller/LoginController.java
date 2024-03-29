package site.jaymw.JStore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import site.jaymw.JStore.domain.ApiResult;
import site.jaymw.JStore.domain.SliderCheck;
import site.jaymw.JStore.utils.SliderCheckUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/api/login")
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public static final String SLIDER_X_KEY = "slider-X-index";
    public static final String LOGIN_FAIL_COUNT = "login-fail_count";

    @GetMapping("/getCode")
    public Object code(HttpServletRequest request) {
        SliderCheck build = SliderCheckUtils.build();
        request.getSession().setAttribute(SLIDER_X_KEY, build.getPuzzleXAxis());
        build.setPuzzleXAxis("");
        build.setSourceImg("");
        return ApiResult.buildSuccess(build, "图片验证加载成功");
    }

    @PostMapping("/checkSlider")
    public Object sliserCheck(HttpServletRequest request, @RequestBody Map body) {
        String distance = (String) body.get("distance");
        String index = (String) request.getSession().getAttribute(SLIDER_X_KEY);
        if (Double.valueOf(distance) >= (Double.valueOf(index) - 5) && Double.valueOf(distance) <= (Double.valueOf(index) + 5)) {
            return ApiResult.buildSuccess(null, "验证成功");
        }
        return ApiResult.buildError("验证失败");
    }

    @GetMapping("/signIn")
    public Object login(
            HttpServletRequest request,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        if(true){//登录失败 次数+1，超高

        }
        return ApiResult.buildError("失败");
    }


}
