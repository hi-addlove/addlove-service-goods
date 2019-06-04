package com.addlove.service.goods.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * ID生成器工具类
 *
 */
public class GeneratorIdHttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorIdHttpUtil.class);

    /**
     * 通信超时时间，单位ms
     */
    public static final int DEFAULT_SOCKET_TIMEOUT = 2000;

    /**
     * 链接超时时间，单位ms
     */
    public static final int DEFAULT_CONNECTION_TIMEOUT = 2000;

    /**
     * UTF-8 编码
     */
    private static final String UNICODE_UTF8 = "UTF-8";

    /**
     * 调用ID生成器生成id
     *
     * @param url
     *            ID生成器的地址
     * @param bizName
     *            业务标识
     * @param headers 头信息
     * @param requestContent 请求消息
     * @return id
     */
    public static String createGeneratorId(String url, String bizName, List<Header> headers, String requestContent) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
        managerParams.setSoTimeout(DEFAULT_SOCKET_TIMEOUT);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, UNICODE_UTF8);
        Header sign = new Header("cs", "XGR7@3-Wcu");
        postMethod.setRequestHeader(sign);
        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                postMethod.setRequestHeader(header);
            }
        }
        postMethod.setRequestHeader("serviceName", "ngoc-service-generator");
        String resultBody = null;
        try {
            // 发送json数据需要设置contentType
            StringRequestEntity s = new StringRequestEntity(requestContent, "application/json", UNICODE_UTF8);
            postMethod.setRequestEntity(s);
            int statusCode = httpClient.executeMethod(postMethod);
            byte[] responseBody = postMethod.getResponseBody();
            // 处理内容
            resultBody = new String(responseBody);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("Method failed: {}, url:{}, body:{}", postMethod.getStatusLine(), url, resultBody);
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("请求id生成器异常, requestMsg:{}, exception:{} ", requestContent, e);
        } catch (HttpException e) {
            LOGGER.error("请求id生成器异常, requestMsg:{}, exception:{}.", e);
        } catch (IOException e) {
            LOGGER.error("请求id生成器异常, requestMsg:{}, exception:{}", e);
        } finally {
            postMethod.releaseConnection();
        }

        return resultBody;
    }

    /**
     * 调用ID生成器生成id
     *
     * @param url
     *            ID生成器的地址
     * @param bizName
     *            业务标识
     * @param headers 头信息
     * @return id
     */
    @Deprecated
    public static String createGeneratorId(String url, String bizName, List<Header> headers) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
        managerParams.setSoTimeout(DEFAULT_SOCKET_TIMEOUT);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, UNICODE_UTF8);
        Header sign = new Header("cs", "XGR7@3-Wcu");
        postMethod.setRequestHeader(sign);
        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                postMethod.setRequestHeader(header);
            }
        }
        postMethod.setRequestHeader("serviceName", "ngoc-service-generator");
        String requestBody = null;
        JSONObject obj = new JSONObject();
        obj.put("bizName", bizName);
        String bodyJsonString = JsonUtil.toJson(obj);
        try {
            // 发送json数据需要设置contentType
            StringRequestEntity s = new StringRequestEntity(bodyJsonString, "application/json", UNICODE_UTF8);
            postMethod.setRequestEntity(s);
            int statusCode = httpClient.executeMethod(postMethod);
            byte[] responseBody = postMethod.getResponseBody();
            // 处理内容
            requestBody = new String(responseBody);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("Method failed: {}, url:{}, body:{}", postMethod.getStatusLine(), url, requestBody);
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("请求id生成器异常, requestMsg:{}, exception:{} ", bodyJsonString, e);
        } catch (HttpException e) {
            LOGGER.error("请求id生成器异常, requestMsg:{}, exception:{}.", e);
        } catch (IOException e) {
            LOGGER.error("请求id生成器异常, requestMsg:{}, exception:{}", e);
        } finally {
            postMethod.releaseConnection();
        }

        return requestBody;
    }
}
