package com.addlove.service.goods.util;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * @author lw
 */
public final class LoggerEnhance {

    /**
     * 获取当前线程的日志id
     *
     * @return 日志id
     */
    public static String getTraceId() {
        String logId = LogUtil.getLogId();
        String logTime = DateUtil.getCurrentTime(DateUtil.FORMAT_YYYYMMDDHHMMSS);
        String logYearTime = DateUtil.getCurrentTime(DateUtil.FORMAT_YEAR_YYYY);
        String logMonthTime = DateUtil.getCurrentTime(DateUtil.FORMAT_YYYYMM);
        String logDayTime = DateUtil.getCurrentTime(DateUtil.FORMAT_YYYYMMDD);
        String traceKey = LogUtil.getLogTraceKey();
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(logYearTime);
        strBuilder.append("-^,");
        strBuilder.append(logMonthTime);
        strBuilder.append("-^,");
        strBuilder.append(logDayTime);
        strBuilder.append("-^,");
        strBuilder.append(logTime);
        strBuilder.append("-^,");
        strBuilder.append(logId);
        strBuilder.append("-^,");
        strBuilder.append(traceKey);
        strBuilder.append("-^,");
        strBuilder.append(LogUtil.getServiceName());
        strBuilder.append("-^,");
        return strBuilder.toString();
    }

    /**
     * 获取当前线程的日志id
     * @param code 日志码
     * @return 日志id
     */
    public static String getTraceId(int code) {
        String logId = LogUtil.getLogId();
        String logTime = DateUtil.getCurrentTime(DateUtil.FORMAT_YYYYMMDDHHMMSS);
        String logYearTime = DateUtil.getCurrentTime(DateUtil.FORMAT_YEAR_YYYY);
        String logMonthTime = DateUtil.getCurrentTime(DateUtil.FORMAT_YYYYMM);
        String logDayTime = DateUtil.getCurrentTime(DateUtil.FORMAT_YYYYMMDD);
        String traceKey = LogUtil.getLogTraceKey();
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(logYearTime);
        strBuilder.append("-^,");
        strBuilder.append(logMonthTime);
        strBuilder.append("-^,");
        strBuilder.append(logDayTime);
        strBuilder.append("-^,");
        strBuilder.append(logTime);
        strBuilder.append("-^,");
        strBuilder.append(logId);
        strBuilder.append("-^,");
        strBuilder.append(traceKey);
        strBuilder.append("-^,");
        strBuilder.append(code);
        strBuilder.append("-^,");
        strBuilder.append(LogUtil.getServiceName());
        strBuilder.append("-^,");
        return strBuilder.toString();
    }

    /**
     * getName
     *
     * @param logger logger logger
     * @return Name
     */
    public static String getName(Logger logger) {
        return logger.getName();
    }

