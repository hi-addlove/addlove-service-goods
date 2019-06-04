package com.addlove.service.goods.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 工作表注解
 * @author lw
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SheetInfo {

    /**
     * 工作表名称
     * @return 工作表名称
     */
    public String sheetName() default "";

    /**
     * 工作表序列
     * @return 工作表序列
     */
    public int sheetIndex() default 0;

    /**
     * 工作表列数
     * @return 工作表列数
     */
    public int minColumn() default 16;

    /**
     * 跳过行数
     * @return 跳过行数
     */
    public int skipRow() default 1;

    /**
     * 单元格配置
     * @author Dts
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.FIELD })
    public @interface CellInfo {
        /**
         * 单元格表头名称
         * @return 单元格表头名称
         */
        String name() default "";

        /**
         * 单元格列编号
         * @return 单元格列编号
         */
        int seq() default -1;

        /**
         * 是否跳过该单元格
         * @return 是否跳过该单元格
         */
        boolean skip() default false;

        /**
         * 导出是否启用该字段
         * @return 导出是否启用该字段
         */
        boolean isExport() default false;
    }
}
