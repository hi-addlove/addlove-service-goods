package com.addlove.service.goods.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.addlove.service.goods.util.excel.ExcelUtil;
import com.alibaba.fastjson.util.IOUtils;

/**
 * ftp工具类
 * @author lw
 */
public class FtpUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FtpUtil.class);

    /**
     * FTP默认端口号
     */
    private static final int DEFAULT_PORT = 21;

    //ftp协议编码
    private static final String LOCAL_CHARSET = "UTF8";

    //ftp协议编码
    private static final String SERVER_CHARSET = "ISO-8859-1";

    /**
     * 默认目录分割符号
     */
    public static final String SIGN_SLASH = "/";

    /**
     * ftp临时目录
     */
    public static final String TEMP_PATH = SIGN_SLASH + "temp";

    /**
     * 获取ftp连接
     *
     * @param ftpInfo
     *            链接对象
     * @return FTPClient对象
     */
    public static FTPClient connectFtp(FtpInfo ftpInfo) {
        if (StringUtils.isBlank(ftpInfo.getIpAddr())) {
            throw new RuntimeException("未指定ftp服务地址");
        }

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpInfo.getIpAddr(), ftpInfo.getPort() == null ? DEFAULT_PORT : ftpInfo.getPort());
            ftpClient.login(ftpInfo.getUserName(), ftpInfo.getPwd());

            // 如果延时不在200到300之间，就关闭连接
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new RuntimeException("未连接到FTP，用户名或密码错误.");
            }

            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            ftpClient.setControlEncoding(LOCAL_CHARSET);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
        } catch (IOException e) {
            LOG.error("连接FTP服务器失败,ip:" + ftpInfo.getIpAddr(), e);
            throw new RuntimeException("连接FTP服务器失败,ip:" + ftpInfo.getIpAddr(), e);
        }
        return ftpClient;
    }

    /**
     * 关闭ftp连接
     *
     * @param ftpClient
     *            ftpClient对象
     */
    public static void closeFtp(FTPClient ftpClient) {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                LOG.error("关闭ftp连接异常", e);
            }
        }
    }

    /**
     * 从FTP服务器下载文件
     *
     * @param ftpInfo
     *            ftp信息对象
     * @param localPath
     *            本地路径
     * @throws IOException 
     */
    public static void downloadFtpFile(FtpInfo ftpInfo, String localPath) throws Exception {
        FTPClient ftpClient = connectFtp(ftpInfo);
        OutputStream outputStream = null;
        try {
            ftpClient.changeWorkingDirectory(getFtpPath(ftpInfo.getPath()));
            File localDirectory = new File(localPath);
            if(!localDirectory.exists() && !localDirectory.isDirectory()) {
                localDirectory.mkdirs();
            }
            File localFile = new File(localPath + SIGN_SLASH + ftpInfo.getFileName());
            if (localFile.exists()) {
                localFile.delete();
            }
            outputStream = new FileOutputStream(localFile);
            ftpClient.retrieveFile(getFtpPath(ftpInfo.getFileName()), outputStream);
        } catch (Exception e) {
            LOG.error("下载文件错误,path:{},filename:{}", ftpInfo.getPath(), ftpInfo.getFileName(), e);
            throw e;
        } finally {
            IOUtils.close(outputStream);
            closeFtp(ftpClient);
        }
    }

    /**
     * 获取ftp输入流
     * @param ftpInfo ftp信息
     * @param filePath 文件路径
     * @return 响应流
     */
    public static InputStream getFtpFileInputStream(FtpInfo ftpInfo, String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new RuntimeException("未指定ftp文件目录");
        }
        FTPClient ftpClient = connectFtp(ftpInfo);
        try {
            InputStream in = ftpClient.retrieveFileStream(getFtpPath(filePath));
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            IOUtils.close(in);
            return new ByteArrayInputStream(swapStream.toByteArray());
        } catch (IOException e) {
            LOG.error("文件读取异常:path:" + ftpInfo.getPath() + ",name:" + ftpInfo.getFileName(), e);
            throw new RuntimeException("文件读取异常:path:" + ftpInfo.getPath() + ",name:" + ftpInfo.getFileName(), e);
        } finally {
            closeFtp(ftpClient);
        }
    }

    /**
     * 上传文件到ftp,根据时间产生目录
     * @param ftpInfo ftp信息
     * @param inputStream 输入流
     * @param nowTime 当前时间
     * @return 是否上传成功
     * @throws Exception
     */
    public static boolean uploadFileToFtpService(FtpInfo ftpInfo, InputStream inputStream, Long nowTime)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String timePath = StringUtils.replace(format.format(nowTime), "-", SIGN_SLASH);
        ftpInfo.setPath(ftpInfo.getPath() + SIGN_SLASH + timePath);
        return uploadFileToFtpService(ftpInfo, inputStream);
    }

    /**
     * 上传文件到ftp指定目录
     * @param ftpInfo ftp信息
     * @param inputStream 输入流
     * @return 是否上传成功
     */
    public static boolean uploadFileToFtpService(FtpInfo ftpInfo, InputStream inputStream) {
        return uploadFileToFtpService(ftpInfo, ftpInfo.getPath(), ftpInfo.getFileName(), inputStream);
    }

    /**
     * 上传文件到ftp指定目录
     * @param ftpInfo ftp信息
     * @param basePath 基础路径
     * @param fileName 文件名称
     * @param inputStream 输入流
     * @return 结果
     */
    public static boolean uploadFileToFtpService(FtpInfo ftpInfo, String basePath, String fileName,
            InputStream inputStream) {
        if (StringUtils.isBlank(basePath)) {
            throw new RuntimeException("未指定ftp根目录");
        }

        if (StringUtils.isBlank(fileName)) {
            throw new RuntimeException("未指定ftp文件名称");
        }

        FTPClient ftpClient = connectFtp(ftpInfo);
        try {
            changeWorkingDirectory(ftpClient, basePath);
            if (!ftpClient.storeFile(getFtpPath(fileName), inputStream)) {
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException("文件上传错误", e);
        } finally {
            IOUtils.close(inputStream);
            closeFtp(ftpClient);
        }
        return true;
    }

    /**
     * 导出excel存放到ftp
     * @param list 基础数据
     * @param ftpInfo ftp信息
     * @param basePath 基础路径
     * @param fileName 文件名称
     * @return ftp真实路径
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> String exportExcelToFtp(List<T> list, FtpInfo ftpInfo, String basePath, String fileName)
            throws Exception {
        return exportExcelToFtp(new List[] { list }, ftpInfo, basePath, fileName);
    }

    /**
     * 导出excel存放到ftp 如果数据为空，将不产生对应的单元表
     * @param listArray 来源数据
     * @param ftpInfo ftp信息
     * @param basePath 基础路径
     * @param fileName 文件名称
     * @return 结果
     * @throws Exception
     */
    public static <T> String exportExcelToFtp(List<T>[] listArray, FtpInfo ftpInfo, String basePath, String fileName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ExcelUtil.exportExcel(out, listArray, StringUtils.endsWithIgnoreCase(fileName, "xlsx"));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
            if (uploadFileToFtpService(ftpInfo, basePath, fileName, inputStream)) {
                if (StringUtils.endsWith(basePath, SIGN_SLASH)) {
                    return basePath + fileName;
                }
                return basePath + SIGN_SLASH + fileName;
            }
            throw new RuntimeException("导出excel文件到ftp未成功;basePath:" + basePath + ",fileName:" + fileName);
        } catch (Exception e) {
            LOG.error("导出excel文件到ftp异常;basePath:" + basePath + ",fileName:" + fileName, e);
            throw new RuntimeException("导出excel文件到ftp异常;basePath:" + basePath + ",fileName:" + fileName, e);
        } finally {
            IOUtils.close(out);
        }
    }

    /**
     * 导出excel存放到ftp 如果数据为空，将产生对应的单元表
     * @param sheetDefine 单元表定义
     * @param sheetData 单元表数据
     * @param ftpInfo  ftp信息
     * @param basePath 基础路径
     * @param fileName 文件名称
     * @return 结果
     */
    public static <T> String exportExcelToFtp(List<Class<?>> sheetDefine, List<T>[] sheetData, FtpInfo ftpInfo,
            String basePath, String fileName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ExcelUtil.exportExcel(out, sheetDefine, sheetData, StringUtils.endsWithIgnoreCase(fileName, "xlsx"));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
            if (uploadFileToFtpService(ftpInfo, basePath, fileName, inputStream)) {
                if (StringUtils.endsWith(basePath, SIGN_SLASH)) {
                    return basePath + fileName;
                }
                return basePath + SIGN_SLASH + fileName;
            }
            throw new RuntimeException("导出excel文件到ftp未成功;basePath:" + basePath + ",fileName:" + fileName);
        } catch (Exception e) {
            LOG.error("导出excel文件到ftp异常;basePath:" + basePath + ",fileName:" + fileName, e);
            throw new RuntimeException("导出excel文件到ftp异常;basePath:" + basePath + ",fileName:" + fileName, e);
        } finally {
            IOUtils.close(out);
        }
    }

    /**
     * 移动文件
     * @param ftpInfo ftp信息
     * @param from 来源地址
     * @param basePath 目标地址
     * @return 结果
     */
    public static FTPFile moveFile(FtpInfo ftpInfo, String from, String basePath) {
        if (StringUtils.isBlank(basePath) || StringUtils.isBlank(from)) {
            throw new RuntimeException("未指定ftp文件目录:bathPath:" + basePath + ",from:" + from);
        }
        FTPFile file = null;
        FTPClient ftpClient = connectFtp(ftpInfo);
        try {
            String source = getFtpPath(from);
            String target = getFtpPath((basePath + "/" + new File(from).getName()));
            //尝试移动
            if (!ftpClient.rename(source, target)) {
                //移动失败，进行数据检查

                //已经导入到目标文件夹了直接返回
                //因为文件名称经过运算,所以不容易遇见已经存在的可能,但是终端可能处理错误，
                //如果ftp已经完成移动.为了处理方便，目标列表存在就不在坐后续处理
                FTPFile[] ftpFiles = ftpClient.listFiles(target);
                if (ftpFiles != null && ftpFiles.length != 0) {
                    return ftpFiles[0];
                }

                ftpFiles = ftpClient.listFiles(source);
                if (ftpFiles == null || ftpFiles.length == 0) {
                    throw new RuntimeException("在ftp中未查找到原文件,source:" + source);
                }

                //创建目录后再试一次
                ftpClient.makeDirectory(basePath);
                if (!ftpClient.rename(source, target)) {
                    throw new RuntimeException("文件迁移失败,source:" + source + ";to:" + target);
                }
            }

            FTPFile[] ftpFiles = ftpClient.listFiles(target);
            if (ftpFiles != null && ftpFiles.length > 0) {
                file = ftpFiles[0];
            }
        } catch (IOException e) {
            LOG.error("文件读取异常:path:{},name:{},filePath:{}", ftpInfo.getPath(), ftpInfo.getFileName(), from, e);
        } finally {
            closeFtp(ftpClient);
        }

        if (file == null) {
            throw new RuntimeException("文件迁移失败,source:" + from + ";bath:" + basePath);
        }

        return file;
    }

    /**
     * 切换ftp目录
     * @param ftpClient
     * @param uploadFilePath
     * @throws IOException
     */
    private static void changeWorkingDirectory(FTPClient ftpClient, String path) throws IOException {
        String uploadFilePath = getFtpPath(path);
        if (ftpClient.changeWorkingDirectory(uploadFilePath)) {
            return;
        }

        // 逐层切换
        String[] split1 = StringUtils.split(uploadFilePath, SIGN_SLASH);
        StringBuilder sbs = new StringBuilder();
        for (String s : split1) {
            if ("".equals(s)) {
                continue;
            }
            sbs.append(SIGN_SLASH).append(s);
            if (ftpClient.changeWorkingDirectory(sbs.toString())) {
                continue;
            }

            if (!ftpClient.makeDirectory(sbs.toString())) {
                throw new RuntimeException("创建文件目录失败");
            } else {
                ftpClient.changeWorkingDirectory(sbs.toString());
            }
        }
    }

    private static String getFtpPath(String path) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(path)) {
            throw new RuntimeException("ftp路径为空");
        }
        path = StringUtils.replace(path, "\\", SIGN_SLASH).replace("//", SIGN_SLASH);
        return new String(path.getBytes(LOCAL_CHARSET), SERVER_CHARSET);
    }

}
