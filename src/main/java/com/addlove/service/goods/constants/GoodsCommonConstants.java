package com.addlove.service.goods.constants;
/**
 * 配送验收枚举类
 * @author lw
 *
 */
public interface GoodsCommonConstants {
    /**
     * 单据类型
     * @author lw
     *
     */
    public enum BillType{
        /**
         * 退货申请业务类型
         */
        RETURN_APPLY("0918", "退货申请"),
        /**
         * 验收申请单据类型
         */
        ACCEPTANCE_APPLY("0904", "验收申请"),
        
        /**
         * 调拨
         */
        DB_ORDER("1804", "调拨"),
        
        /**
         * 6209-生产计划
         */
        PRO_PLAN_ORDER("6209", "生产计划业务"),
        
        /**
         * 0905-采购退货业务类型
         */
        PURCHASE_RETURN("0905", "采购退货"),;
        
        private String value;
        private String name;
        
        private BillType(String value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }
    
    /**
     * 存储过程结果
     * @author lw
     *
     */
    public enum ProcedureResult {
        /**
         * 1-执行成功
         */
        EXEC_SUCCESS(0, "执行成功"),
        /**
         * -1-错误：记录日志，返回错误信息
         */
        EXEC_ERROR_RECORD(-1, "错误：记录日志，返回错误信息"),
        /**
         * -2-错误：中途退出，返回错误信息
         */
        EXEC_ERROR_EXIT(-2, "错误：中途退出，返回错误信息");
        
        private int value;
        private String name;
        
        private ProcedureResult(int value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * @return the value
         */
        public int getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(int value) {
            this.value = value;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }
}
