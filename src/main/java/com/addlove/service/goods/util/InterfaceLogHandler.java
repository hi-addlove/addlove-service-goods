package com.addlove.service.goods.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志输出处理类
 * @author lw
 *
 */
public class InterfaceLogHandler {

    private static final Logger CALL_LOGGER = LoggerFactory.getLogger("common.interfaceLogger");

    private static final Logger EXCEPTION_LOGGER = LoggerFactory.getLogger("common.interfaceErrorLogger");

    private static final Logger CALL_INVOKE_LOGGER = LoggerFactory.getLogger("common.invoke.interfaceLogger");

    private static final Logger EXCEPTION_INVOKE_LOGGER = LoggerFactory.getLogger("common.invoke.interfaceErrorLogger");

    // 接口调用日志内容格式：被调用ip+“^”+被调用serviceName+“^”+调用方ip+“^”+调用方serviceName+“^”+“调用接口url”
    // +“^”+调用时间+“^”+接口执行时间
    // （单位ms）
    private static final String INTERFACE_CALL_LOG_TEMPLATE = "^A001^,%1$s^,%2$s^,%3$s^,%4$s^,%5$s^,%6$s^,%7$s^,%8$s^,"
            + "%9$tC%9$ty%9$tm%9$td%9$tH%9$tM%9$tS%9$tL^,%10$d^,%11$s^,%12$s^,%13$s^,%14$s^,%15$s^,%16$s^,%17$s^,%18$s^,%19$s\r\n";

    // 接口异常日志内容格式：被调用ip+“^”+被调用serviceName+“^”+调用方ip+“^”+调用方serviceName+“^”+“调用接口url”
    // +“^”+调用时间+“^”+接口执行时间
    // （单位ms）+“^”+消息体++“^”+消息参数++“^”+异常信息
    private static final String INTERFACE_EXCEPTION_LOG_TEMPLATE = "^A001^,%1$s^,%2$s^,%3$s^,%4$s^,%5$s^,%6$s^,%7$s^,"
            + "%8$s^,%9$tC%9$ty%9$tm%9$td%9$tH%9$tM%9$tS%9$tL^,%10$d^,%11$s^,%12$s^,%13$s^,%14$s^,%15$s^,%16$s^,%17$s^,%18$s^,%19$s\r\n";

    // 接口调用日志内容格式：被调用ip+“^”+被调用serviceName+“^”+调用方ip+“^”+调用方serviceName+“^”+“调用接口url”
    // +“^”+调用时间+“^”+接口执行时间
    // （单位ms）
    private static final String INTERFACE_INVOKE_LOG_TEMPLATE = "^A001^,%1$s^,%2$s^,%3$s^,%4$s^,%5$s^,%6$s^,"
            + "%7$s^,%8$s^,%9$tC%9$ty%9$tm%9$td%9$tH%9$tM%9$tS%9$tL^,%10$d^,%11$s^,%12$s^,%13$s^,%14$s^,%15$s^,%16$s^,%17$s^,%18$s^,%19$s\r\n";

    // 接口异常日志内容格式：被调用ip+“^”+被调用serviceName+“^”+调用方ip+“^”+调用方serviceName+“^”+“调用接口url”
    // +“^”+调用时间+“^”+接口执行时间
    // （单位ms）+“^”+消息体++“^”+消息参数++“^”+异常信息
    private static final String INTERFACE_INVOKE_EXCEPTION_LOG_TEMPLATE = "^A001^,%1$s^,%2$s^,%3$s^,%4$s^,%5$s^,"
            + "%6$s^,%7$s^,%8$s^,%9$tC%9$ty%9$tm%9$td%9$tH%9$tM%9$tS%9$tL^,%10$d^,%11$s^,%12$s^,%13$s^,%14$s^,%15$s^,%16$s^,%17$s^,%18$s^,%19$s\r\n";

    // 接口调用日志内容格式：外部url地址+“^”+外部serviceName+“^”+调用方ip+“^”+调用方serviceName+“^”
    // +“^”+调用时间+“^”+接口执行时间
    // （单位ms）
    private static final String EXTERNAL_INTERFACE_CALL_LOG_TEMPLATE = "^A001^,%1$s^,%2$s^,%3$s^,%4$s^,%5$s^,%6$s^,%7$s^,%8$s^,"
            + "%9$tC%9$ty%9$tm%9$td%9$tH%9$tM%9$tS%9$tL^,%10$d^,%11$s^,%12$s^,%13$s^,%14$s^,%15$s^,%16$s^,%17$s^,%18$s^,%19$s\r\n";

    // 接口异常日志内容格式：外部url地址+“^”+被调用serviceName+“^”+调用方ip+“^”+调用方serviceName+“^”
    // +“^”+调用时间+“^”+接口执行时间
    // （单位ms）+“^”+消息体++“^”+消息参数++“^”+异常信息
    private static final String EXTERNAL_INTERFACE_EXCEPTION_LOG_TEMPLATE = "^A001^,%1$s^,%2$s^,%3$s^,%4$s^,%5$s^,%6$s^,%7$s^,"
            + "%8$s^,%9$tC%9$ty%9$tm%9$td%9$tH%9$tM%9$tS%9$tL^,%10$d^,%11$s^,%12$s^,%13$s^,%14$s^,%15$s^,%16$s^,%17$s^,%18$s^,%19$s\r\n";

