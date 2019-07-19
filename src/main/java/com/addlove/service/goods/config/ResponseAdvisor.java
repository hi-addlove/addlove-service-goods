package com.addlove.service.goods.config;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.addlove.service.goods.message.CommonResponseCode;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.util.LogUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 响应结果处理
 * @author lw
 */
@RestControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseAdvisor.class);

    private static final String CONSTANT_RESULT = "result";

    private static final String CONSTANT_FORMAT = "format";

    private static final String CONSTANT_ACCEPT = "Accept";

    private static final String CONSTANT_JSONP = "jsonp";

    private static final String CONSTANT_APPLICATION_JSONP = "application/jsonp";

    private static final String CONSTANT_APPLICATION_JSON = "application/json";

    /**
     * Whether this component supports the given controller method return type and the selected
     * {@code HttpMessageConverter} type.
     *
     * @param returnType
     *            the return type
     * @param converterType
     *            the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked; {@code false} otherwise
     */
    @Override
    public final boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 响应返回前的处理
     *
     * @param body
     *            消息body
     * @param returnType
     *            返回类型
     * @param selectedContentType
     *            选择类型
     * @param selectedConverterType
     *            选择转换类型
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @return 响应结果
     */
    @Override
    public final Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {

        String traceKey = LogUtil.getLogTraceKey();
        String requestPath = request.getURI().getPath();
        if (StringUtils.isBlank(requestPath)) {
            LOGGER.error("Result Path is null exception,traceKey:{}, code:{},msg:{}", traceKey, CommonResponseCode.UNKNOWN_ERROR,
                    JSONObject.toJSONString(body));
            ResponseMessage msg = ResponseMessage.fail(CommonResponseCode.UNKNOWN_ERROR.getMsg(),
                    CommonResponseCode.UNKNOWN_ERROR.getCode());
            LogUtil.logInterfaceExceptionMsg(null == msg ? null : msg.toJSONString(), msg.getCode());
            return msg;
        }

        if (requestPath.startsWith("/error")) {
            ResponseMessage rst = null;
            if (body instanceof Map) {
                Object obj = ((Map<?, ?>) body).get(CONSTANT_RESULT);
                if (obj instanceof ResponseMessage) {
                    rst = (ResponseMessage) obj;
                }
            }

            if (null == rst) {
                LOGGER.error("Global Exception,traceKey:{}, code:{},msg:{}", traceKey, CommonResponseCode.SYS_GLOBAL_ERROR,
                        JSONObject.toJSONString(body));
                rst = ResponseMessage.fail(CommonResponseCode.SYS_GLOBAL_ERROR.getMsg(),
                        CommonResponseCode.SYS_GLOBAL_ERROR.getCode());
            }

            LogUtil.logInterfaceExceptionMsg(null == rst ? null : rst.toJSONString(), rst.getCode());
            return rst;
        }

        if (!requestPath.startsWith("/service")) {
            LogUtil.logInterfaceMsg("", CommonResponseCode.SUCCESS.getCode());
            return body;
        }

        if (body instanceof ResponseMessage) {
            ResponseMessage result = (ResponseMessage) body;
            HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
            HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();
            resp.setCharacterEncoding("UTF-8");

            if (this.isAjaxUrl(req)) {
                resp.setContentType("application/json;charset=UTF-8");
            } else {
                resp.setContentType("text/html;charset=UTF-8");
            }

            StringBuffer responseSb = new StringBuffer();
            if (this.isJsonp(req)) {
                String callback = req.getParameter("callback");
                responseSb.append("(").append(callback).append(result.toJSONString()).append(")");
            } else {
                LogUtil.logInterfaceMsg("", CommonResponseCode.SUCCESS.getCode());
                return result;
            }
        }

        // 异常信息若包含result对象，则直接返回result对象
        if (body instanceof Map) {
            Object obj = ((Map<?, ?>) body).get(CONSTANT_RESULT);
            if (obj instanceof ResponseMessage) {
                LogUtil.logInterfaceMsg("", CommonResponseCode.SUCCESS.getCode());
                return obj;
            }
        }

        ResponseMessage responseMessage = new ResponseMessage(body);
        LogUtil.logInterfaceMsg("", CommonResponseCode.SUCCESS.getCode());
        return responseMessage;
    }

    /**
     * 是否为ajax请求
     *
     * @param request
     *            请求对象
     * @return true 表示是ajax
     */
    private boolean isAjaxUrl(HttpServletRequest request) {
        String format = request.getParameter(CONSTANT_FORMAT);
        if ("json".equals(format) || CONSTANT_JSONP.equals(format)) {
            return true;
        }

        String accept = request.getHeader(CONSTANT_ACCEPT);
        if (null == accept) {
            return false;
        }

        if (accept.contains(CONSTANT_APPLICATION_JSON) || accept.contains(CONSTANT_APPLICATION_JSONP)) {
            return true;
        }

        return false;
    }

    /**
     * 是否为jsonp请求
     *
     * @param request
     *            请求对象
     * @return true 表示是jsonp
     */
    private boolean isJsonp(HttpServletRequest request) {
        String format = request.getParameter(CONSTANT_FORMAT);
        if (CONSTANT_JSONP.equals(format)) {
            return true;
        }

        String accept = request.getHeader(CONSTANT_ACCEPT);
        if (null == accept) {
            return false;
        }

        if (accept.contains(CONSTANT_APPLICATION_JSONP)) {
            return true;
        }
        return false;
    }
}
