package com.addlove.service.goods.constants;

/**
 * 门店配送退货申请单(质量退货)枚举类
 * @author lw
 *
 */
public interface GoodsDstRtnConstants {
    /**
     * 保存类型：1-保存;2-记账
     * @author lw
     *
     */
    public enum SaveType {
        /**
         * 1-保存
         */
        SAVE(1, "保存"),
        /**
         * 2-记账
         */
        EXEC_ACCOUNT(2, "记账");
        
        private int value;
        private String name;
        
        private SaveType(int value, String name) {
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
    
    /**
     * 配送退货类型
     * @author lw
     *
     */
    public enum YwType {
        /**
         * 2004-配送退货
         */
        PS_RTN("2004", "配送退货");
        
        private String value;
        private String name;
        
        private YwType(String value, String name) {
            this.value = value;
            this.name = name;
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
    }
    
    /**
     * 数据状态：0－录入；1－审批；2－转审；3－提交；4-可记账；9-关闭
     * @author lw
     *
     */
    public enum DataStatus {
        /**
         * 0－录入
         */
        ENTRY("0", "录入"),
        /**
         * 1－审批
         */
        APPROVAL("1", "审批"),
        /**
         * 2－转审
         */
        REVIEW("2", "转审"),
        /**
         * 3－提交
         */
        SUBMIT("3", "提交"),
        /**
         * 4-可记账
         */
        ACCOUNT_ABLE("4", "可记账"),
        /**
         * 9-关闭
         */
        CLOSED("9", "关闭");
        
        private String value;
        private String name;
        
        private DataStatus(String value, String name) {
            this.value = value;
            this.name = name;
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
    }
    
    /**
     * 分类类型
     * @author lw
     *
     */
    public enum FlCode {
        /**
         * 2002-退货原因
         */
        TH_REASON("2002", "退货原因");
        
        private String value;
        private String name;
        
        private FlCode(String value, String name) {
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
     * 单据状态:0－已驳回；1－待确认；3－已确认
     * @author lw
     *
     */
    public enum BillStatus {
        /**
         * 0-已驳回
         */
        HAS_REJECTED("0", "已驳回"),
        
        /**
         * 1-待确认
         */
        WAIT_CONFIRM("1", "待确认"),
        
        /**
         * 3－已确认
         */
        HAS_CONFIRM("3", "已确认");
        
        private String value;
        private String name;
        
        private BillStatus(String value, String name) {
            this.value = value;
            this.name = name;
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
    }
}
