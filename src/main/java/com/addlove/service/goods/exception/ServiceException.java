package com.addlove.service.goods.exception;

import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;

/**
 * 业务异常类
 * @author lw
 *
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -6971716908203238516L;

    /**
     * 日志对象.
     */
    private Logger logger;

    /**
     * 错误编码.
     */
    private int code = -1;

    /**
     * 错误消息的补充内容.
     */
    private String messageSupplement = "";

    /**
     * 构造函数 Creates a new instance of ServiceException.
     *
     * @param message
     *            异常描述
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param message
     *            异常描述
     * @param logger
     *            当前类的日志对象
     */
    public ServiceException(String message, Logger logger) {
        super(message);
        this.logger = logger;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param cause
     *            Throwable异常
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param cause
     *            Throwable异常
     * @param logger
     *            当前类的日志对象
     */
    public ServiceException(Throwable cause, Logger logger) {
        super(cause);
        this.logger = logger;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param code
     *            异常码
     * @param message
     *            异常描述
     */
    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param logger
     *            当前类的日志对象
     */
    public ServiceException(int code, String message, Logger logger) {
        super(message);
        this.code = code;
        this.logger = logger;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param message
     *            异常描述
     * @param cause
     *            Throwable异常
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param message
     *            异常描述
     * @param cause
     *            Throwable异常
     * @param logger
     *            当前类的日志对象
     */
    public ServiceException(String message, Throwable cause, Logger logger) {
        super(message, cause);
        this.logger = logger;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param cause
     *            Throwable异常
     */
    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param cause
     *            Throwable异常
     * @param logger
     *            当前类的日志对象
     */
    public ServiceException(int code, String message, Throwable cause, Logger logger) {
        super(message, cause);
        this.code = code;
        this.logger = logger;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param messageSupplement
     *            异常描述
     */
    public ServiceException(int code, String message, String messageSupplement) {
        super(messageSupplement + message);
        this.code = code;
        this.messageSupplement = messageSupplement;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param messageSupplement
     *            异常描述
     * @param logger
     *            当前类的日志对象
     */
    public ServiceException(int code, String message, String messageSupplement, Logger logger) {
        super(messageSupplement + message);
        this.code = code;
        this.messageSupplement = messageSupplement;
        this.logger = logger;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param cause
     *            Throwable异常
     * @param messageSupplement
     *            异常描述
     */
    public ServiceException(int code, String message, Throwable cause, String messageSupplement) {
        super(messageSupplement + message, cause);
        this.code = code;
        this.messageSupplement = messageSupplement;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param cause
     *            Throwable异常
     * @param messageSupplement
     *            异常描述
     * @param logger
     *            当前类的日志对象
     */
    public ServiceException(int code, String message, Throwable cause, String messageSupplement, Logger logger) {
        super(messageSupplement + message, cause);
        this.code = code;
        this.messageSupplement = messageSupplement;
        this.logger = logger;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param value
     *            其他值
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param messageSupplement
     *            异常描述
     * @param logger
     *            当前类的日志对象
     */
    public ServiceException(Object value, int code, String message, String messageSupplement, Logger logger) {
        super(messageSupplement + " :" + toJSONString(value) + message);
        this.code = code;
        this.messageSupplement = messageSupplement;
        this.logger = logger;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param value
     *            其他值
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param cause
     *            Throwable异常
     * @param messageSupplement
     *            异常描述
     */
    public ServiceException(Object value, int code, String message, Throwable cause, String messageSupplement) {
        super(messageSupplement + ":" + toJSONString(value) + message, cause);
        this.code = code;
        this.messageSupplement = messageSupplement;
    }

    /**
     * Creates a new instance of ServiceException.
     *
     * @param value
     *            其他值
     * @param code
     *            异常码
     * @param message
     *            异常描述
     * @param cause
     *            Throwable异常
     * @param messageSupplement
     *            异常描述
     * @param logger
     *            当前类的日志对象
     */
    public ServiceException(Object value, int code, String message, Throwable cause, String messageSupplement,
            Logger logger) {
        super(messageSupplement + ":" + toJSONString(value) + message, cause);
        this.code = code;
        this.messageSupplement = messageSupplement;
        this.logger = logger;
    }

    /**
     * 对象转为json字符串
     *
     * @param obj
     *            对象
     * @return json字符串
     */
    private static String toJSONString(Object obj) {
        return null == obj ? "" : JSON.toJSONString(obj);
    }

    /**
     * 返回 logger
     * @return logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * 设置  logger
     * @param logger value
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
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
     * 返回 messageSupplement
     * @return messageSupplement
     */
    public String getMessageSupplement() {
        return messageSupplement;
    }

    /**
     * 设置  messageSupplement
     * @param messageSupplement value
     */
    public void setMessageSupplement(String messageSupplement) {
        this.messageSupplement = messageSupplement;
    }
}
