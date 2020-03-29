package site.jaymw.JStore.interceptor;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import site.jaymw.JStore.domain.ApiResult;
import site.jaymw.JStore.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Gson gson = new Gson();

    /**
     * 进入到controller之前进行拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("Authorization");
        if (token == null) {
            //header里拿不到再尝试从paramter里拿token，回调的情况是把token放url上
            token = request.getParameter("Authorization");
        }
        if (token != null) {
            Claims claims = JwtUtils.parseToken(token);
            if (claims != null) {
                Integer userId = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                //将拿到的信息放入request里，后续controller业务里的request可以取到
                request.setAttribute("user_id", userId);
                request.setAttribute("name", name);
                return true;
            }
        }
        sendJsonMessage(response, ApiResult.buildError("请登录"));
        return false;
    }

    /**
     * 响应数据给前端
     *
     * @param response
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(gson.toJson(obj));
        writer.close();
        response.flushBuffer();
    }
}
