package com.addlove.service.goods.constants;

/**
 * 门店盘点枚举类
 * @author lw
 *
 */
public interface GoodsAdlYhConstants {
    /**
     * 保存类型：1-保存;2-提交
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
     * 门店报损业务类型
     * @author lw
     *
     */
    public enum YwType {
        /**
         * 6205-门店要货
         */
        MD_YH("6205", "门店要货");
        
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
     * 审核状态:0-未申请；1-已申请；2-审核通过；3-审核驳回
     * @author lw
     *
     */
    public enum YwStatus {
        /**
         * 0-未确认
         */
        NOT_CONFIRM("0", "未确认"),
        
        /**
         * 1-已确认
         */
        HAS_CONFIRM("1", "已确认"),
        
        /**
         * 2-已作废
         */
        HAS_INVALID("2", "已作废");
        
        private String value;
        private String name;
        
        private YwStatus(String value, String name) {
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
         * 2008-要货波次
         */
        YH_BC("2008", "要货波次");
        
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
     * 是否可用
     * @author lw
     *
     */
    public enum isUse {
        /**
         * 0-不可用
         */
        UN_USE("0", "不可用"),
        /**
         * 1-可用
         */
        CAN_USE("1", "可用");
        
        private String value;
        private String name;
        
        private isUse(String value, String name) {
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
     * 要货类型
     * @author lw
     *
     */
    public enum yhType {
        /**
         * 0-普通要货
         */
        ORDINARY_YH("0", "普通要货"),
        /**
         * 1-紧急要货
         */
        URGENT_YH("1", "紧急要货");
        
        private String value;
        private String name;
        
        private yhType(String value, String name) {
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
}
