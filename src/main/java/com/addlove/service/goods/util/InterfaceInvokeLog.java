package com.addlove.service.goods.util;

import java.util.GregorianCalendar;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;


/**
 * 调用接口日志
 * @author lw
 */
public class InterfaceInvokeLog {
    /**
     * 正常文件名
     */
    private static final String FILE_PATTERN = "%1$s-interface-invoke-%2$s.log";
    
    /**
     * 异常文件名称
     */
    private static final String ERROR_FILE_PATTERN = "%1$s-interface-invoke-error-%2$s.log";

    /**
     * 物流LIS系统对应的正常文件名
     */
    private static final String IOT_FILE_PATTERN = "%1$s-invoke-iot-%2$s.log";
    
    /**
     * 物流LIS系统对应的异常文件名称
     */
    private static final String IOT_ERROR_FILE_PATTERN = "%1$s-invoke-iot-error-%2$s.log";
    
    /**
     * 日志id
     */
    private String logId;

    /**
     * 原服务名称
     */
    private String originalService;

    /**
     * 源服务id
     */
    private String originalIp;

    /**
     * 目标服务名称
     */
    private String targetService;

    /**
     * 目标服务ip
     */
    private String targetIp;

    /**
     * 请求url
     */
    private String url;

    /**
     * 访问开始时间
     */
    private GregorianCalendar callTime;

    /**
     * 耗时（单位:毫秒）
     */
    private long costTime;

    /**
     * 调用返回的错误信息
     */
    private String errorInfo = "";

    /**
     * 请求消息体
     */
    private String msgBody;

    /**
     * 响应消息体
     */
    private String resultMsg;

    /**
     * code码
     */
    private int code;

    /**
     * 调用链条日志key
     */
    private String traceKey;

    /**
     * 前一个序号
     */
    private int previousNo;

    /**
     * 当前序号
     */
    private int number;

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 日
     */
    private String day;

    /**
     * 日志所在文件名称
     */
    private String fileName;

    /**
     * Creates a new instance of InterfaceInvokeLog.
     */
    public InterfaceInvokeLog() {
        logId = UUID.randomUUID().toString();
        year = DateUtil.getCurrentTime(DateUtil.FORMAT_YEAR_YYYY);
        month = DateUtil.getCurrentTime(DateUtil.FORMAT_YYYYMM);
        day = DateUtil.getCurrentTime(DateUtil.FORMAT_YYYYMMDD);
    }

    /**
     * 返回 logId
     * @return logId
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 设置  logId
     * @param logId value
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * 返回 originalService
     * @return originalService
     */
    public String getOriginalService() {
        return originalService;
    }

    /**
     * 设置  originalService
     * @param originalService value
     */
    public void setOriginalService(String originalService) {
        this.originalService = originalService;
    }

    /**
     * 返回 originalIp
     * @return originalIp
     */
    public String getOriginalIp() {
        return originalIp;
    }

    /**
     * 设置  originalIp
     * @param originalIp value
     */
    public void setOriginalIp(String originalIp) {
        this.originalIp = originalIp;
    }

    /**
     * 返回 targetService
     * @return targetService
     */
    public String getTargetService() {
        return targetService;
    }

    /**
     * 设置  targetService
     * @param targetService value
     */
    public void setTargetService(String targetService) {
        this.targetService = targetService;
    }

    /**
     * 返回 targetIp
     * @return targetIp
     */
    public String getTargetIp() {
        return targetIp;
    }

    /**
     * 设置  targetIp
     * @param targetIp value
     */
    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    /**
     * 返回 url
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置  url
     * @param url value
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 返回 callTime
     * @return callTime
     */
    public GregorianCalendar getCallTime() {
        return callTime;
    }

    /**
     * 设置  callTime
     * @param callTime value
     */
    public void setCallTime(GregorianCalendar callTime) {
        this.callTime = callTime;
    }

    /**
     * 返回 costTime
     * @return costTime
     */
    public long getCostTime() {
        return costTime;
    }

    /**
     * 设置  costTime
     * @param costTime value
     */
    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    /**
     * 返回 errorInfo
     * @return errorInfo
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * 设置  errorInfo
     * @param errorInfo value
     */
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 返回 msgBody
     * @return msgBody
     */
    public String getMsgBody() {
        return msgBody;
    }

    /**
     * 设置  msgBody
     * @param msgBody value
     */
    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    /**
     * 返回 resultMsg
     * @return resultMsg
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * 设置  resultMsg
     * @param resultMsg value
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    /**
     * 返回 code
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置  code
     * @param code value
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回 traceKey
     * @return traceKey
     */
    public String getTraceKey() {
        return traceKey;
    }

    /**
     * 设置  traceKey
     * @param traceKey value
     */
    public void setTraceKey(String traceKey) {
        this.traceKey = traceKey;
    }

    /**
     * 返回 previousNo
     * @return previousNo
     */
    public int getPreviousNo() {
        return previousNo;
    }

    /**
     * 设置  previousNo
     * @param previousNo value
     */
    public void setPreviousNo(int previousNo) {
        this.previousNo = previousNo;
    }

    /**
     * 返回 number
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * 设置  number
     * @param number value
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 返回 year
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * 设置  year
     * @param year value
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * 返回 month
     * @return month
     */
    public String getMonth() {
        return month;
    }

    /**
     * 设置  month
     * @param month value
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * 返回 day
     * @return day
     */
    public String getDay() {
        return day;
    }

    /**
     * 设置  day
     * @param day value
     */
    public void setDay(String day) {
        this.day = day;
    }
    
    /**
     * 返回 filePath
     * @return filePath
     */
    public String getFileName() {
        if (StringUtils.isBlank(targetService)) {
            return "";
        }
        
        if (targetService.startsWith("ngoc-")) { // ngoc内部服务
            if (this.code == 0) {
                this.fileName = String.format(FILE_PATTERN, this.originalService, DateUtil.getTodateString());
            } else {
                this.fileName = String.format(ERROR_FILE_PATTERN, this.originalService, DateUtil.getTodateString());
            }
        } else if (targetService.startsWith("iot-")) { // 请求中移
            if (this.code == 0) {
                this.fileName = String.format(IOT_FILE_PATTERN, this.originalService, DateUtil.getTodateString());
            } else {
                this.fileName = String.format(IOT_ERROR_FILE_PATTERN, this.originalService, DateUtil.getTodateString());
            }
        }
        
        return this.fileName;
    }
}
