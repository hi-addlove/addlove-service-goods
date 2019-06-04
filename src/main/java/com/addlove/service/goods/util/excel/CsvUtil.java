package com.addlove.service.goods.util.excel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.util.IOUtils;

/**
 * csv读取工具
 * @author lw
 */
public class CsvUtil extends AbstractExcelService {

    /**
     * csv解析
     * @param inp 输入流
     * @param charSet 字符集
     * @param cls 类名
     * @return 解析对象
     * @throws Exception
     */
    public static <T> List<T> readFromFile(InputStream inp, String charSet, Class<T> cls) throws Exception {
        return readFromFile(inp, charSet, 1, cls);
    }

    /**
     * csv解析
     * @param inp 输入
     * @param charSet 字符集
     * @param beginLine 开始行
     * @param cls 类名
     * @return  解析结果
     * @throws Exception
     */
    public static <T> List<T> readFromFile(InputStream inp, String charSet, int beginLine, Class<T> cls)
            throws Exception {
        List<T> retList = new LinkedList<T>();
        BufferedReader br = null;
        try {
            Map<Integer, Field> fieldMap = getCellFiledMap(cls);
            if (fieldMap.isEmpty()) {
                LOGGER.info("类：{}未解析到数据映射关系,请确认是否准确", cls.getName());
                return retList;
            }

            br = new BufferedReader(new InputStreamReader(inp, charSet));
            String line = null;
            int skipeNumber = 0;
            while ((line = br.readLine()) != null) {
                if (skipeNumber++ < beginLine) {
                    continue;
                }

                if (line.startsWith("=")) {
                    line = line.substring(1);
                }

                line = line.replaceAll("\"", "");
                String[] datas = line.split(",");
                retList.add(convertEntity(datas, fieldMap, cls));
            }
        } catch (Exception e) {
            LOGGER.error("解析文件异常：{}", cls.getName(), e);
        } finally {
            IOUtils.close(br);
        }
        return retList;
    }

}
