package com.addlove.service.goods.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 统一的服务调用返回消息
 * @author lw
 *
 */
public class ResponseMessage {

    /**
     * 成功码
     */
    private int code = 0;

    /**
     * 默认成功的描述
     */
    private String msg = "处理成功";

    /**
     * 响应数据
     */
    private Object data = "";

    /**
     * 是否将日期转换为
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private boolean useDateFormat = false;

    /**
     * 日期格式
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 创建成功响应对象
     * @param data 响应数据
     * @return 响应对象
     */
    public static ResponseMessage ok(Object data) {
        return new ResponseMessage(data);
    }

    /**
     * 创建成功响应对象
     *
     * @return 响应对象
     */
    public static ResponseMessage ok() {
        return new ResponseMessage();
    }

    /**
     * 创建失败响应对象
     *
     * @param code 响应code
     * @return 响应对象
     */
    public static ResponseMessage fail(int code) {
        return new ResponseMessage("", code);
    }

    /**
     * 创建失败响应对象
     *
     * @param msg 失败的消息
     * @param code 响应code
     * @return 响应对象
     */
    public static ResponseMessage fail(String msg, int code) {
        return new ResponseMessage(msg, code);
    }

    /**
     * 创建失败响应对象
     *
     * @param msg 失败的消息
     * @return 响应对象
     */
    public static ResponseMessage failByParam(String msg) {
        return new ResponseMessage(msg, CommonResponseCode.PARAM_TYPE_ERROR.getCode());
    }

    /**
     * Creates a new instance of ResponseMessage.
     *
     * @param data data
     */
    public ResponseMessage(Object data) {
        super();
        this.data = data;
    }

    /**
     * Creates a new instance of ResponseMessage.
     *
     * @param msg msg
     * @param code code
     */
    public ResponseMessage(String msg, int code) {
        super();
        this.msg = msg;
        this.code = code;
    }

    /**
     * Creates a new instance of ResponseMessage.
     *
     * @param msg 消息
     * @param code code
     * @param data 数据
     */
    public ResponseMessage(String msg, int code, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = null == data ? "" : data;
    }

    /**
     * Creates a new instance of ResponseMessage.
     *
     */
    public ResponseMessage() {
        super();
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
     * 返回 msg
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置  msg
     * @param msg value
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 返回 data
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置  data
     * @param data value
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * isUseDateFormat
     *
     * @return true 格式化
     */
    @JsonIgnore
    public boolean isUseDateFormat() {
        return useDateFormat;
    }

    /**
     * 设置  useDateFormat
     * @param useDateFormat value
     */
    public void setUseDateFormat(boolean useDateFormat) {
        this.useDateFormat = useDateFormat;
    }

    /**
     * 返回 dateFormat
     * @return dateFormat
     */
    @JsonIgnore
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * 设置  dateFormat
     * @param dateFormat value
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * toString
     *
     * @return 对象数据
     */
    @Override
    public String toString() {
        return "res{" + "code=" + this.code + ", msg='" + msg + ", data=" + data + '}';
    }

    /**
     * 转为json
     *
     * @return json
     */
    public String toJSONString() {
        if (this.isUseDateFormat()) {
            return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
        } else {
            return JSON.toJSONString(this);
        }
    }
}
