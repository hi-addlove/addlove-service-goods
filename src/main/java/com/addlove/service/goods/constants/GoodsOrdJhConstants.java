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
        DELIVERY_ACCEPTANCE("0908", "配送验收");
        
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
}
