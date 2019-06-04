package com.addlove.service.goods.message;

/**
 * 所有服务公共的相应错误代码
 * @author lw
 *
 */
public enum CommonResponseCode {

    /** 正确 **/
    SUCCESS(0, "成功"),


    /**
     * 系统相关[9900, 9999]
     */
    /** 未知错误 **/
    UNKNOWN_ERROR(9900, "未知错误"),

    /** 系统异常 **/
    SYSTEM_ERROR(9901, "系统异常"),

    /** 服务异常 **/
    SERVICE_ERROR(9902, "服务异常"),

    /** 接口参数异常 **/
    SERVICE_INTERFACE_PARAM_ERROR(9903, "接口参数异常"),

    /** 参数错误 **/
    PARAM_TYPE_ERROR(9904, "参数错误"),

    /** 参数不能为空 **/
    PARAM_NULL_ERROR(9905, "参数不能为空"),

    /** 参数值传入错误 **/
    PARAM_VALUE_ERROR(9906, "参数值传入错误"),

    /** 限制调用 **/
    LIMIT_ERROR_CODE(9907, "限制调用"),

    /** 禁止访问 **/
    NO_AUTH_CODE(9908, "禁止访问 "),

    /** 资源没找到 **/
    NOT_FOUND_CODE(9909, "资源没找到"),

    /** 消息头错误 **/
    HEADER_ERROR_CODE(9910, "消息头错误"),

    /** 消息内容被篡改 **/
    MSG_BODY_TAMPERED_CODE(9911, "消息内容被篡改 "),

    /** 请求消息中没有业务所需的用户信息 **/
    MSG_HEADER_NOT_USERMODEL_CODE(9912, "请求消息中没有业务所需的用户信息"),

    /**
     * 公共调用异常码
     */
    SYS_GLOBAL_ERROR(9000, "访问服务异常"),

    /** 接口异常，请检查接口是否存在 **/
    SERVICE_INTERFACE_MAYBE_NOT_EXIST(9001, "接口异常，请检查接口是否存在"),

    /** 访问服务缓存异常 **/
    SYS_REDIS_ERROR(9002, "访问服务缓存异常"),

    /** 调用id生成器服务异常 **/
    MSG_ID_GENERATOR_SERVICE_CODE(9003, "调用id生成器服务异常"),

    /** 不支持的编码异常 **/
    UNSUPPORTED_ENCODING_EXCEPTION(9004, "不支持的编码异常"),

    /** 将返回的json转为ResponseMessage异常 **/
    DECODE_RESPONSE_MSG_EXCEPTION(9005, "将返回的json转为ResponseMessage异常"),
    /**
     * sql异常
     */
    SQL_ERROR(9006, "sql有误,无法解析"),

    /**
     * 账号已存在
     */
    EXIST_ACCOUNT(9007, "账号已存在"),

    /**
     * 已有用户在修改接口，请稍候重试
     */
    SOMEONE_IS_MODIFYING(9008, "已有用户在修改接口，请稍候重试");

    private int code;
    private String msg;

    private CommonResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回 code
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置 code
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
