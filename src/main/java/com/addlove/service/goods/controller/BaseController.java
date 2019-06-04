package com.addlove.service.goods.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageInfo;
import com.addlove.service.goods.model.PageQuery;
import com.addlove.service.goods.util.ValidateUtil;
import com.addlove.service.goods.util.DateUtil;
import com.addlove.service.goods.util.DateUtil.DigitalConstant;

/**
 * 基础Controller
 *
 * @author haow
 */
public class BaseController {

    @Autowired(required = false)
    private Validator validator;

    /**
     * 获取当前请求request对象
     *
     * @return request对象
     */
    protected HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    /**
     * 获取当前请求reponse对象
     *
     * @return reponse对象
     */
    protected HttpServletResponse getHttpServletReponse() {
        HttpServletResponse reponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        return reponse;
    }

    /**
     * jsonp 安全处理
     *
     * @param jsonp
     *            jsonp
     * @return 处理
     */
    protected String jsonpSecurityFilter(String jsonp) {
        // TODO
        return null;
    }

    /**
     * 判断您是否有xss攻击
     *
     * @param value
     *            值
     * @return true表示存在xss攻击
     */
    protected boolean isSecurity(String value) {
        return true;
    }

    /**
     * 获取参数的值
     *
     * @param name
     *            参数名
     * @return 值
     */
    protected String getStringParameter(String name) {
        return getStringParameter(name, null);
    }

    /**
     * 获取参数的值
     *
     * @param name
     *            参数名
     * @param defaultValue
     *            默认值
     * @return 值
     */
    protected String getStringParameter(String name, String defaultValue) {
        String value = getHttpServletRequest().getParameter(name);

        return StringUtils.isEmpty(value) ? defaultValue : value.trim();
    }

    /**
     * 获取参数的boolean类型值
     *
     * @param name
     *            参数名
     * @return boolean类型值
     */
    protected Boolean getBooleanParameter(String name) {
        return getBooleanParameter(name, null);
    }

    /**
     * 获取参数的boolean类型值
     *
     * @param name
     *            参数名
     * @param defaultValue
     *            默认值
     * @return boolean类型值
     */
    protected Boolean getBooleanParameter(String name, Boolean defaultValue) {
        String value = getHttpServletRequest().getParameter(name);

        return StringUtils.isEmpty(value) ? defaultValue : Boolean.valueOf(value);
    }

    /**
     * 获取参数的Integer类型值
     *
     * @param name
     *            参数名
     * @return Integer类型值
     */
    protected Integer getIntegerParameter(String name) {
        return getIntegerParameter(name, null);
    }

    /**
     * 获取参数的Integer类型值
     *
     * @param name
     *            参数名
     * @param defaultValue
     *            默认值
     * @return Integer类型值
     */
    protected Integer getIntegerParameter(String name, Integer defaultValue) {
        String value = getHttpServletRequest().getParameter(name);

        return StringUtils.isEmpty(value) ? defaultValue : Integer.valueOf(value);
    }

    /**
     * 获取参数的Long类型值
     *
     * @param name
     *            参数名
     * @return Long类型值
     */
    protected Long getLongParameter(String name) {
        return getLongParameter(name, null);
    }

    /**
     * 获取参数的Long类型值
     *
     * @param name
     *            参数名
     * @param defaultValue
     *            默认值
     * @return Long类型值
     */
    protected Long getLongParameter(String name, Long defaultValue) {
        String value = getHttpServletRequest().getParameter(name);

        return StringUtils.isEmpty(value) ? defaultValue : Long.valueOf(value);
    }

    /**
     * 获取参数的Float类型值
     *
     * @param name
     *            参数名
     * @return Float类型值
     */
    protected Float getFloatParameter(String name) {
        return getFloatParameter(name, null);
    }

    /**
     * 获取参数的Float类型值
     *
     * @param name
     *            参数名
     * @param defaultValue
     *            默认值
     * @return Float类型值
     */
    protected Float getFloatParameter(String name, Float defaultValue) {
        String value = getHttpServletRequest().getParameter(name);

        return StringUtils.isEmpty(value) ? defaultValue : Float.valueOf(value);
    }

    /**
     * 获取参数的Double类型值
     *
     * @param name
     *            参数名
     * @return Double类型值
     */
    protected Double getDoubleParameter(String name) {
        return getDoubleParameter(name, null);
    }

    /**
     * 获取参数的Double类型值
     *
     * @param name
     *            参数名
     * @param defaultValue
     *            默认值
     * @return Double类型值
     */
    protected Double getDoubleParameter(String name, Double defaultValue) {
        String value = getHttpServletRequest().getParameter(name);

        return StringUtils.isEmpty(value) ? defaultValue : Double.valueOf(value);
    }

    /**
     * 获取参数的Date类型值
     *
     * @param name
     *            参数名
     * @return Date类型值(yyyy-MM-dd HH:mm:ss)
     */
    protected Date getDateParameter(String name) {
        return getDateParameter(name, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS, null);
    }

    /**
     * 获取参数的Date类型值
     *
     * @param name
     *            参数名
     * @param format
     *            日期格式
     * @return Date类型值
     */
    protected Date getDateParameter(String name, String format) {
        return getDateParameter(name, format, null);
    }

    /**
     * 获取参数的Date类型值
     *
     * @param name
     *            参数名
     * @param format
     *            日期格式
     * @param defaultValue
     *            默认值
     * @return Date类型值
     */
    protected Date getDateParameter(String name, String format, String defaultValue) {
        String value = getHttpServletRequest().getParameter(name);
        DateFormat dateFormat = new SimpleDateFormat(format);

        try {
            if (StringUtils.isBlank(value)) {
                return null == defaultValue ? null : dateFormat.parse(defaultValue);
            } else {
                Long timeMillis = null;

                try {
                    timeMillis = Long.parseLong(value);
                } catch (NumberFormatException e) {
                }

                if (timeMillis != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(timeMillis);
                    return calendar.getTime();
                } else {
                    return dateFormat.parse(value);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建分页对象
     *
     * @param pageInfo
     *            分页对象
     * @return 分页对象
     */
    protected PageQuery getPageQuery(PageInfo<?> pageInfo) {
        Integer pageNo = pageInfo.getPages();
        Integer pageSize = pageInfo.getPageSize();
        Object data = pageInfo.getList();
        Long total = pageInfo.getTotal();
        return new PageQuery(pageNo, pageSize, data, total);
    }

    /**
     * 创建分页对象
     *
     * @return 分页对象
     */
    protected PageQuery getPageQuery() {
        Integer pageNo = getIntegerParameter("pageNo", 1);
        Integer pageSize = getIntegerParameter("pageSize", DigitalConstant.TEN);
        return new PageQuery(pageNo, pageSize);
    }

    /**
     * 设值排序类型和排序字段到分页对象中
     *
     * @param page
     *            分页对象
     */
    protected void setOrderBy(PageQuery page) {
        String orderBy = getStringParameter("order_by");
        String orderType = getStringParameter("order_type");
        if (!StringUtils.isBlank(orderBy)) {
            page.setOrderBy(orderBy);
            page.setOrderType(orderType);
        }
    }

    /**
     * 添加验证类型
     *
     * @param t 类型
     * @return 验证结果
     */
    protected <T> List<String> validate(T t) {
        return ValidateUtil.validate(this.validator, t);
    }

}
