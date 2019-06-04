package com.addlove.service.goods.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.addlove.service.goods.message.CommonResponseCode;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.util.LogUtil;

/**
 * @author lw
 */
@RestControllerAdvice
public class SysExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysExceptionHandler.class);

    /**
     * 运行异常统一处理返回
     *
     * @param ex
     *            异常对象
     * @param 泛型
     * @return result对象
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    private ResponseMessage runtimeExceptionHandler(Exception ex) {
        String traceKey = LogUtil.getLogTraceKey();
        String exceptionTrace = this.getTrace(ex);
        ResponseMessage responseMessage = null;
        if (ServiceException.class.isAssignableFrom(ex.getClass())) {
            // 业务异常返回给页面提示
            ServiceException se = (ServiceException) ex;
            if (se.getCode() > 0) {
                responseMessage = new ResponseMessage(se.getMessage(), se.getCode());
            } else {
                responseMessage = new ResponseMessage(CommonResponseCode.SERVICE_ERROR.getMsg(),
                        CommonResponseCode.SERVICE_ERROR.getCode());
            }
            LOGGER.error("Service Exception,traceKey:{},response:{},exception:{}", traceKey, responseMessage.toJSONString(), exceptionTrace);
            LogUtil.logInterfaceExceptionMsg(null == responseMessage ? null : responseMessage.toJSONString(), responseMessage.getCode());
        } else if (SystemException.class.isAssignableFrom(ex.getClass())) {
            // 系统异常
            responseMessage = new ResponseMessage(CommonResponseCode.SYSTEM_ERROR.getMsg(),
                    CommonResponseCode.SERVICE_ERROR.getCode(), null);
            LogUtil.logInterfaceExceptionMsg(null == responseMessage ? null : responseMessage.toJSONString(), responseMessage.getCode());
            LOGGER.error("System Exception,traceKey:{},response:{},exception:{}", traceKey, responseMessage.toJSONString(), exceptionTrace);
        } else if (MethodArgumentNotValidException.class.isAssignableFrom(ex.getClass())) {
            responseMessage = getIllegalParamsExceptionHandler(
                    ((MethodArgumentNotValidException) ex).getBindingResult());
            LogUtil.logInterfaceExceptionMsg(null == responseMessage ? null : responseMessage.toJSONString(), responseMessage.getCode());
            LOGGER.error("argumnet not valid Exception,traceKey:{},response:{},exception:{}", traceKey, responseMessage.toJSONString(),
                    exceptionTrace);
        } else {
            // 系统错误
            responseMessage = new ResponseMessage(CommonResponseCode.UNKNOWN_ERROR.getMsg(),
                    CommonResponseCode.UNKNOWN_ERROR.getCode(), null);
            LogUtil.logInterfaceExceptionMsg(null == responseMessage ? null : responseMessage.toJSONString(), responseMessage.getCode());
            LOGGER.error("Unknown Exception,traceKey:{},response:{},exception:{}", traceKey, responseMessage.toJSONString(), exceptionTrace);
        }
        
        ex.printStackTrace();
        return responseMessage;
    }

    /**
     * 参数校验异常统一处理返回内容
     *
     * @param ex
     *            异常对象
     * @param 泛型
     * @return result
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    private ResponseMessage illegalParamsExceptionHandler(BindException ex) {
        return getIllegalParamsExceptionHandler(ex.getBindingResult());
    }

    /**
     * 参数校验异常统一处理返回内容
     *
     * @param br
     *            异常对象
     * @param 泛型
     * @return result
     */
    private ResponseMessage getIllegalParamsExceptionHandler(BindingResult br) {
        ResponseMessage result = new ResponseMessage(CommonResponseCode.SERVICE_INTERFACE_PARAM_ERROR.getMsg(),
                CommonResponseCode.SERVICE_INTERFACE_PARAM_ERROR.getCode(), null);
        // 按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<ArgumentInvalidResult>();
        // 解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : br.getFieldErrors()) {
            ArgumentInvalidResult air = new ArgumentInvalidResult();
            air.setDefaultMessage(error.getDefaultMessage());
            air.setField(error.getField());
            air.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(air);
        }
        result.setData(invalidArguments);
        LOGGER.error("interface invalid request:{}", result.toJSONString());
        return result;
    }

    /**
     * 获取异常堆栈信息
     *
     * @param t
     *            异常对象
     * @return 异常堆栈信息
     */
    public final String getTrace(Throwable t) {
        if (null == t) {
            return "";
        }

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
