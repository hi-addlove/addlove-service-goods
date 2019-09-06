package com.addlove.service.goods.intercept;

import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.addlove.service.goods.config.UserInit;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.LoginMsgCode.LoginCode;
import com.addlove.service.goods.context.SysUserDataContextHolder;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.SysUserModel;
import com.addlove.service.goods.model.UsrUserModel;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.util.DES3;
import com.addlove.service.goods.util.JsonUtil;
import com.addlove.service.goods.util.LoggerEnhance;
import com.alibaba.fastjson.JSONObject;

/**
 * 拦截器
 * @author lw
 *
 */
@Component
public class PermissionIntercept implements HandlerInterceptor {
    /**PermissionIntercept类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionIntercept.class);
    
    @Autowired
    private GoodsCommonService commonService;
    
    @Value("${addlove.user.key}")
    private String key;

    /**
     * 权限验证
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String encryptUserCode = request.getHeader("userCode");
        LoggerEnhance.info(LOGGER, "拦截器拦截外部系统传输的UserCode密文为:{}", encryptUserCode);
        if (StringUtils.isBlank(encryptUserCode)) {
            PrintWriter print = response.getWriter();
            print.write(JsonUtil.toJSONString(
                    ResponseMessage.fail("非法请求,消息头缺少userCode", GoodsResponseCode.HEADER_ERROR_CODE.getCode())));
            return false;
        }
        String encryptOrgCode = request.getHeader("orgCode");
        LoggerEnhance.info(LOGGER, "拦截器拦截外部系统传输的OrgCode密文为:{}", encryptOrgCode);
        if (StringUtils.isBlank(encryptOrgCode)) {
            PrintWriter print = response.getWriter();
            print.write(JsonUtil.toJSONString(
                    ResponseMessage.fail("非法请求,消息头缺少orgCode", GoodsResponseCode.HEADER_ERROR_CODE.getCode())));
            return false;
        }
        String userCode = DES3.decrypt(encryptUserCode, key);
        UsrUserModel userModel = this.commonService.getUserByCode(userCode.trim().toString());
        if (null == userModel) {
            PrintWriter print = response.getWriter();
            print.write(JsonUtil.toJSONString(
                    ResponseMessage.fail(LoginCode.ACCOUNT.getMsg(), LoginCode.ACCOUNT.getCode())));
            return false;
        }
        String orgCode = DES3.decrypt(encryptOrgCode, key);
        OrgManageModel orgModel = this.commonService.getOrgModel(orgCode.trim().toString());
        if (null == orgModel) {
            PrintWriter print = response.getWriter();
            print.write(JsonUtil.toJSONString(
                    ResponseMessage.fail(LoginCode.ORGANIZE.getMsg(), LoginCode.ORGANIZE.getCode())));
            return false;
        }
        SysUserDataContextHolder.clearSysUserData();
        SysUserModel sysUserModel = new SysUserModel();
        sysUserModel.setUserModel(userModel);
        sysUserModel.setOrgModel(orgModel);
        SysUserDataContextHolder.setSysUserData(sysUserModel);
        if (request.getRequestURI().equals(UserInit.LOGINURL)) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(UserInit.SESSIONKEY)) {
                    Map<String, Long> session = UserInit.getSession();
                    Long loginTime = session.get(cookie.getValue());
                    if (null == loginTime) {
                        continue;
                    }
                    long now = System.currentTimeMillis();
                    if ((60 * 1000 * 30) > (now - loginTime)) {
                        session.put(cookie.getValue(), now); //重置登录时间
                        return true;
                    } else {
                        session.remove(cookie.getValue()); //将过期信息从服务器清除
                    }
                }
            }
        }
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("code", LoginCode.SESSION.getCode());
        json.put("msg", LoginCode.SESSION.getMsg());
        writer.write(json.toJSONString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
