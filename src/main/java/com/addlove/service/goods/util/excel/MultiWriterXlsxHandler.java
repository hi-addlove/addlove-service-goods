package com.addlove.service.goods.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import com.addlove.service.goods.util.excel.SheetInfo.CellInfo;

/**
 *分段式导出xlsx；由于导出数据较多时，先将数据读取到内存会造成内存压力过大，
 *因此开发此多段导出功能，用户可以从数据库获取部分数据后。立即写入到xlsx文本中，并根据阈值决定将文件落在本地硬盘上，在结束的时候。将数据流转换出来。
 */
public class MultiWriterXlsxHandler extends AbstractExcelService {
    private SXSSFWorkbook workBook = null;
    private CellStyle cellStyle = null;
    private Map<Class<?>, XlsxWriter> writerMap = null;
    /**
     * 初始化方法，定义xlsx的单元表，及表头信息
     * @param sheetDefine 要导出的数据定义
     */
    private MultiWriterXlsxHandler(List<Class<?>> sheetDefine) {
        //定义检查
        for (Class<?> defineCls : sheetDefine) {
            if (defineCls == null) {
                throw new RuntimeException("定义类为空，无法创建excel文件");
            }

            // 获取工作表元属性
            SheetInfo sheetInfo = defineCls.getAnnotation(SheetInfo.class);
            if (sheetInfo == null) {
                throw new RuntimeException("定义类没有Excel描述，无法创建excel文件");
            }
        }

        // 在内存当中保持 1000 行 , 超过的数据放到硬盘中
        writerMap = new HashMap<Class<?>, XlsxWriter>();
        workBook = new SXSSFWorkbook(1000);
        cellStyle = workBook.createCellStyle();
        cellStyle.setDataFormat(workBook.createDataFormat().getFormat("General"));

        //初始化单元格
        for (Class<?> defineCls : sheetDefine) {
            SheetInfo sheetInfo = defineCls.getAnnotation(SheetInfo.class);
            List<Field> fields = new LinkedList<Field>();
            for (Field f : getCellFiledList(defineCls)) {
                CellInfo attr = f.getAnnotation(CellInfo.class);
                if (attr.isExport()) {
                    fields.add(f);
                }
            }

            String sheetName = sheetInfo.sheetName();
            Sheet sheet = workBook.createSheet(sheetName);
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
            writerMap.put(defineCls, new XlsxWriter(sheet, fields, rowNumber));
        }
    }
    
    /**
     * 获取写入实例
     * @param sheetDefine 实例
     * @return
     */
    public static MultiWriterXlsxHandler open(Class<?> sheetDefine) {
        if (sheetDefine == null) {
            throw new RuntimeException("列表定义列表为空，无法创建excel文件");
        }
        List<Class<?>> defineList = new ArrayList<Class<?>>();
        defineList.add(sheetDefine);
        return new MultiWriterXlsxHandler(defineList);
    }

    /**
     * 获取写入实例
     * @param sheetDefine 实例
     * @return
     */
    public static MultiWriterXlsxHandler open(List<Class<?>> sheetDefine) {
        if (CollectionUtils.isEmpty(sheetDefine)) {
            throw new RuntimeException("列表定义列表为空，无法创建excel文件");
        }
        return new MultiWriterXlsxHandler(sheetDefine);
    }

    /**
     * 数据写入，要求写入的数据是事先定义好的格式
     * @param sheetData 数据
     */
    public <T> void write(List<T> sheetData) {
        if (CollectionUtils.isEmpty(sheetData)) {
            return;
        }

        Class<?> defineCls = sheetData.get(0).getClass();
        XlsxWriter writer = writerMap.get(defineCls);
        if (writer == null) {
            throw new RuntimeException("数据未找到对应的描述定义,请在open时传入描述定义");
        }

        // 写行
        List<Field> fields = writer.getField();
        int rowNumber = writer.getRowNumber();
        for (T model : sheetData) {
            Row row = writer.getSheet().createRow(rowNumber++);
            row.setRowStyle(cellStyle);
            for (int j = 0, k = 0; j < fields.size(); j++) {
                Field field = fields.get(j); // 获得field.
                Cell cell = row.createCell(k); // 创建cell
                cell.setCellStyle(cellStyle);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                Object obj =null;
                try {
                    obj = field.get(model);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (obj == null) {
                    cell.setCellValue("");
                } else {
                    cell.setCellValue(String.valueOf(obj));
                }
                k++;
            }
        }
        writer.setRowNumber(rowNumber);
    }

    /**
     * 多数据写入
     * @param sheetData
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public <T> void multiWrite(List<T>[] sheetData) throws IllegalArgumentException, IllegalAccessException {
        for (List<T> data : sheetData) {
            write(data);
        }
    }

    /**
     * 写入完成，将写入的数据读取到输出流
     * @param out 输出
     * @throws IOException
     */
    public void finish(OutputStream out) throws IOException {
        workBook.write(out);
        out.flush();
    }

    /**
     * 对象
     */
    private class XlsxWriter {
        private Sheet sheet;
        private List<Field> field;
        private int rowNumber;

        public XlsxWriter(Sheet sheet, List<Field> field, int rowNumber) {
            this.sheet = sheet;
            this.field = field;
            this.rowNumber = rowNumber;
        }

        /**
         * @return the sheet
         */
        public Sheet getSheet() {
            return this.sheet;
        }

        /**
         * @return the field
         */
        public List<Field> getField() {
            return this.field;
        }

        /**
         * @return the rowNumber
         */
        public int getRowNumber() {
            return this.rowNumber;
        }

        /**
         * @param rowNumber the rowNumber to set
         */
        public void setRowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
        }
    }
}