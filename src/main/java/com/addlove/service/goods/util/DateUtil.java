package com.addlove.service.goods.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 日期时间工具类
 * @author lw
 */
public class DateUtil {

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * yyyyMMddHHmmss
     */
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * yyyy-MM-dd
     */
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * yyyyMMdd
     */
    public static final String FORMAT_YYYYMM = "yyyyMM";

    /**
     * yyyyMMdd
     */
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * yyyy
     */
    public static final String FORMAT_YEAR_YYYY = "yyyy";

    /**
     * MM
     */
    private static final String FORMAT_MONTH_MM = "MM";

    /**
     * dd
     */
    private static final String FORMAT_DAY_DD = "dd";

    /**
     * 24小时对应的秒
     */
    private static final long DATEMM = 86400;

    /**
     * 获得当前时间(字符串类型) 格式：2014-12-02 10:38:53
     * @return 获取正常时间格式
     */
    public static String getCurrentTime() {
        return getCurrentTime(FORMAT_YYYY_MM_DD_HH_MM_SS);
    }
    
    /**
     * 获得当前时间(字符串类型) 格式：2014-12-02 10:38:53
     * @return 获取正常时间格式
     */
    public static String getCurrentTimeMS() {
        return getCurrentTime(FORMAT_YYYY_MM_DD_HH_MM_SS_SSS);
    }

