package com.addlove.service.goods.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 * @author lw
 *
 */
public class ValidUtil {

    /**
     * 汉字校验
     *
     * @param strPara value
     * @return true:都为汉字
     */
    public static boolean isChinese(String strPara) {
        boolean res = false;
        final Pattern pStr = Pattern.compile("[\\u4e00-\\u9fa5]+");
        final Matcher matcher = pStr.matcher(strPara);
        if (matcher.find() && matcher.group(0).equals(strPara)) {
            res = true;
        }
        return res;
    }

    /**
     * 数字校验
     *
     * @param str value
     * @return true: 都为数字
     */
    public static boolean isDigital(String str) {
        return str.matches("^[0-9]*$");
    }

    /**
     * 一个字符串中是否包含连续的4位数字
     *
     * @param str value
     * @return true：包含
     */
    public static boolean hasAtleastFourDigital(String str) {
        return str.matches("^.*\\d{4}.*$");
    }

}
