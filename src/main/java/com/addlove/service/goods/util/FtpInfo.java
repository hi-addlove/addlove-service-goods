package com.addlove.service.goods.util;

/**
 * ftp连接信息
 * @author lw
 *
 */
public class FtpInfo {

    /**
     * ip地址
     */
    private String ipAddr;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * aaa路径
     */
    private String path;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 编码
     */
    private String encoding;

    /**
     * 返回 ipAddr
     *
     * @return ipAddr
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * 设置 ipAddr
     *
     * @param ipAddr
     *            value
     * @return 结果
     */
    public FtpInfo setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
        return this;
    }

    /**
     * 返回 port
     *
     * @return port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 设置 port
     *
     * @param port
     *            value
     * @return FtpInfo
     */
    public FtpInfo setPort(Integer port) {
        this.port = port;
        return this;
    }

    /**
     * 返回 userName
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 userName
     *
     * @param userName
     *            value
     * @return FtpInfo
     */
    public FtpInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 返回 pwd
     *
     * @return pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置 pwd
     *
     * @param pwd
     *            value
     * @return FtpInfo
     */
    public FtpInfo setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    /**
     * 返回 path
     *
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置 path
     *
     * @param path
     *            value
     * @return FtpInfo
     */
    public FtpInfo setPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * 返回 fileName
     *
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName 文件名称
     * @return FtpInfo
     */
    public FtpInfo setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * @return the encoding
     */
    public String getEncoding() {
        return this.encoding;
    }

    /**
     * @param encoding the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