    /**
     * 打印接口访问信息
     *
     * @param info
     *            接口访问信息
     */
    public static void log(InterfaceLog info) {
        String string = String.format(INTERFACE_CALL_LOG_TEMPLATE, info.getTraceKey(), info.getNumber(),
                info.getPreviousNo(), info.getIntervieweeIp(), info.getIntervieweeName(), info.getVisitorIp(),
                info.getVisitorName(), info.getUrl(), info.getCallTime(), info.getCostTime(), info.getMsgBody(),
                info.getErrorInfo(), info.getResultMsg(), info.getLogId(), info.getYear(), info.getMonth(),
                info.getDay(), info.getCode(), info.getFileName());
        CALL_LOGGER.info(string);
    }

    /**
     * 打印接口异常信息
     *
     * @param info
     *            接口异常信息
     */
    public static void logException(InterfaceLog info) {
        String string = String.format(INTERFACE_EXCEPTION_LOG_TEMPLATE, info.getTraceKey(), info.getNumber(),
                info.getPreviousNo(), info.getIntervieweeIp(), info.getIntervieweeName(), info.getVisitorIp(),
                info.getVisitorName(), info.getUrl(), info.getCallTime(), info.getCostTime(), info.getMsgBody(),
                info.getErrorInfo(), info.getResultMsg(), info.getLogId(), info.getYear(), info.getMonth(),
                info.getDay(), info.getCode(), info.getFileName());
        EXCEPTION_LOGGER.error(string);
    }

    /**
     * 打印接口访问信息
     *
     * @param info
     *            接口访问信息
     */
    protected static void logInvoke(InterfaceInvokeLog info) {
        String string = String.format(INTERFACE_INVOKE_LOG_TEMPLATE, info.getTraceKey(), info.getNumber(),
                info.getPreviousNo(), info.getTargetService(), info.getOriginalIp(), info.getOriginalService(),
                info.getTargetIp(), info.getUrl(), info.getCallTime(), info.getCostTime(), info.getMsgBody(),
                info.getErrorInfo(), info.getResultMsg(), info.getLogId(), info.getYear(), info.getMonth(),
                info.getDay(), info.getCode(), info.getFileName());
        CALL_INVOKE_LOGGER.info(string);
    }

    /**
     * 打印接口异常信息
     *
     * @param info
     *            接口异常信息
     */
    protected static void logInvokeException(InterfaceInvokeLog info) {
        String string = String.format(INTERFACE_INVOKE_EXCEPTION_LOG_TEMPLATE, info.getTraceKey(), info.getNumber(),
                info.getPreviousNo(), info.getTargetService(), info.getOriginalIp(), info.getOriginalService(),
                info.getTargetIp(), info.getUrl(), info.getCallTime(), info.getCostTime(), info.getMsgBody(),
                info.getErrorInfo(), info.getResultMsg(), info.getLogId(), info.getYear(), info.getMonth(),
                info.getDay(), info.getCode(), info.getFileName());
        EXCEPTION_INVOKE_LOGGER.error(string);
    }

    /**
     * 打印接口访问信息
     *
     * @param loggers 日志对象列表
     * @param info
     *            接口访问信息
     */
    protected static void logOutInvoke(Logger[] loggers, InterfaceInvokeLog info) {
        if (null == loggers || loggers.length <= 0) {
            return;
        }

        String log = String.format(EXTERNAL_INTERFACE_CALL_LOG_TEMPLATE, info.getTraceKey(), info.getNumber(),
                info.getPreviousNo(), info.getTargetService(), info.getOriginalIp(), info.getOriginalService(),
                info.getTargetIp(), info.getUrl(), info.getCallTime(), info.getCostTime(), info.getMsgBody(),
                info.getErrorInfo(), info.getResultMsg(), info.getLogId(), info.getYear(), info.getMonth(),
                info.getDay(), info.getCode(), info.getFileName());
        for (Logger logger : loggers) {
            logger.info(log);
        }
    }

    /**
     * 打印接口异常信息
     * @param loggers 日志对象列表
     * @param info
     *            接口异常信息
     */
    protected static void logOutInvokeException(Logger[] loggers, InterfaceInvokeLog info) {
        if (null == loggers || loggers.length <= 0) {
            return;
        }

        String log = String.format(EXTERNAL_INTERFACE_EXCEPTION_LOG_TEMPLATE, info.getTraceKey(), info.getNumber(),
                info.getPreviousNo(), info.getTargetService(), info.getOriginalIp(), info.getOriginalService(),
                info.getTargetIp(), info.getUrl(), info.getCallTime(), info.getCostTime(), info.getMsgBody(),
                info.getErrorInfo(), info.getResultMsg(), info.getLogId(), info.getYear(), info.getMonth(),
                info.getDay(), info.getCode(), info.getFileName());
        for (Logger logger : loggers) {
            logger.info(log);
        }
    }
}
