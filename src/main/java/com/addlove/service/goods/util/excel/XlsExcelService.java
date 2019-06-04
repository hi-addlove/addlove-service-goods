package com.addlove.service.goods.util.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.addlove.service.goods.util.excel.SheetInfo.CellInfo;

/**
 * xls服务工具
 * @author lw
 */
public class XlsExcelService extends AbstractExcelService {

    /**
     * 读取excel多个工作表数据
     * @param in 输入流
     * @return 解析对象
     * @throws Exception
     */
    public static Map<Integer, List<List<String>>> readExcel(InputStream in) throws Exception {
        Map<Integer, List<List<String>>> dataMap = new HashMap<Integer, List<List<String>>>();
        Workbook workbook = create(in);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            List<List<String>> data = readSheet(workbook, i);
            dataMap.put(i, data);
        }
        return dataMap;
    }

    /**
     * 读取excel单个工作表数据
     * @param workbook 工作表
     * @param sheetIndex 工作表序列
     * @return 解析结果
     */
    public static List<List<String>> readSheet(Workbook workbook, int sheetIndex) {
        List<List<String>> dataList = new ArrayList<List<String>>();
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            List<String> rowLst = new ArrayList<String>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    continue;
                }
                String value = parseCell(cell);
                rowLst.add(value);

            }
            dataList.add(rowLst);
        }
        return dataList;
    }

    /**
     *  读取excel
     * @param in 输入流
     * @param entryCls 实例
     * @return 解析结果
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> readerExcel(InputStream in, Class<T> entryCls) throws Exception {
        Map<Class<T>, List<T>> map = readerExcel(in, new Class[] { entryCls });
        return map.get(entryCls);
    }

    /**
     * 读取excel
     * @param inp T
     * @param entryClsArray  实例
     * @return 解析结果
     * @throws Exception
     */
    public static <T> Map<Class<T>, List<T>> readerExcel(InputStream inp, Class<T>[] entryClsArray) throws Exception {
        Workbook workbook = create(inp);
        Map<Class<T>, List<T>> map = new HashMap<Class<T>, List<T>>();
        for (Class<T> cls : entryClsArray) {
            SheetInfo sheetInfo = cls.getAnnotation(SheetInfo.class);
            if (sheetInfo == null) {
                throw new UnsupportedOperationException("请配置模板信息");
            }

            Sheet sheet = workbook.getSheetAt(sheetInfo.sheetIndex());
            List<T> ret = new LinkedList<T>();
            if (sheet.getLastRowNum() <= sheetInfo.skipRow()) {
                map.put(cls, ret);
                continue;
            }

            List<Field> fields = getCellFiledList(cls);
            for (int i = sheetInfo.skipRow(); i <= sheet.getLastRowNum(); i++) {
                T entity = parseRow(fields, sheet.getRow(i), cls);
                if (entity != null) {
                    ret.add(entity);
                }
            }
            map.put(cls, ret);
        }
        return map;
    }

    private static <T> T parseRow(List<Field> fields, Row row, Class<T> cls) throws Exception {
        T entity = cls.newInstance();
        boolean isNull = true;
        for (Field field : fields) {
            if (field == null) {
                continue;
            }

            CellInfo attr = field.getAnnotation(CellInfo.class);
            if (attr == null || attr.skip()) {
                continue;
            }

            Cell cell = row.getCell(attr.seq(), Row.RETURN_BLANK_AS_NULL);
            if (cell == null) {
                continue;
            }

            field.setAccessible(true);
            String value = parseCell(cell);
            setEntityValue(entity, field, value);
            isNull = false;
        }

        if (isNull) {
            return null;
        }
        return entity;
    }

    private static String parseCell(Cell cell) {
        if (cell == null) {
            return null;
        }
        String value = null;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            DecimalFormat decimalFormat = new DecimalFormat("#.000000");
            value = decimalFormat.format(cell.getNumericCellValue());
            if (value.matches("^[-+]?\\d+\\.[0]+$")) {
                value = value.substring(0, value.indexOf("."));
            } else if (cell.getNumericCellValue() == 0) {
                value = 0 + "";
            } else {
                value = cell.getNumericCellValue() + "";
            }
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            value = cell.getStringCellValue();
        }

        if (value != null) {
            value = value.replaceAll("#N/A", "").trim();
        }
        return value;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     * 如果list为空，将不生成对应的单元表
     * @param sheetData 列表
     * @param sheetSize 单个表格允许的最大数据行，如果超过，将生产多个单元表
     * @param output 输出流
     * @return 是否成功
     * @throws Exception
     */
    public static <T> boolean exportExcel(List<T>[] sheetData, int sheetSize, OutputStream output) throws Exception {
        List<Class<?>> sheetDefine = new ArrayList<Class<?>>();
        for (List<T> tempList : sheetData) {
            if (tempList == null || tempList.size() == 0) {
                sheetDefine.add(null);
                continue;
            }
            Class<?> cls = tempList.get(0).getClass();
            sheetDefine.add(cls);
        }
        return exportExcel(sheetDefine, sheetData, sheetSize, output);
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     * 如果list为空，也会生成对应的单元表
     * @param sheetDefine 单元表定义
     * @param sheetData 单元表数据
     * @param sheetSize 单个表格允许的最大数据行，如果超过，将生产多个单元表
     * @param output 输出流
     * @throws Exception
     * @return 解析结果
     */
    public static <T> boolean exportExcel(List<Class<?>> sheetDefine, List<T>[] sheetData, int sheetSize,
            OutputStream output) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(); // 产生工作薄对象
        Set<String> existSheetName = new HashSet<String>();
        //excel总共的表格数量
        int allSheetNo = 0;
        for (int q = 0; q < sheetData.length; q++) {
            Class<?> defineCls = sheetDefine.get(q);
            if (defineCls == null) {
                continue;
            }

            List<T> tempList = sheetData[q];
            if (CollectionUtils.isNotEmpty(tempList)) {
                Class<?> check = tempList.get(0).getClass();
                if (!defineCls.equals(check)) {
                    throw new RuntimeException("列表定义和数据定义不一致");
                }
            }

            // 获取导出属性
            SheetInfo sheetInfo = defineCls.getAnnotation(SheetInfo.class);
            List<Field> fields = new LinkedList<Field>();
            for (Field f : getCellFiledList(defineCls)) {
                CellInfo attr = f.getAnnotation(CellInfo.class);
                if (attr.isExport()) {
                    fields.add(f);
                }
            }

            // 单个导出列表对应的表格数量
            //取出一共有多少个sheet.需要添加表头
            double perSheetNo = Math.ceil((tempList.size() + 1) / (double) sheetSize);
            for (int index = 0; index < perSheetNo; index++) {
                HSSFSheet sheet = workbook.createSheet(); // 产生工作表对象
                String sheetName = sheetInfo.sheetName() + (perSheetNo > 1 ? "_" + (index + 1) : "");
                if (existSheetName.contains(sheetName)) {
                    sheetName = sheetName + "_" + index;
                }
                existSheetName.add(sheetName);
                workbook.setSheetName(allSheetNo++, sheetName); // 设置工作表的名称.
                sheet.setDefaultColumnWidth(sheetInfo.minColumn() + 10);

                //写入表头
                int rowNumber = 0;
                HSSFRow row = sheet.createRow(rowNumber++); // 产生一行
                for (int i = 0, k = 0; i < fields.size(); i++) {
                    HSSFCell cell = row.createCell(k); // 创建列
                    Field field = fields.get(i);
                    CellInfo attr = field.getAnnotation(CellInfo.class);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置列中写入内容为String类型
                    cell.setCellValue(attr.name()); // 写入列名
                    k++;
                }

                int startNo = index * sheetSize;
                int endNo = Math.min(startNo + sheetSize, tempList.size());
                // 写入各条记录,每条记录对应excel表中的一行
                for (int i = startNo; i < endNo; i++) {
                    row = sheet.createRow(rowNumber++);
                    T vo = (T) tempList.get(i); // 得到导出对象.
                    for (int j = 0, k = 0; j < fields.size(); j++) {
                        Field field = fields.get(j); // 获得field.
                        Cell cell = row.createCell(k); // 创建cell
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        Object obj = field.get(vo);
                        if (obj == null) {
                            cell.setCellValue("");
                        } else {
                            cell.setCellValue(String.valueOf(obj));
                        }
                        k++;
                    }
                }
            }
        }

        try {
            workbook.write(output);
            output.flush();
            return true;
        } catch (IOException e) {
            LOGGER.error("导出文件异常", e);
            return false;
        }
    }

    /**
     * 创建工作表
     * @param inp
     * @return
     * @throws Exception
     */
    private static Workbook create(InputStream inp) throws Exception {
        if (!inp.markSupported() && !(inp instanceof PushbackInputStream)) {
            inp = new PushbackInputStream(inp, 8);
        }

        if (POIFSFileSystem.hasPOIFSHeader(inp)) {
            return new HSSFWorkbook(inp);
        }
        if (POIXMLDocument.hasOOXMLHeader(inp)) {
            return new XSSFWorkbook(OPCPackage.open(inp));
        }
        throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
    }
}
