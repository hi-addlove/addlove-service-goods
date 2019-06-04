package com.addlove.service.goods.util.excel;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Excel解析
 * @author lw
 */
public class ExcelUtil {

    /**
     * xls格式表格最大工数据行,需要减掉标题行
     */
    public static final int MAX_XLS_ROW_NUMBER = 65530;

    /**
     * 读取excel,读取单个工作表
     * @param in 输入流
     * @param entryCls 解析模板
     * @return 解析结果
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> readerExcel(InputStream in, Class<T> entryCls) throws Exception {
        Map<Class<T>, List<T>> map = readerExcel(in, new Class[] { entryCls });
        return map.get(entryCls);
    }

    /**
     * 读取excel多个工作表，注意工作表名称不能重复
     * @param in 输入流
     * @param entryClsArray 解析样本
     * @return 解析对象
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<Class<T>, List<T>> readerExcel(InputStream in, Class<T>[] entryClsArray) throws Exception {
        if (!in.markSupported()) {
            in = new PushbackInputStream(in, 8);
        }

        if (POIFSFileSystem.hasPOIFSHeader(in)) {
            return XlsExcelService.readerExcel(in, entryClsArray);
        }

        if (POIXMLDocument.hasOOXMLHeader(in)) {
            return XlsxExcelReaderService.readerExcel(in, entryClsArray);
        }
        return new HashedMap();
    }

    /**
     * 导出单个工作表到excel;根据导出数据量决定导出格式是xls还是xlsx版本
     * 使用方需要注意后缀名称
     * @param out 输出流
     * @param list 数据
     * @throws Exception
     */
    public static <T> void exportExcel(OutputStream out, List<T> list) throws Exception {
        exportExcel(out, list, false);
    }

    /**
     * 导出单个工作表到excel;用户可以强制指定为xlsx， 如果设置isXlsx=false;
     * 但是list的数据量大于xls存储最大数量的时候，为了保证数据不丢失，这里会采用xlsx格式存储;
     * xlsx导出后,需要使用excel打开并重新保存后才能再次导入, 使用方需要注意后缀名称;
     * @param out 输出流
     * @param list 导入数据
     * @param isXlsx 是否xlsx格式
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> void exportExcel(OutputStream out, List<T> list, boolean isXlsx) throws Exception {
        exportExcel(out, new List[] { list }, isXlsx);
    }

    /**
     * 导出多个工作表到excel;
     * @see ExcelUtil#exportExcel(OutputStream, List)
     * @param out 输出流
     * @param listArray 导入数据
     * @throws Exception
     */
    public static <T> void exportExcel(OutputStream out, List<T>[] listArray) throws Exception {
        exportExcel(out, listArray, false);
    }

    /**
     * 导出多个工作表到excel;
     * @see ExcelUtil#exportExcel(OutputStream, List, boolean)
     * @param out 输出流
     * @param sheetData 导入数据
     * @param isXlsx 是否xlsx格式
     * @throws Exception
     */
    public static <T> void exportExcel(OutputStream out, List<T>[] sheetData, boolean isXlsx) throws Exception {
        //数据需要使用2003以后的xlsx格式存储
        if (isXlsx || isXlsxRow(sheetData)) {
            XlsxExcelWriterService.exportExcel(out, sheetData);
        } else {
            XlsExcelService.exportExcel(sheetData, MAX_XLS_ROW_NUMBER, out);
        }
    }

    /**
     * 导出多个工作表到excel;
     * @param out 输出流
     * @param sheetDefine 工作表定义
     * @param sheetData 工作表数据
     * @param isXlsx 是否是2003后格式
     * @throws Exception
     */
    public static <T> void exportExcel(OutputStream out, List<Class<?>> sheetDefine, List<T>[] sheetData, boolean isXlsx)
            throws Exception {
        //数据需要使用2003以后的xlsx格式存储
        if (isXlsx || isXlsxRow(sheetData)) {
            XlsxExcelWriterService.exportExcel(out, sheetDefine, sheetData);
        } else {
            XlsExcelService.exportExcel(sheetDefine, sheetData, MAX_XLS_ROW_NUMBER, out);
        }
    }

    /**
     * 判定数据行是否超过xls允许的最大行
     * @param sheetData
     * @return
     */
    private static <T> boolean isXlsxRow(List<T>[] sheetData) {
        int maxLine = 0;
        for (List<T> list : sheetData) {
            maxLine = Math.max(maxLine, list.size());
        }
        return maxLine > MAX_XLS_ROW_NUMBER;
    }
}
