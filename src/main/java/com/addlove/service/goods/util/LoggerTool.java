package com.addlove.service.goods.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 描述: 记录业务日志工具类.
 * @author lw
 *
 */
public class LoggerTool {

    /**
     * 获取日志对象.
     *
     * @param loggerName
     *            日志名称
     * @return 日志对象
     */
    public static Logger getLogger(String loggerName) {
        return LoggerFactory.getLogger(loggerName);
    }

    /**
     * 记录日志.
     *
     * @param loggerName
     *            日志名称.
     * @param msg
     *            消息.
     */
    public static void log(String loggerName, String msg) {
        if (StringUtils.isEmpty(loggerName)) {
            return;
        }

        Logger logger = getLogger(loggerName);
        if (null != logger) {
            logger.debug(msg);
        }
    }

}
