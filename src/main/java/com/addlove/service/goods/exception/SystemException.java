package com.addlove.service.goods.exception;

/**
 * 系统异常类
 *
 * @author lw
 *
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -6971716908203238516L;

    /**
     * Creates a new instance of SystemException.
     *
     * @param message
     *            系统异常描述
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of SystemException.
     *
     * @param cause
     *            异常对象
     */
    public SystemException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new instance of SystemException.
     *
     * @param message
     *            系统异常描述
     * @param cause
     *            异常对象
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

}
