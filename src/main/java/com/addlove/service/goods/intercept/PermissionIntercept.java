package com.addlove.service.goods.intercept;

import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.addlove.service.goods.config.UserInit;
import com.addlove.service.goods.constants.LoginMsgCode.LoginCode;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author lw
 *
 */
public class PermissionIntercept implements HandlerInterceptor {

    /**
     * 权限验证
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(UserInit.SESSIONKEY)) {
                    Map<String, Long> session = UserInit.getSession();
                    Long loginTiem = session.get(cookie.getValue());
                    if (loginTiem == null) {
                        continue;
                    }
                    long now = System.currentTimeMillis();
                    if ((60 * 1000 * 30) > (now - loginTiem)) {
                        session.put(cookie.getValue(), now); //重置登录时间
                        return true;
                    } else {
                        session.remove(cookie.getValue()); //将过期信息从服务器清除
                    }
                }
            }
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("code", LoginCode.SESSION.getCode());
        json.put("msg", LoginCode.SESSION.getMsg());
        writer.write(json.toJSONString());*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