    /**
     * isTraceEnabled
     *
     * @param logger logger
     * @return isTrace
     */
    public static boolean isTraceEnabled(Logger logger) {
        return logger.isTraceEnabled();
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramString String
     */
    public static void trace(Logger logger, String paramString) {
        logger.trace(getTraceId() + paramString);
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject obj
     */
    public static void trace(Logger logger, String paramString, Object paramObject) {
        logger.trace(getTraceId() + paramString, paramObject);
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void trace(Logger logger, String paramString, Object paramObject1, Object paramObject2) {
        logger.trace(getTraceId() + paramString, paramObject1, paramObject2);
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void trace(Logger logger, String paramString, Object... paramArrayOfObject) {
        logger.trace(getTraceId() + paramString, paramArrayOfObject);
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void trace(Logger logger, String paramString, Throwable paramThrowable) {
        logger.trace(getTraceId() + paramString, paramThrowable);
    }

    /**
     * isTraceEnabled
     *
     * @param logger logger
     * @param paramMarker marker
     * @return isTrace
     */
    public static boolean isTraceEnabled(Logger logger, Marker paramMarker) {
        return logger.isTraceEnabled(paramMarker);
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     */
    public static void trace(Logger logger, Marker paramMarker, String paramString) {
        logger.trace(paramMarker, getTraceId() + paramString);
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject obj
     */
    public static void trace(Logger logger, Marker paramMarker, String paramString, Object paramObject) {
        logger.trace(paramMarker, getTraceId() + paramString, paramObject);
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void trace(Logger logger, Marker paramMarker, String paramString, Object paramObject1,
            Object paramObject2) {
        logger.trace(paramMarker, getTraceId() + paramString, paramObject1, paramObject2);
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void trace(Logger logger, Marker paramMarker, String paramString, Object... paramArrayOfObject) {
        logger.trace(paramMarker, getTraceId() + paramString, paramArrayOfObject);
    }

    /**
     * trace
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void trace(Logger logger, Marker paramMarker, String paramString, Throwable paramThrowable) {
        logger.trace(paramMarker, getTraceId() + paramString, paramThrowable);
    }

    /**
     * isDebugEnabled
     *
     * @param logger logger
     * @return isDebug
     */
    public static boolean isDebugEnabled(Logger logger) {
        return logger.isDebugEnabled();
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramString String
     */
    public static void debug(Logger logger, String paramString) {
        logger.debug(getTraceId() + paramString);
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject obj
     */
    public static void debug(Logger logger, String paramString, Object paramObject) {
        logger.debug(getTraceId() + paramString, paramObject);
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void debug(Logger logger, String paramString, Object paramObject1, Object paramObject2) {
        logger.debug(getTraceId() + paramString, paramObject1, paramObject2);
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void debug(Logger logger, String paramString, Object... paramArrayOfObject) {
        logger.debug(getTraceId() + paramString, paramArrayOfObject);
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void debug(Logger logger, String paramString, Throwable paramThrowable) {
        logger.debug(getTraceId() + paramString, paramThrowable);
    }

    /**
     * isDebugEnabled
     *
     * @param logger logger
     * @param paramMarker marker
     * @return isDebug
     */
    public static boolean isDebugEnabled(Logger logger, Marker paramMarker) {
        return logger.isDebugEnabled(paramMarker);
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     */
    public static void debug(Logger logger, Marker paramMarker, String paramString) {
        logger.debug(paramMarker, getTraceId() + paramString);
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject obj
     */
    public static void debug(Logger logger, Marker paramMarker, String paramString, Object paramObject) {
        logger.debug(paramMarker, getTraceId() + paramString, paramObject);
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void debug(Logger logger, Marker paramMarker, String paramString, Object paramObject1,
            Object paramObject2) {
        logger.debug(paramMarker, getTraceId() + paramString, paramObject1, paramObject2);
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void debug(Logger logger, Marker paramMarker, String paramString, Object... paramArrayOfObject) {
        logger.debug(paramMarker, getTraceId() + paramString, paramString, paramArrayOfObject);
    }

    /**
     * debug
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void debug(Logger logger, Marker paramMarker, String paramString, Throwable paramThrowable) {
        logger.debug(paramMarker, getTraceId() + paramString, paramThrowable);
    }

    /**
     * isInfoEnabled
     *
     * @param logger logger
     * @return isEnable
     */
    public static boolean isInfoEnabled(Logger logger) {
        return logger.isInfoEnabled();
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramString String
     */
    public static void info(Logger logger, String paramString) {
        logger.info(getTraceId() + paramString);
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject obj
     */
    public static void info(Logger logger, String paramString, Object paramObject) {
        logger.info(getTraceId() + paramString, paramObject);
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void info(Logger logger, String paramString, Object paramObject1, Object paramObject2) {
        logger.info(getTraceId() + paramString, paramObject1, paramObject2);
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void info(Logger logger, String paramString, Object... paramArrayOfObject) {
        logger.info(getTraceId() + paramString, paramArrayOfObject);
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void info(Logger logger, String paramString, Throwable paramThrowable) {
        logger.info(getTraceId() + paramString, paramThrowable);
    }

    /**
     * isInfoEnabled
     *
     * @param logger logger
     * @param paramMarker marker
     * @return true
     */
    public static boolean isInfoEnabled(Logger logger, Marker paramMarker) {
        return logger.isInfoEnabled(paramMarker);
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     */
    public static void info(Logger logger, Marker paramMarker, String paramString) {
        logger.info(paramMarker, getTraceId() + paramString);
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject obj
     */
    public static void info(Logger logger, Marker paramMarker, String paramString, Object paramObject) {
        logger.info(paramMarker, getTraceId() + paramString, paramObject);
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void info(Logger logger, Marker paramMarker, String paramString, Object paramObject1,
            Object paramObject2) {
        logger.info(paramMarker, getTraceId() + paramString, paramObject1, paramObject2);
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void info(Logger logger, Marker paramMarker, String paramString, Object... paramArrayOfObject) {
        logger.info(paramMarker, getTraceId() + paramString, paramArrayOfObject);
    }

    /**
     * info
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void info(Logger logger, Marker paramMarker, String paramString, Throwable paramThrowable) {
        logger.info(paramMarker, getTraceId() + paramString, paramThrowable);
    }

    /**
     * isWarnEnabled
     *
     * @param logger logger
     * @return logger
     */
    public static boolean isWarnEnabled(Logger logger) {
        return logger.isWarnEnabled();
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramString String
     */
    public static void warn(Logger logger, String paramString) {
        logger.warn(getTraceId() + paramString);
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject obj
     */
    public static void warn(Logger logger, String paramString, Object paramObject) {
        logger.warn(getTraceId() + paramString, paramObject);
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void warn(Logger logger, String paramString, Object... paramArrayOfObject) {
        logger.warn(getTraceId() + paramString, paramArrayOfObject);
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void warn(Logger logger, String paramString, Object paramObject1, Object paramObject2) {
        logger.warn(getTraceId() + paramString, paramObject1, paramObject2);
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void warn(Logger logger, String paramString, Throwable paramThrowable) {
        logger.warn(getTraceId() + paramString, paramThrowable);
    }

    /**
     * isWarnEnabled
     *
     * @param logger logger
     * @param paramMarker marker
     * @return true
     */
    public static boolean isWarnEnabled(Logger logger, Marker paramMarker) {
        return logger.isWarnEnabled(paramMarker);
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     */
    public static void warn(Logger logger, Marker paramMarker, String paramString) {
        logger.warn(paramMarker, getTraceId() + paramString);
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject obj
     */
    public static void warn(Logger logger, Marker paramMarker, String paramString, Object paramObject) {
        logger.warn(paramMarker, getTraceId() + paramString, paramObject);
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void warn(Logger logger, Marker paramMarker, String paramString, Object paramObject1,
            Object paramObject2) {
        logger.warn(paramMarker, getTraceId() + paramString, paramObject1, paramObject2);
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void warn(Logger logger, Marker paramMarker, String paramString, Object... paramArrayOfObject) {
        logger.warn(paramMarker, getTraceId() + paramString, paramArrayOfObject);
    }

    /**
     * warn
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void warn(Logger logger, Marker paramMarker, String paramString, Throwable paramThrowable) {
        logger.warn(paramMarker, getTraceId() + paramString, paramThrowable);
    }

    /**
     * isErrorEnabled
     *
     * @param logger logger
     * @return true
     */
    public static boolean isErrorEnabled(Logger logger) {
        return logger.isErrorEnabled();
    }

    /**
     * error
     *
     * @param logger logger
     * @param paramString String
     */
//    public static void error(Logger logger, String paramString) {
//        logger.error(getTraceId() + paramString);
//    }

    /**
     * error
     *
     * @param logger logger
     * @param code code
     * @param paramString String
     */
    public static void error(Logger logger, int code, String paramString) {
        logger.error(getTraceId(code) + paramString);
    }

    /**
     * error
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject obj
     */
//    public static void error(Logger logger, String paramString, Object paramObject) {
//        logger.error(getTraceId() + paramString, paramObject);
//    }

    /**
     * error
     *
     * @param logger logger
     * @param code code
     * @param paramString String
     * @param paramObject obj
     */
    public static void error(Logger logger, int code, String paramString, Object paramObject) {
        logger.error(getTraceId(code) + paramString, paramObject);
    }

    /**
     * error
     *
     * @param logger logger
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
//    public static void error(Logger logger, String paramString, Object paramObject1, Object paramObject2) {
//        logger.error(getTraceId() + paramString, paramObject1, paramObject2);
//    }

    /**
     * error
     *
     * @param logger logger
     * @param code code
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void error(Logger logger, int code, String paramString, Object paramObject1, Object paramObject2) {
        logger.error(getTraceId(code) + paramString, paramObject1, paramObject2);
    }

    /**
     * error
     *
     * @param logger logger
     * @param paramString String
     * @param paramArrayOfObject array
     */
//    public static void error(Logger logger, String paramString, Object... paramArrayOfObject) {
//        logger.error(getTraceId() + paramString, paramArrayOfObject);
//    }

    /**
     * error
     *
     * @param logger logger
     * @param code code
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void error(Logger logger, int code, String paramString, Object... paramArrayOfObject) {
        logger.error(getTraceId(code) + paramString, paramArrayOfObject);
    }

    /**
     * error
     *
     * @param logger logger
     * @param paramString String
     * @param paramThrowable throwable
     */
//    public static void error(Logger logger, String paramString, Throwable paramThrowable) {
//        logger.error(getTraceId() + paramString, paramThrowable);
//    }


    /**
     * error
     *
     * @param logger logger
     * @param code code
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void error(Logger logger, int code, String paramString, Throwable paramThrowable) {
        logger.error(getTraceId(code) + paramString, paramThrowable);
    }

    /**
     * isErrorEnabled
     *
     * @param logger logger
     * @param paramMarker marker
     * @return isError
     */
    public static boolean isErrorEnabled(Logger logger, Marker paramMarker) {
        return logger.isErrorEnabled(paramMarker);
    }

    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     */
//    public static void error(Logger logger, Marker paramMarker, String paramString) {
//        logger.error(paramMarker, getTraceId() + paramString);
//    }

    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param code code
     * @param paramString String
     */
    public static void error(Logger logger, Marker paramMarker, int code, String paramString) {
        logger.error(paramMarker, getTraceId(code) + paramString);
    }


    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject obj
     */
//    public static void error(Logger logger, Marker paramMarker, String paramString, Object paramObject) {
//        logger.error(paramMarker, getTraceId() + paramString, paramObject);
//    }

    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param code code
     * @param paramString String
     * @param paramObject obj
     */
    public static void error(Logger logger, Marker paramMarker, int code, String paramString, Object paramObject) {
        logger.error(paramMarker, getTraceId(code) + paramString, paramObject);
    }

    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
//    public static void error(Logger logger, Marker paramMarker, String paramString, Object paramObject1,
//            Object paramObject2) {
//        logger.error(paramMarker, getTraceId() + paramString, paramObject1, paramObject2);
//    }

    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param code code
     * @param paramObject1 obj1
     * @param paramObject2 obj2
     */
    public static void error(Logger logger, Marker paramMarker, int code, String paramString, Object paramObject1,
            Object paramObject2) {
        logger.error(paramMarker, getTraceId(code) + paramString, paramObject1, paramObject2);
    }

    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramArrayOfObject array
     */
//    public static void error(Logger logger, Marker paramMarker, String paramString, Object... paramArrayOfObject) {
//        logger.error(paramMarker, getTraceId() + paramString, paramArrayOfObject);
//    }

    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param code code
     * @param paramString String
     * @param paramArrayOfObject array
     */
    public static void error(Logger logger, Marker paramMarker, int code, String paramString, Object... paramArrayOfObject) {
        logger.error(paramMarker, getTraceId(code) + paramString, paramArrayOfObject);
    }

    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param paramString String
     * @param paramThrowable throwable
     */
//    public static void error(Logger logger, Marker paramMarker, String paramString, Throwable paramThrowable) {
//        logger.error(paramMarker, getTraceId() + paramString, paramThrowable);
//    }

    /**
     * error
     *
     * @param logger logger
     * @param paramMarker marker
     * @param code code
     * @param paramString String
     * @param paramThrowable throwable
     */
    public static void error(Logger logger, Marker paramMarker, int code, String paramString, Throwable paramThrowable) {
        logger.error(paramMarker, getTraceId(code) + paramString, paramThrowable);
    }
}
