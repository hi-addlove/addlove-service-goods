package com.addlove.service.goods.util.excel;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 使用CVS模式解决XLSX文件，可以有效解决用户模式内存溢出的问题 该模式是POI官方推荐的读取大数据的模式，在用户模式下，数据量较大、Sheet较多、或者是有很多无用的空行的情况
 * ，容易出现内存溢出,用户模式读取Excel的典型代码如下： FileInputStream file=new FileInputStream("c:\\test.xlsx"); Workbook wb=new
 * XSSFWorkbook(file);
 */
public class XlsxExcelReaderService extends AbstractExcelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XlsxExcelReaderService.class);

    /**
     * The type of the data value is indicated by an attribute on the cell. The value is usually in a "v" element within
     * the cell.
     */
    enum XSSFDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
    }

    /**
     * 使用xssf_sax_API处理Excel,请参考： http://poi.apache.org/spreadsheet/how-to.html#xssf_sax_api
     * <p/>
     * Also see Standard ECMA-376, 1st edition, part 4, pages 1928ff, at
     * http://www.ecma-international.org/publications/standards/Ecma-376.htm
     * <p/>
     * A web-friendly version is http://openiso.org/Ecma/376/Part4
     */
    class MyXSSFSheetHandler extends DefaultHandler {

        /**
         * Table with styles
         */
        private StylesTable stylesTable;

        /**
         * Table with unique strings
         */
        private ReadOnlySharedStringsTable sharedStringsTable;

        /**
         * Number of columns to read starting with leftmost
         */
        private final int minColumnCount;

        // Set when V start element is seen
        private boolean vIsOpen;

        // Set when cell start element is seen;
        // used when cell close element is seen.
        private XSSFDataType nextDataType;

        // Used to format numeric cell values.
        private short formatIndex;
        private String formatString;
        private final DataFormatter formatter;

        private int thisColumn = -1;
        // The last column printed to the output stream
        private int lastColumnNumber = -1;

        // Gathers characters as they are seen.
        private StringBuffer value;
        private String[] record;
        private List<String[]> rows = new ArrayList<String[]>();
        private boolean isCellNull = false;

        /**
         * Accepts objects needed while parsing.
         * @param styles
         *            Table of styles
         * @param strings
         *            Table of shared strings
         * @param cols
         *            Minimum number of columns to show
         * @param target
         *            Sink for output
         */
        public MyXSSFSheetHandler(StylesTable styles, ReadOnlySharedStringsTable strings, int cols) {
            this.stylesTable = styles;
            this.sharedStringsTable = strings;
            this.minColumnCount = cols;
            this.value = new StringBuffer();
            this.nextDataType = XSSFDataType.NUMBER;
            this.formatter = new DataFormatter();
            record = new String[this.minColumnCount];
            rows.clear(); // 每次读取都清空行集合
        }

        /*
         * (non-Javadoc)
         * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
         * org.xml.sax.Attributes)
         */
        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {

            if ("inlineStr".equals(name) || "v".equals(name)) {
                vIsOpen = true;
                value.setLength(0);
            } else if ("c".equals(name)) { // c => cell
                // Get the cell reference
                String r = attributes.getValue("r");
                int firstDigit = -1;
                for (int c = 0; c < r.length(); ++c) {
                    if (Character.isDigit(r.charAt(c))) {
                        firstDigit = c;
                        break;
                    }
                }
                thisColumn = nameToColumn(r.substring(0, firstDigit));

                // Set up defaults.
                this.nextDataType = XSSFDataType.NUMBER;
                this.formatIndex = -1;
                this.formatString = null;
                String cellType = attributes.getValue("t");
                String cellStyleStr = attributes.getValue("s");
                if ("b".equals(cellType)) {
                    nextDataType = XSSFDataType.BOOL;
                } else if ("e".equals(cellType)) {
                    nextDataType = XSSFDataType.ERROR;
                } else if ("inlineStr".equals(cellType)) {
                    nextDataType = XSSFDataType.INLINESTR;
                } else if ("s".equals(cellType)) {
                    nextDataType = XSSFDataType.SSTINDEX;
                } else if ("str".equals(cellType)) {
                    nextDataType = XSSFDataType.FORMULA;
                } else if (cellStyleStr != null) {
                    // It's a number, but almost certainly one
                    // with a special style or format
                    int styleIndex = Integer.parseInt(cellStyleStr);
                    XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                    this.formatIndex = style.getDataFormat();
                    this.formatString = style.getDataFormatString();
                    if (this.formatString == null) {
                        this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
                    }
                }
            }

        }

        /*
         * (non-Javadoc)
         * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
         */
        public void endElement(String uri, String localName, String name) throws SAXException {
            String thisStr = null;
            // v => contents of a cell
            if ("v".equals(name)) {
                // Process the value contents as required.
                // Do now, as characters() may be called more than once
                switch (nextDataType) {

                case BOOL:
                    char first = value.charAt(0);
                    thisStr = first == '0' ? "FALSE" : "TRUE";
                    break;

                case ERROR:
                    thisStr = "\"ERROR:" + value.toString() + '"';
                    break;

                case FORMULA:
                    // A formula could result in a string value,
                    // so always add double-quote characters.
                    thisStr = value.toString();
                    break;

                case INLINESTR:
                    // TODO: have seen an example of this, so it's untested.
                    XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                    thisStr = rtsi.toString();
                    break;

                case SSTINDEX:
                    String sstIndex = value.toString();
                    try {
                        int idx = Integer.parseInt(sstIndex);
                        XSSFRichTextString rtss = new XSSFRichTextString(sharedStringsTable.getEntryAt(idx));
                        thisStr = rtss.toString();
                    } catch (NumberFormatException ex) {
                        LOGGER.error("Failed to parse SST index '" + sstIndex + "': " + ex.toString());
                    }
                    break;

                case NUMBER:
                    String n = value.toString();
                    // 判断是否是日期格式
                    if (HSSFDateUtil.isADateFormat(this.formatIndex, n)) {
                        Double d = Double.parseDouble(n);
                        Date date = HSSFDateUtil.getJavaDate(d);
                        thisStr = formateDateToString(date);
                    } else if (this.formatString != null) {
                        thisStr = formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex,
                                this.formatString);
                    } else {
                        thisStr = n;
                    }
                    break;

                default:
                    thisStr = "(TODO: Unexpected type: " + nextDataType + ")";
                    break;
                }

                // Output after we've seen the string contents
                // Emit commas for any fields that were missing on this row
                if (lastColumnNumber == -1) {
                    lastColumnNumber = 0;
                }
                // 判断单元格的值是否为空
                if (thisStr == null || "".equals(thisStr.trim())) {
                    isCellNull = true; // 设置单元格是否为空值
                }
                record[thisColumn] = thisStr;
                // Update column
                if (thisColumn > -1) {
                    lastColumnNumber = thisColumn;
                }
            } else if ("row".equals(name)) {
                // Print out any missing commas if needed
                if (minColumns > 0) {
                    // Columns are 0 based
                    if (lastColumnNumber == -1) {
                        lastColumnNumber = 0;
                    }

                    //不存在空行就加入
                    if (!isCellNull) {
                        for (String s : record) {
                            if (StringUtils.isNotBlank(s)) {
                                rows.add(record.clone());
                                break;
                            }
                        }
                        isCellNull = false;
                        for (int i = 0; i < record.length; i++) {
                            record[i] = null;
                        }
                    }
                }
                lastColumnNumber = -1;
            }

        }

        public List<String[]> getRows() {
            return rows;
        }

        public void setRows(List<String[]> rows) {
            this.rows = rows;
        }

        /**
         * Captures characters only if a suitable element is open. Originally was just "v"; extended for inlineStr also.
         */
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (vIsOpen) {
                value.append(ch, start, length);
            }
        }

        /**
         * Converts an Excel column name like "C" to a zero-based index.
         * @param name
         * @return Index corresponding to the specified name
         */
        private int nameToColumn(String name) {
            int column = -1;
            for (int i = 0; i < name.length(); ++i) {
                int c = name.charAt(i);
                column = (column + 1) * 26 + c - 'A';
            }
            return column;
        }

        private String formateDateToString(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化日期
            return sdf.format(date);

        }

    }

    // /////////////////////////////////////

    private OPCPackage xlsxPackage;
    private int minColumns;
    private String sheetName;

    /**
     * Creates a new XLSX -> CSV converter
     * @param pkg The XLSX package to process
     * @param sheetName 单元格名称
     * @param minColumns 最小解析行
     */
    public XlsxExcelReaderService(OPCPackage pkg, String sheetName, int minColumns) {
        this.xlsxPackage = pkg;
        this.minColumns = minColumns;
        this.sheetName = sheetName;
    }

    /**
     * Parses and shows the content of one sheet using the specified styles and shared-strings tables.
     * @param styles 样式
     * @param strings 内容
     * @param sheetInputStream 流
     * @return 解析结果
     */
    public List<String[]> processSheet(StylesTable styles, ReadOnlySharedStringsTable strings,
            InputStream sheetInputStream) throws IOException, ParserConfigurationException, SAXException {

        InputSource sheetSource = new InputSource(sheetInputStream);
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        XMLReader sheetParser = saxParser.getXMLReader();
        MyXSSFSheetHandler handler = new MyXSSFSheetHandler(styles, strings, this.minColumns);
        sheetParser.setContentHandler(handler);
        sheetParser.parse(sheetSource);
        return handler.getRows();
    }

    /**
     * 初始化这个处理程序 将
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @return 解析结果
     */
    public List<String[]> process() throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        List<String[]> list = null;
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        while (iter.hasNext()) {
            InputStream stream = iter.next();
            String sheetNameTemp = iter.getSheetName();
            if (this.sheetName.equals(sheetNameTemp)) {
                list = processSheet(styles, strings, stream);
                stream.close();
                break;
            }
        }
        return list;
    }

    /**
     * 初始化这个处理程序 将
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @return 解析结果
     */
    public Map<String, List<List<String>>> processAllSheet() throws IOException, OpenXML4JException,
            ParserConfigurationException, SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        List<String[]> list = null;
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
        while (iter.hasNext()) {
            InputStream stream = iter.next();
            list = processSheet(styles, strings, stream);
            if (CollectionUtils.isNotEmpty(list)) {
                List<List<String>> sheetData = new ArrayList<List<String>>();
                for (int i = 0; i < list.size(); i++) {
                    sheetData.add(Arrays.asList(list.get(i)));
                }
                map.put(iter.getSheetName(), sheetData);
            }
            stream.close();
        }
        return map;
    }

    /**
     * 读取Excel
     * @param path
     *            文件路径
     * @param sheetName
     *            sheet名称
     * @param minColumns
     *            列总数
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws OpenXML4JException
     * @throws IOException
     * @return 解析结果
     */
    public static List<String[]> readerExcel(String path, String sheetName, int minColumns) throws IOException,
            OpenXML4JException, ParserConfigurationException, SAXException {
        OPCPackage p = OPCPackage.open(path, PackageAccess.READ);
        XlsxExcelReaderService xlsx2csv = new XlsxExcelReaderService(p, sheetName, minColumns);
        List<String[]> list = xlsx2csv.process();
        p.close();
        return list;
    }

    /**
     * 读取excel
     * @param in 输入流
     * @param sheetName 单元格名称
     * @param minColumns 最小行数
     * @return 解析对象
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static List<String[]> readerExcel(InputStream in, String sheetName, int minColumns) throws IOException,
            OpenXML4JException, ParserConfigurationException, SAXException {
        OPCPackage p = OPCPackage.open(in);
        XlsxExcelReaderService xlsx2csv = new XlsxExcelReaderService(p, sheetName, minColumns);
        List<String[]> list = xlsx2csv.process();
        p.close();
        return list;
    }

    /**
     * 读取excel中所有工作表的数据 ，返回key：sheetName
     * @param in 输入流
     * @param minColumns 最小行
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @return 解析结果
     */
    public static Map<String, List<List<String>>> readerExcel(InputStream in, int minColumns) throws IOException,
            OpenXML4JException, ParserConfigurationException, SAXException {
        OPCPackage p = OPCPackage.open(in);
        XlsxExcelReaderService xlsx2csv = new XlsxExcelReaderService(p, "", minColumns);
        Map<String, List<List<String>>> map = xlsx2csv.processAllSheet();
        p.close();
        return map;
    }

    /**
     * @param in 输入流
     * @param sheetNumber 单元表序号
     * @param minColumns 最小行
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @return 解析结果
     */
    public static Map<Integer, List<String[]>> readerExcel(InputStream in, int sheetNumber, int minColumns)
            throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
        OPCPackage p = OPCPackage.open(in);
        Map<Integer, List<String[]>> map = new HashMap<Integer, List<String[]>>();
        for (int index = 0; index < sheetNumber; index++) {
            XlsxExcelReaderService xlsx2csv = new XlsxExcelReaderService(p, "", minColumns);
            List<String[]> list = xlsx2csv.process(index);
            map.put(index, list);
        }
        p.close();
        return map;
    }

    /**
     * 初始化这个处理程序 将
     * @param sheetIndex 单元格
     * @return 响应
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public List<String[]> process(int sheetIndex) throws IOException, OpenXML4JException, ParserConfigurationException,
            SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        List<String[]> list = null;
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        int index = 0;
        while (iter.hasNext()) {
            InputStream stream = iter.next();
            if (index == sheetIndex) {
                list = processSheet(styles, strings, stream);
                stream.close();
                break;
            }
            index++;
        }
        return list;
    }

    /**
     * 读取excel
     * @param in 输入流
     * @param entryCls 解析实例
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
     * @param in 输入流
     * @param entryClsArray 解析列表
     * @return 响应
     * @throws Exception
     */
    public static <T> Map<Class<T>, List<T>> readerExcel(InputStream in, Class<T>[] entryClsArray) throws Exception {
        OPCPackage p = OPCPackage.open(in);
        Map<Class<T>, List<T>> map = new HashMap<Class<T>, List<T>>();
        for (Class<T> cls : entryClsArray) {
            SheetInfo sheetInfo = cls.getAnnotation(SheetInfo.class);
            if (sheetInfo == null) {
                throw new UnsupportedOperationException("请配置模板信息");
            }

            XlsxExcelReaderService xlsx2csv = new XlsxExcelReaderService(p, sheetInfo.sheetName(),
                    sheetInfo.minColumn());
            List<String[]> list = xlsx2csv.process();
            List<T> ret = convertEntityList(list, sheetInfo.skipRow(), cls);
            map.put(cls, ret);
        }
        p.close();
        return map;
    }
}