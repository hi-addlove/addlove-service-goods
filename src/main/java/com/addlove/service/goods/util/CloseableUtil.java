package com.addlove.service.goods.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭工具类
 * @author lw
 */
public class CloseableUtil {
    /**
     * 关闭
     * @param closeable 关闭器
     */
    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
