package com.addlove.service.goods.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 异常工具类
 * @author lw
 *
 */
public class StackTraceUtil {

    /**
     * 将异常堆栈转换为字符串
     *
     * @param throwable
     *            异常
     * @return String
     */
    public static String getStackTrace(Throwable throwable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        throwable.printStackTrace(printWriter);
        return result.toString();
    }

}
