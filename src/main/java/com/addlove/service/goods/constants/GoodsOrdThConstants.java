package com.addlove.service.goods.constants;
/**
 * 退货申请枚举类
 * @author lw
 *
 */
public interface GoodsOrdThConstants {
    /**
     * 单据类型
     * @author lw
     *
     */
    public enum BillType{
        /**
         * 退货申请业务类型
         */
        RETURN_APPLY("0918", "退货申请");
        
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
     * 退货申请业务类型
     * @author lw
     *
     */
    public enum YwType{
        /**
         * 退货申请业务类型
         */
        SHOP_PURCHASE_RETURN("0911", "门店采购退货"),
        SHOP_TO_ZB("0912", "门店采购退货"),
        SHOP_TO_SUPPLIER("0913", "门店采购退货"),
        ZB_PURCHASE_RETURN("0914", "门店采购退货");
        
        private String value;
        private String name;
        
        private YwType(String value, String name) {
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
