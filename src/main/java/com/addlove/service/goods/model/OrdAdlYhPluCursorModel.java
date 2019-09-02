package com.addlove.service.goods.model;

import java.io.Serializable;

/**
 * 获取要货商品明日数量的存储过程
 * @author lw
 *
 */
public class OrdAdlYhPluCursorModel implements Serializable{
    private static final long serialVersionUID = 7619026138961895045L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PLUID
     * 商品ID
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long pluId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PLUCODE
     * 商品编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String pluCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PLUNAME
     * 商品名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String pluName;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.BARCODE
     * 商品条码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String barCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.SPEC
     * 规格
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String spec;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.UNIT
     * 单位
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String unit;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PRICE
     * 售价
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double price;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PRICE
     * 可用库存数量
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double kcCount;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDCDADLYHBODY.STOTAL
     * 明日到货数量
     * @mbggenerated Mon Aug 19 13:44:22 CST 2019
     */
    private Double mRJhCount;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PACKUNIT
     * 包装单位
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String packUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PACKQTY
     * 包装细数
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double packQty;

    /**
     * @return the pluId
     */
    public Long getPluId() {
        return pluId;
    }

    /**
     * @param pluId the pluId to set
     */
    public void setPluId(Long pluId) {
        this.pluId = pluId;
    }

    /**
     * @return the pluCode
     */
    public String getPluCode() {
        return pluCode;
    }

    /**
     * @param pluCode the pluCode to set
     */
    public void setPluCode(String pluCode) {
        this.pluCode = pluCode;
    }

    /**
     * @return the pluName
     */
    public String getPluName() {
        return pluName;
    }

    /**
     * @param pluName the pluName to set
     */
    public void setPluName(String pluName) {
        this.pluName = pluName;
    }

    /**
     * @return the barCode
     */
    public String getBarCode() {
        return barCode;
    }

    /**
     * @param barCode the barCode to set
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    /**
     * @return the spec
     */
    public String getSpec() {
        return spec;
    }

    /**
     * @param spec the spec to set
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the kcCount
     */
    public Double getKcCount() {
        return kcCount;
    }

    /**
     * @param kcCount the kcCount to set
     */
    public void setKcCount(Double kcCount) {
        this.kcCount = kcCount;
    }

    /**
     * @return the mRJhCount
     */
    public Double getmRJhCount() {
        return mRJhCount;
    }

    /**
     * @param mRJhCount the mRJhCount to set
     */
    public void setmRJhCount(Double mRJhCount) {
        this.mRJhCount = mRJhCount;
    }

    /**
     * @return the packUnit
     */
    public String getPackUnit() {
        return packUnit;
    }

    /**
     * @param packUnit the packUnit to set
     */
    public void setPackUnit(String packUnit) {
        this.packUnit = packUnit;
    }

    /**
     * @return the packQty
     */
    public Double getPackQty() {
        return packQty;
    }

    /**
     * @param packQty the packQty to set
     */
    public void setPackQty(Double packQty) {
        this.packQty = packQty;
    }
}