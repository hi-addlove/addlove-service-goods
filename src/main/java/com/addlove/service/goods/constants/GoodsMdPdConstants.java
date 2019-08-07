package com.addlove.service.goods.constants;

/**
 * 门店盘点枚举类
 * @author lw
 *
 */
public interface GoodsMdPdConstants {

    
    /**
     * 保存类型：1-编辑保存；2-记账；3-启动
     * @author lw
     *
     */
    public enum SaveType {
        /**
         * 1-编辑保存
         */
        EDIT_SAVE(1, "编辑保存"),
        /**
         * 2-记账
         */
        PD_ACCOUNT(2, "盘点记账"),
        /**
         * 3-启动
         */
        PDSTART_UP(3, "启动");
        
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
     * 门店盘点业务类型
     * @author lw
     *
     */
    public enum YwType {
        /**
         * 6213-门店盘点
         */
        MD_PD("6213", "门店盘点");
        
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
     * 单据类型:0-日盘；1-周盘；2-月盘
     * @author lw
     *
     */
    public enum BillType {
        /**
         * 0-日盘
         */
        DAY_PD("0", "日盘"),
        
        /**
         * 1-周盘
         */
        WEEK_PD("1", "周盘"),
        
        /**
         * 2-月盘
         */
        MONTH_PD("2", "月盘");
        
        private String value;
        private String name;
        
        private BillType(String value, String name) {
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
     * 盘点状态:0-未执行；1-已执行；2-已完成
     * @author lw
     *
     */
    public enum CouStatus {
        /**
         * 0-未执行
         */
        NOT_EXEC("0", "未执行"),
        
        /**
         * 1-已执行
         */
        HAS_EXEC("1", "已执行"),
        
        /**
         * 2-已完成
         */
        HAS_COMPLETED("2", "已完成");
        
        private String value;
        private String name;
        
        private CouStatus(String value, String name) {
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
}
