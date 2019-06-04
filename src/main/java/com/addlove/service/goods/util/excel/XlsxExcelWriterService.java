package com.addlove.service.goods.util.excel;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.addlove.service.goods.util.excel.SheetInfo.CellInfo;


/**
 * 使用CVS模式解决XLSX文件，可以有效解决用户模式内存溢出的问题 该模式是POI官方推荐的读取大数据的模式，在用户模式下，数据量较大、Sheet较多、或者是有很多无用的空行的情况
 * ，容易出现内存溢出,用户模式读取Excel的典型代码如下： FileInputStream file=new FileInputStream("c:\\test.xlsx"); Workbook wb=new
 * XSSFWorkbook(file);
 */
public class XlsxExcelWriterService extends AbstractExcelService {
    /**
     * 大数据量导出
     * @param out 输出流
     * @param sheetData 根据数据列表对象定义单元表格，如果数据对象为空，将不生成单元表
     * @throws Exception 异常
     */
    public static <T> void exportExcel(OutputStream out, List<T>[] sheetData) throws Exception {
        List<Class<?>> sheetDefine = new ArrayList<Class<?>>();
        for (List<T> tempList : sheetData) {
            if (tempList == null || tempList.size() == 0) {
                sheetDefine.add(null);
                continue;
            }
            Class<?> cls = tempList.get(0).getClass();
            sheetDefine.add(cls);
        }
        exportExcel(out, sheetDefine, sheetData);
    }

    /**
     * 大数据量导出 根据数据列表对象定义单元表格，如果数据为空也会生成对应的单元表
     * @param out 输出流
     * @param sheetDefine 单元表定义
     * @param sheetData 单元表数据
     * @throws Exception
     */
    public static <T> void exportExcel(OutputStream out, List<Class<?>> sheetDefine, List<T>[] sheetData)
            throws Exception {
        if (sheetData.length != sheetDefine.size()) {
            throw new RuntimeException("列表定义列表和数据列表的长度不一致");
        }
        // 在内存当中保持 1000 行 , 超过的数据放到硬盘中
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        CellStyle cellType = wb.createCellStyle();
        cellType.setDataFormat(wb.createDataFormat().getFormat("General"));
        Set<String> existSheetName = new HashSet<String>();
        for (int q = 0; q < sheetData.length; q++) {
            Class<?> defineCls = sheetDefine.get(q);
            if (defineCls == null) {
                continue;
            }
            List<T> tempList = sheetData[q];
            Class<?> check = tempList.get(0).getClass();
            if (!defineCls.equals(check)) {
                throw new RuntimeException("列表定义和数据定义不一致");
            }

            // 获取工作表元属性
            SheetInfo sheetInfo = defineCls.getAnnotation(SheetInfo.class);
            if (sheetInfo == null) {
                continue;
            }

            // 获取导出属性
            List<Field> fields = new LinkedList<Field>();
            for (Field f : getCellFiledList(defineCls)) {
                CellInfo attr = f.getAnnotation(CellInfo.class);
                if (attr.isExport()) {
                    fields.add(f);
                }
            }

            String sheetName = sheetInfo.sheetName();
            if (existSheetName.contains(sheetName)) {
                sheetName = sheetName + "_dup";
            }
            existSheetName.add(sheetName);

            Sheet sheet = wb.createSheet(sheetName);
            sheet.setDefaultColumnWidth(sheetInfo.minColumn() + 10);

            // 写入表头
            int rowNumber = 0;
            Row row = sheet.createRow(rowNumber++); //
            for (int i = 0, k = 0; i < fields.size(); i++) {
                Cell cell = row.createCell(k);
                Field field = fields.get(i);
                CellInfo attr = field.getAnnotation(CellInfo.class);
                cell.setCellValue(attr.name());
                k++;
            }

            // 写行
            for (T model : tempList) {
                row = sheet.createRow(rowNumber++);
                row.setRowStyle(cellType);
                for (int j = 0, k = 0; j < fields.size(); j++) {
                    Field field = fields.get(j); // 获得field.
                    Cell cell = row.createCell(k); // 创建cell
                    cell.setCellStyle(cellType);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    Object obj = field.get(model);
                    if (obj == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(String.valueOf(obj));
                    }
                    k++;
                }
            }
        }
        wb.write(out);
        out.flush();
    }
}