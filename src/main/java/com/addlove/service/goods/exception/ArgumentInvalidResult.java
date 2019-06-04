package com.addlove.service.goods.exception;

/**
 * @author lw
 */
public class ArgumentInvalidResult {

    private String field;

    private Object rejectedValue;

    private String defaultMessage;

    /**
     * 返回 field
     * @return field
     */
    public String getField() {
        return field;
    }

    /**
     * 设置  field
     * @param field value
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * 返回 rejectedValue
     * @return rejectedValue
     */
    public Object getRejectedValue() {
        return rejectedValue;
    }

    /**
     * 设置  rejectedValue
     * @param rejectedValue value
     */
    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    /**
     * 返回 defaultMessage
     * @return defaultMessage
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }

    /**
     * 设置  defaultMessage
     * @param defaultMessage value
     */
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
