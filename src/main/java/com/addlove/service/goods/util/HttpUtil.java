package com.addlove.service.goods.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;

import javax.servlet.ServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * http 工具类
 * @author lw
 *
 */
public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

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
    public static final String CHARSET_NAME = "UTF-8";

    /**
     * 获取请求Body
     *
     * @param request
     *            请求对象
     * @return 结果
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(CHARSET_NAME)));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("io exception :{}", e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error("io exception:{}", e);
                }
            }
        }
        return sb.toString();
    }

    /**
     * POST请求
     *
     * @param url 请求url
     * @param bodyJsonString 请求json参数
     * @return 响应结果字符串
     */
    public static String postHttpRequest(String url, String bodyJsonString) {
        return postHttpRequest(url, bodyJsonString, null, DEFAULT_CONNECTION_TIMEOUT, null);
    }

    /**
     * POST请求
     *
     * @param url 请求url
     * @param bodyJsonString 请求json参数
     * @param secretKey 密钥
     * @return 响应结果字符串
     */
    public static String postHttpRequest(String url, String bodyJsonString, String secretKey) {
        return postHttpRequest(url, bodyJsonString, null, DEFAULT_CONNECTION_TIMEOUT, secretKey);
    }

    /**
     * POST请求
     *
     * @param url 请求url
     * @param bodyJsonString 请求json参数
     * @param socketTimeoutMilli 通信超时时间
     * @param secretKey 密钥
     * @return 响应结果字符串
     */
    public static String postHttpRequest(String url, String bodyJsonString, int socketTimeoutMilli, String secretKey) {
        return postHttpRequest(url, bodyJsonString, null, socketTimeoutMilli, secretKey);

    }

    /**
     * POST请求
     *
     * @param url 请求url
     * @param bodyJsonString 请求json参数
     * @param headers 头信息集合
     * @param socketTimeout 通信超时时间
     * @param secretKey 密钥
     * @return 响应结果字符串
     */
    public static String postHttpRequest(String url, String bodyJsonString, Collection<Header> headers,
            int socketTimeout, String secretKey) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
        if (socketTimeout > 0) {
            managerParams.setSoTimeout(socketTimeout);
        } else {
            managerParams.setSoTimeout(DEFAULT_SOCKET_TIMEOUT);
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, CHARSET_NAME);
        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                postMethod.setRequestHeader(header);
            }
        }
        if (!StringUtils.isEmpty(secretKey)) {
            String encryptedBody = SHA256EncryptUtil.encrypt(secretKey, bodyJsonString);
            postMethod.setRequestHeader(new Header("encryptedMsgBody", encryptedBody));
        }

        String requestBody = null;
        try {
            if (!StringUtils.isEmpty(bodyJsonString)) {
                // 发送json数据需要设置contentType
                StringRequestEntity s = new StringRequestEntity(bodyJsonString, "application/json", CHARSET_NAME);
                postMethod.setRequestEntity(s);
            }
            int statusCode = httpClient.executeMethod(postMethod);
            byte[] responseBody = postMethod.getResponseBody();
            // 处理内容
            requestBody = new String(responseBody);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("Method failed: {}, url:{}, body:{}", postMethod.getStatusLine(), url, requestBody);
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Encode Exception:{}", e);
        } catch (HttpException e) {
            LOGGER.error("HTTP exception:{}", e);
        } catch (IOException e) {
            LOGGER.error("IO exception:{}", e);
        } finally {
            try {
                // 释放链接
                postMethod.releaseConnection();
                HttpConnectionManager hcm = httpClient.getHttpConnectionManager();
                if (hcm instanceof SimpleHttpConnectionManager) {
                    ((SimpleHttpConnectionManager) hcm).shutdown();
                }
            } catch (Exception e2) {
                LOGGER.error("Close Httpconnect exception:{}", e2);
            }
        }

        return requestBody;
    }
    
    public static String postHttpClientRequest(String url, String bodyJsonString, Collection<Header> headers,
            int socketTimeout) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
        if (socketTimeout > 0) {
            managerParams.setSoTimeout(socketTimeout);
        } else {
            managerParams.setSoTimeout(DEFAULT_SOCKET_TIMEOUT);
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, CHARSET_NAME);
        if (CollectionUtils.isNotEmpty(headers)) {
            for (Header header : headers) {
                postMethod.setRequestHeader(header);
            }
        }
//        if (!StringUtils.isEmpty(secretKey)) {
//            String encryptedBody = SHA256EncryptUtil.encrypt(secretKey, bodyJsonString);
//            postMethod.setRequestHeader(new Header("encryptedMsgBody", encryptedBody));
//        }

        String requestBody = null;
        try {
            if (!StringUtils.isEmpty(bodyJsonString)) {
                // 发送json数据需要设置contentType
                StringRequestEntity s = new StringRequestEntity(bodyJsonString, "application/json", CHARSET_NAME);
                postMethod.setRequestEntity(s);
            }
            int statusCode = httpClient.executeMethod(postMethod);
            byte[] responseBody = postMethod.getResponseBody();
            // 处理内容
            requestBody = new String(responseBody);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("Method failed: {}, url:{}, body:{}", postMethod.getStatusLine(), url, requestBody);
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Encode Exception:{}", e);
        } catch (HttpException e) {
            LOGGER.error("HTTP exception:{}", e);
        } catch (IOException e) {
            LOGGER.error("IO exception:{}", e);
        } finally {
            try {
                // 释放链接
                postMethod.releaseConnection();
                HttpConnectionManager hcm = httpClient.getHttpConnectionManager();
                if (hcm instanceof SimpleHttpConnectionManager) {
                    ((SimpleHttpConnectionManager) hcm).shutdown();
                }
            } catch (Exception e2) {
                LOGGER.error("Close Httpconnect exception:{}", e2);
            }
        }

        return requestBody;
    }
}
