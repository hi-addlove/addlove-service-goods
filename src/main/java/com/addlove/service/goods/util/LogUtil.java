package com.addlove.service.goods.util;

import java.util.GregorianCalendar;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import com.alibaba.fastjson.JSONObject;

/**
 * @author lw
 */
public class LogUtil {

    /**
     * 获取日志对应的traceKey.
     *
     * @return traceKey
     */
    public static String getLogTraceKey() {
        InterfaceLog logInfo = InterfaceLogThreadLocal.getInterfaceCallInfo();
        String traceKey = "";
        if (null != logInfo) {
            traceKey = logInfo.getTraceKey();
            return StringUtils.isBlank(traceKey) ? "NAN" : traceKey;
        }

        return "NA";
    }

    /**
     * 获取当前服务名.
     *
     * @return serviceName
     */
    public static String getServiceName() {
        InterfaceLog logInfo = InterfaceLogThreadLocal.getInterfaceCallInfo();
        String serviceName = "";
        if (null != logInfo) {
            serviceName = logInfo.getIntervieweeName();
            return StringUtils.isBlank(serviceName) ? "NAN" : serviceName;
        }

        return "NA";
    }

    /**
     * 获取日志对应的traceKey.
     *
     * @return traceKey
     */
    public static String getLogId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 本系统作为服务端，接收到请求，并异常响应的日志，请求和响应结果为一条日志
     * @param msg 日志
     * @param code code码
     */
    public static void logInterfaceExceptionMsg(String msg, int code) {
        InterfaceLog logInfo = InterfaceLogThreadLocal.getInterfaceCallInfo();
        if (null == logInfo) {
            return;
        }

        logInfo.setCostTime((new GregorianCalendar()).getTimeInMillis() - logInfo.getCallTime().getTimeInMillis());
        logInfo.setResultMsg(msg);
        logInfo.setCode(code);
        InterfaceLogHandler.logException(logInfo);
    }

    /**
     * 本系统作为服务端，接收到请求，并正常响应的日志，请求和响应结果为一条日志
     * @param msg 日志
     * @param code code码
     */
    public static void logInterfaceMsg(Object msg, int code) {
        InterfaceLog logInfo = InterfaceLogThreadLocal.getInterfaceCallInfo();
        if (null == logInfo) {
            return;
        }

        logInfo.setCostTime((new GregorianCalendar()).getTimeInMillis() - logInfo.getCallTime().getTimeInMillis());
        logInfo.setResultMsg(null == msg ? null : JSONObject.toJSONString(msg));
        logInfo.setCode(code);
        InterfaceLogHandler.log(logInfo);
    }
    
    /**
     * 本系统作为客户端，请求服务端，出现异常时，输出请求日志和异常返回的日志，请求和响应结果为一条日志
     *
     * @param callTime 访问时间
     * @param visitorServerName 访问者名称
     * @param serverName 服务名称
     * @param url 调用地址
     * @param requestBody 请求消息
     * @param errorInfo 异常信息
     * @param msg 消息
     * @param code code码
     */
    public static void logInvokeInterfaceExceptionMsg(GregorianCalendar callTime, String visitorServerName,
            String serverName, String url, String requestBody, String errorInfo, String msg, int code) {
        InterfaceLog logInfo = InterfaceLogThreadLocal.getInterfaceCallInfo();
        if (null == logInfo) {
            return;
        }
        String localIp = NetUtil.getLocalIp();
        InterfaceInvokeLog log = new InterfaceInvokeLog();
        log.setTraceKey(logInfo.getTraceKey());
        log.setNumber(logInfo.getNumber());
        log.setPreviousNo(logInfo.getPreviousNo());
        log.setOriginalIp(localIp);
        log.setOriginalService(visitorServerName);
        log.setUrl(url);
        log.setTargetIp("");
        log.setTargetService(serverName);
        log.setCallTime(callTime);
        log.setCostTime((new GregorianCalendar()).getTimeInMillis() - callTime.getTimeInMillis());
        log.setMsgBody(requestBody);
        log.setErrorInfo(errorInfo);
        log.setResultMsg(msg);
        log.setCode(code);
        InterfaceLogHandler.logInvokeException(log);
    }

