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
        
        private ModelTags(String name, String value) {
            this.name = name;
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
