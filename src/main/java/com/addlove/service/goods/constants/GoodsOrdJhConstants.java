package com.addlove.service.goods.constants;
/**
 * 配送验收枚举类
 * @author lw
 *
 */
public interface GoodsOrdJhConstants {
    public enum ModelTags {
        /**
         * 0-未验收
         */
        NOT_ACCEPTANCE("0", "未验收"),
        /**
         * 1-正常验收
         */
        NORMAL_ACCEPTANCE("1", "正常验收"),
        /**
         * 2-异常验收
         */
        ABNORMAL_ACCEPTANCE("2", "异常验收");
        
        private String value;
        private String name;
        
        private ModelTags(String value, String name) {
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
     * 验收业务类型
     * @author lw
     *
     */
    public enum YwType {
        /**
         * 0906-无采购验收
         */
        NO_PURCHASE_ACCEPTANCE("0906", "无采购验收"),
        /**
         * 0908-配送验收
         */
        DELIVERY_ACCEPTANCE("0908", "配送验收"),
        
        /**
         * 1804-调拨业务
         */
        DB_ORDER("1804", "调拨业务"),
        
        /**
         * 6209-生产计划
         */
        PRO_PLAN_ORDER("6209", "生产计划业务"),
        
        /**
         * 0911-采购退货
         */
        PURCHASE_RETURN("0911", "采购退货");
        
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
     * 保存类型：1-保存；2-记账
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