    /**
     * 获得当前时间(字符串类型) 格式：2014-12-02 10:38:53
     * @param date 字符日期
     * @return String
     */
    public static String getCurrentTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat(date);
        return format.format(new Date());
    }

    /**
     * 获得当前时间(日期类型) 格式：2014-12-02 10:38:53
     *
     * @return String
     */
    public static Date getSysTime() {
        return DateUtil.stringToDate(getCurrentTime(), FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取年月日(日期类型) 格式：2014-12-02
     *
     * @return String
     */
    public static Date getSysDate() {
        return DateUtil.stringToDate(getCurrentTime(), FORMAT_YYYY_MM_DD);
    }

    /**
     * 获取年月日(字符串类型) 格式：2014-12-02
     *
     * @return String
     */
    public static String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        return format.format(new Date());
    }

    /**
     * 可以获取昨天的日期 格式：2014-12-01
     *
     * @return String
     */
    public static String getYesterdayYYYYMMDD() {
        Date date = new Date(System.currentTimeMillis() - DATEMM * DigitalConstant.ONE_THOUSAND);
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        String str = format.format(date);
        try {
            SimpleDateFormat formatNew = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
            date = formatNew.parse(str + " 00:00:00");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 可以获取后退N天的日期 格式：传入2 得到2014-11-30
     *
     * @param backDay
     *            字符串日期
     * @return String 格式化后的日期
     */
    public String getStrDate(String backDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Integer.parseInt("-" + backDay));
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        String back = sdf.format(calendar.getTime());
        return back;
    }

    /**
     * 获取当前的年、月、日
     *
     * @return String
     */
    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YEAR_YYYY);
        return sdf.format(new Date());
    }

    /**
     *  获取当前月
     *
     * @return 当前月
     */
    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_MONTH_MM);
        return sdf.format(new Date());
    }

    /**
     *  获取当前日
     *
     * @return 当前日
     */
    public static String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY_DD);
        return sdf.format(new Date());
    }

    /**
     * 获取今天0点开始的秒数
     *
     * @return long
     */
    public static long getTimeNumberToday() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        String str = sdf.format(date);
        try {
            date = sdf.parse(str);
            return date.getTime() / DigitalConstant.ONE_THOUSAND;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 获取今天的日期 格式：20141202
     *
     * @return String
     */
    public static String getTodateString() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDD);
        String str = sdf.format(new Date());
        return str;
    }

    /**
     * 获取昨天的日期 格式：20141201
     *
     * @return String
     */
    public static String getYesterdayString() {
        Date date = new Date(System.currentTimeMillis() - DATEMM * DigitalConstant.ONE_THOUSAND);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDD);
        String str = sdf.format(date);
        return str;
    }

    /**
     * 获得昨天零点
     *
     * @return Date
     */
    public static Date getYesterDayZeroHour() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        return cal.getTime();
    }

    /**
     * 把long型日期转String
     *
     * @param date
     *            long型日期
     * @param format
     *            日期格式
     * @return 格式化的日期
     */
    public static String longToString(long date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        java.util.Date dt2 = new Date(date * DigitalConstant.ONE_THOUSAND);
        String sDateTime = sdf.format(dt2); // 得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    /**
     * 把long型日期转String
     *
     * @param date
     *            long型日期
     * @param format
     *            日期格式
     * @return 格式化的日期
     */
    public static String millToString(long date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        java.util.Date dt2 = new Date(date);
        String sDateTime = sdf.format(dt2); // 得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    /**
     * 把long型日期转String
     *
     * @param date
     *            long型日期
     * @return 格式化的日期
     */
    public static String millisecondToyyyyMMddHHmmss(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        java.util.Date dt2 = new Date(date);
        String sDateTime = sdf.format(dt2); // 得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    /**
     * 获得今天零点
     *
     * @return Date
     */
    public static Date getTodayZeroHour() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        return cal.getTime();
    }

    /**
     * 获得昨天23时59分59秒
     *
     * @return 日期
     */
    public static Date getYesterDay24Hour() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.SECOND, DigitalConstant.FIFTY_NINE);
        cal.set(Calendar.MINUTE, DigitalConstant.FIFTY_NINE);
        cal.set(Calendar.HOUR, DigitalConstant.TWENTY_THREE);
        return cal.getTime();
    }

    /**
     * String To Date
     *
     * @param date
     *            待转换的字符串型日期；
     * @param format
     *            转化的日期格式
     * @return 返回该字符串的日期型数据；
     */
    public static Date stringToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获得指定日期所在的自然周的第一天，即周日
     *
     * @param date
     *            日期
     * @return 自然周的第一天
     */
    public static Date getStartDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, 1);
        return c.getTime();
    }

    /**
     * 获得指定日期所在的自然周的最后一天，即周六
     *
     * @param date
     *            java.util.Date对象
     * @return 最后一天
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, DigitalConstant.SEVEN);
        return c.getTime();
    }

    /**
     * 获得指定日期所在当月第一天
     *
     * @param date
     *            java.util.Date对象
     * @return Date对象
     */
    public static Date getStartDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获得指定日期所在当月最后一天
     *
     * @param date
     *            日期对象
     * @return 日期对象
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    /**
     * 获得指定日期的下一个月的第一天
     *
     * @param date
     *            java.util.Date对象
     * @return Date对象
     */
    public static Date getStartDayOfNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获得指定日期的下一个月的最后一天
     *
     * @param date
     *            java.util.Date对象
     * @return Date对象
     */
    public static Date getLastDayOfNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 2);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }
    
    /**
     * 获取当前日期是星期几
     * @param date
     * @return int
     */
    public static int getDayForWeek(String date) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        try {
            Date parseDate = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate);
            return calendar.get(Calendar.DAY_OF_WEEK) - 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 求某一个时间向前多少秒的时间(currentTimeToBefer)
     *
     * @param givedTime
     *            给定的时间
     * @param interval
     *            间隔时间的毫秒数；计算方式 ：n(天)*24(小时)*60(分钟)*60(秒)(类型)
     * @param formatDateSign
     *            输出日期的格式；如yyyy-MM-dd、yyyyMMdd等；
     * @return 格式化后的日期
     */
    public static String givedTimeToBefer(String givedTime, long interval, String formatDateSign) {
        String tomorrow = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatDateSign);
            Date gDate = sdf.parse(givedTime);
            long current = gDate.getTime(); // 将Calendar表示的时间转换成毫秒
            long beforeOrAfter = current - interval * DigitalConstant.ONE_THOUSAND; // 将Calendar表示的时间转换成毫秒
            Date date = new Date(beforeOrAfter); // 用timeTwo作参数构造date2
            tomorrow = new SimpleDateFormat(formatDateSign).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tomorrow;
    }

    /**
     * 把String 日期转换成long型日期
     *
     * @param date
     *            String 型日期
     * @param format
     *            日期格式
     * @return 毫秒时间戳
     */
    public static long stringToLong(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt2 = null;
        long lTime = 0;
        try {
            dt2 = sdf.parse(date);
            // 继续转换得到秒数的long型
            lTime = dt2.getTime() / DigitalConstant.ONE_THOUSAND;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lTime;
    }
    
    /**
     * 把String 日期转换成long型日期
     *
     * @param date
     *            String 型日期
     * @param format
     *            日期格式
     * @return 毫秒时间戳
     */
    public static long stringToLongMS(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt2 = null;
        long lTime = 0;
        try {
            dt2 = sdf.parse(date);
            // 继续转换得到秒数的long型
            lTime = dt2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lTime;
    }

    /**
     * 把String 日期转换成long型日期
     *
     * @param date
     *            String 型日期
     * @param format
     *            日期格式
     * @param defValue
     *            默认值
     * @return 毫秒时间戳
     */
    public static long stringToMillLong(String date, String format, long defValue) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt2 = null;
        long lTime = 0;
        try {
            dt2 = sdf.parse(date);
            lTime = dt2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return defValue;
        }
        return lTime;
    }

    /**
     * 得到二个日期间的间隔日期；
     *
     * @param endTime
     *            结束时间
     * @param beginTime
     *            开始时间
     * @param isEndTime
     *            是否包含结束日期；
     * @return 结果日期
     */
    public static Map<String, String> getTwoDay(String endTime, String beginTime, boolean isEndTime) {
        Map<String, String> result = new HashMap<String, String>();
        if (StringUtils.isEmpty(beginTime) && StringUtils.isEmpty(endTime)) {
            return null;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
            java.util.Date date = sdf.parse(endTime);
            String newEndTime = sdf.format(date);
            java.util.Date mydate = sdf.parse(beginTime);
            long day = (date.getTime() - mydate.getTime()) / (TimeConstant.ONE_DAY);
            result = getDate(newEndTime, Integer.parseInt(day + ""), isEndTime);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 得到二个日期间的间隔日期；
     *
     * @param endTime
     *            结束时间
     * @param beginTime
     *            开始时间
     * @param isEndTime
     *            是否包含结束日期；
     * @return 间隔日期
     */
    public static Integer getTwoDayInterval(String endTime, String beginTime, boolean isEndTime) {
        if (StringUtils.isEmpty(beginTime) && StringUtils.isEmpty(endTime)) {
            return 0;
        }

        long day = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
            java.util.Date date = sdf.parse(endTime);
            java.util.Date mydate = sdf.parse(beginTime);
            day = (date.getTime() - mydate.getTime()) / (TimeConstant.ONE_DAY);
        } catch (Exception e) {
            return 0;
        }

        return Integer.parseInt(day + "");
    }

    /**
     * 根据结束时间以及间隔差值，求符合要求的日期集合；
     *
     * @param endTime
     *            结束时间
     * @param interval
     *            间隔差值
     * @param isEndTime
     *            是否结束时间
     * @return 日期集合
     */
    public static Map<String, String> getDate(String endTime, Integer interval, boolean isEndTime) {
        Map<String, String> result = new HashMap<String, String>();
        if (interval == 0 || isEndTime) {
            if (isEndTime) {
                result.put(endTime, endTime);
            }
        }
        if (interval > 0) {
            int begin = 0;
            for (int i = begin; i < interval; i++) {
                String newEndTime = givedTimeToBefer(endTime, DATEMM, FORMAT_YYYY_MM_DD);
                result.put(newEndTime, newEndTime);
            }
        }
        return result;
    }

    /**
     * 获取格式化后的时间
     * @param date 日期
     * @param format 格式化
     * @return 字符串日期
     */
    public static String getFormatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取零点时间 1当天,2:本周,3:本月
     *
     * @param sgin 返回时间
     * @return Long
     */
    public static Long getWeebAndMonth(Integer sgin) {
        Calendar cal = Calendar.getInstance();
        //本周1 0点时间
        if (sgin == 2) {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return cal.getTime().getTime();
        } else if (sgin == 3) {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            return cal.getTime().getTime();
        } else if (sgin == 1) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime().getTime();
        }
        return null;
    }

    /**
     * 数字常量
     * @author lw
     */
    public interface DigitalConstant {

        /**
         * 5
         */
        int FIVE = 5;

        /**
         * 6
         */
        int SIX = 6;

        /**
         * 7
         */
        int SEVEN = 7;

        /**
         * 10
         */
        int TEN = 10;

        /**
         * 16
         */
        int SIXTEEN = 16;

        /**
         * 17
         */
        int SEVENTEEN = 17;

        /**
         * 23
         */
        int TWENTY_THREE = 23;

        /**
         * 24
         */
        int TWENTY_FOUR = 24;

        /**
         * 59
         */
        int FIFTY_NINE = 59;

        /**
         * 60
         */
        int SIXTY = 60;

        /**
         * 100
         */
        int ONE_HUNDRED = 100;

        /**
         * 1000
         */
        int ONE_THOUSAND = 1000;

        /**
         * 1000
         */
        Long ONE_THOUSAND_LONG = 1000L;
    }

    /**
     * 时间常量
     * @author lw
     */
    public interface TimeConstant {

        /**
         * 1分钟
         */
        long ONE_MIN = 60000;

        /**
         * 5分钟
         */
        long FIVE_MIN = 300000;

        /**
         * 10分钟
         */
        long TEN_MIN = 600000;

        /**
         * 15分钟
         */
        long FIFTEEN_MIN = 900000;

        /**
         * 半小时
         */
        long HALF_HOUR = 1800000;

        /**
         * 1小时
         */
        long ONE_HOUR = 3600000;

        /**
         * 12小时
         */
        long TWELVE_HOURS = ONE_HOUR * 12;

        /**
         * One Day
         */
        long ONE_DAY = ONE_HOUR * 24;
    }

}
