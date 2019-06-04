package com.addlove.service.goods.util;

import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * 接口调用日志信息
 * @author lw
 *
 */
public class InterfaceLog {
    
    /**
     * 正常文件名
     */
    private static final String FILE_PATTERN = "%1$s-interface-%2$s.log";
    
    /**
     * 异常文件名称
     */
    private static final String ERROR_FILE_PATTERN = "%1$s-interface-error-%2$s.log";
    
    private String logId;

    private String visitorName;

    private String visitorIp;

    private String intervieweeName;

    private String intervieweeIp;

    private String url;

    private GregorianCalendar callTime;

    private long costTime;

    private String errorInfo = "s";

    private String msgBody;

    private String msgParameter;

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
     * Creates a new instance of InterfaceLog.
     */
    public InterfaceLog() {
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
     * 返回 visitorName
     * @return visitorName
     */
    public String getVisitorName() {
        return visitorName;
    }

    /**
     * 设置  visitorName
     * @param visitorName value
     */
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    /**
     * 返回 visitorIp
     * @return visitorIp
     */
    public String getVisitorIp() {
        return visitorIp;
    }

    /**
     * 设置  visitorIp
     * @param visitorIp value
     */
    public void setVisitorIp(String visitorIp) {
        this.visitorIp = visitorIp;
    }

    /**
     * 返回 intervieweeName
     * @return intervieweeName
     */
    public String getIntervieweeName() {
        return intervieweeName;
    }

    /**
     * 设置  intervieweeName
     * @param intervieweeName value
     */
    public void setIntervieweeName(String intervieweeName) {
        this.intervieweeName = intervieweeName;
    }

    /**
     * 返回 intervieweeIp
     * @return intervieweeIp
     */
    public String getIntervieweeIp() {
        return intervieweeIp;
    }

    /**
     * 设置  intervieweeIp
     * @param intervieweeIp value
     */
    public void setIntervieweeIp(String intervieweeIp) {
        this.intervieweeIp = intervieweeIp;
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
     * 返回 msgParameter
     * @return msgParameter
     */
    public String getMsgParameter() {
        return msgParameter;
    }

    /**
     * 设置  msgParameter
     * @param msgParameter value
     */
    public void setMsgParameter(String msgParameter) {
        this.msgParameter = msgParameter;
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
        if (this.code == 0) {
            this.fileName = String.format(FILE_PATTERN, this.intervieweeName, DateUtil.getTodateString());
        } else {
            this.fileName = String.format(ERROR_FILE_PATTERN, this.intervieweeName, DateUtil.getTodateString());
        }
        return this.fileName;
    }
}