    /**
     * 本系统作为客户端，请求服务端，正常返回时，输出请求日志和返回的日志，请求和响应结果为一条日志
     *
     * @param callTime 访问时间
     * @param visitorServerName 访问者名称
     * @param serverName 服务名称
     * @param url 调用地址
     * @param requestBody 请求消息
     * @param msg 消息
     * @param code code码
     */
    public static void logInvokeInterfaceMsg(GregorianCalendar callTime, String visitorServerName, String serverName,
            String url, String requestBody, String msg, int code) {
        InterfaceLog logInfo = InterfaceLogThreadLocal.getInterfaceCallInfo();
        if (null == logInfo) {
            return;
        }

        String localIp = NetUtil.getLocalIp();
        InterfaceInvokeLog log = new InterfaceInvokeLog();
        log.setTraceKey(logInfo.getTraceKey());
        log.setNumber(logInfo.getNumber());
        log.setPreviousNo(logInfo.getPreviousNo());
        log.setOriginalIp(localIp);
        log.setOriginalService(visitorServerName);
        log.setUrl(url);
        log.setTargetIp("");
        log.setTargetService(serverName);
        log.setCallTime(callTime);
        log.setCostTime((new GregorianCalendar()).getTimeInMillis() - log.getCallTime().getTimeInMillis());
        log.setMsgBody(requestBody);
        log.setResultMsg(msg);
        log.setCode(code);
        InterfaceLogHandler.logInvoke(log);
    }

    /**
     * 本系统作为客户端，请求服务端，正常返回时，输出请求日志和返回的日志，请求和响应结果为一条日志
     *
     * @param loggers 打印日志对象
     * @param callTime 访问时间
     * @param visitorServerName 访问者名称
     * @param serverName 服务名称
     * @param url 调用地址
     * @param requestBody 请求消息
     * @param msg 消息
     * @param code code码
     */
    public static void externalInvokeLog(Logger[] loggers, GregorianCalendar callTime,
            String visitorServerName, String serverName,
            String url, String requestBody, String msg, int code) {
        InterfaceLog logInfo = InterfaceLogThreadLocal.getInterfaceCallInfo();
        if (null == logInfo) {
            return;
        }

        String localIp = NetUtil.getLocalIp();
        InterfaceInvokeLog log = new InterfaceInvokeLog();
        log.setTraceKey(logInfo.getTraceKey());
        log.setNumber(logInfo.getNumber());
        log.setPreviousNo(logInfo.getPreviousNo());
        log.setOriginalIp(localIp);
        log.setOriginalService(visitorServerName);
        log.setUrl(url);
        log.setTargetIp("");
        log.setTargetService(serverName);
        log.setCallTime(callTime);
        log.setCostTime((new GregorianCalendar()).getTimeInMillis() - log.getCallTime().getTimeInMillis());
        log.setMsgBody(requestBody);
        log.setResultMsg(msg);
        log.setCode(code);
        InterfaceLogHandler.logOutInvoke(loggers, log);
    }

    /**
     * 本系统作为客户端，请求服务端，出现异常时，输出请求日志和异常返回的日志，请求和响应结果为一条日志
     *
     * @param loggers 打印日志对象
     * @param callTime 访问时间
     * @param visitorServerName 访问者名称
     * @param serverName 服务名称
     * @param url 调用地址
     * @param requestBody 请求消息
     * @param errorInfo 异常信息
     * @param msg 消息
     * @param code code码
     */
    public static void externalInvokeErrorLog(Logger[] loggers, GregorianCalendar callTime, String visitorServerName,
            String serverName, String url, String requestBody, String errorInfo, String msg, int code) {
        InterfaceLog logInfo = InterfaceLogThreadLocal.getInterfaceCallInfo();
        if (null == logInfo) {
            return;
        }
        String localIp = NetUtil.getLocalIp();
        InterfaceInvokeLog log = new InterfaceInvokeLog();
        log.setTraceKey(logInfo.getTraceKey());
        log.setNumber(logInfo.getNumber());
        log.setPreviousNo(logInfo.getPreviousNo());
        log.setOriginalIp(localIp);
        log.setOriginalService(visitorServerName);
        log.setUrl(url);
        log.setTargetIp("");
        log.setTargetService(serverName);
        log.setCallTime(callTime);
        log.setCostTime((new GregorianCalendar()).getTimeInMillis() - callTime.getTimeInMillis());
        log.setMsgBody(requestBody);
        log.setErrorInfo(errorInfo);
        log.setResultMsg(msg);
        log.setCode(code);
        InterfaceLogHandler.logOutInvokeException(loggers, log);
    }
}
